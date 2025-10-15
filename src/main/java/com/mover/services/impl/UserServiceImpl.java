package com.mover.services.impl;

import com.mover.entities.RoleEnum;
import com.mover.entities.User;
import com.mover.entities.transporterrelated.TransporterAddress;
import com.mover.entities.transporterrelated.VehicleDetails;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.UserDto;
import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;
import com.mover.repositories.TransporterAddressRepository;
import com.mover.repositories.UserRepository;
import com.mover.repositories.VehicleDetailsRepository;
import com.mover.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransporterAddressRepository transporterAddressRepository;

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        // Only update password if provided and not empty
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        user.setPhone(userDto.getPhone());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String emailId) {
        User user = this.userRepo.findByEmail(emailId)
                .orElseThrow(()->new ResourceNotFoundException("user","email_id",emailId));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Long userId){
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));

        log.info("Deleting user with ID: {} and email: {}", userId, user.getEmail());

        try {
            // Delete related address records first (if they exist)
            TransporterAddress address = transporterAddressRepository.findByUserId(userId);
            if (address != null) {
                log.info("Deleting address for user: {}", userId);
                transporterAddressRepository.delete(address);
            }

            // Delete related vehicle records
            List<VehicleDetails> vehicles = vehicleDetailsRepository.findAll()
                    .stream()
                    .filter(vd -> vd.getUserId().equals(userId))
                    .toList();

            if (!vehicles.isEmpty()) {
                log.info("Deleting {} vehicle(s) for user: {}", vehicles.size(), userId);
                vehicleDetailsRepository.deleteAll(vehicles);
            }

            // Finally delete the user
            this.userRepo.delete(user);
            log.info("Successfully deleted user with ID: {}", userId);

        } catch (Exception e) {
            log.error("Error deleting user with ID: {}", userId, e);
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> allUsers = this.userRepo.findAll();
        return allUsers.stream().map(this::userToDto).toList();
    }

    // TransporterAddress methods

    public TransporterAddressDto addAddress(Long userId, TransporterAddressDto transporterAddressDto) {
        User transporter = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", userId.toString()));

        TransporterAddress address = this.toEntity(transporterAddressDto);
        address.setUserId(userId);
        TransporterAddress saved = transporterAddressRepository.save(address);
        return this.toDto(saved);
    }

    public TransporterAddressDto updateAddress(Long transporterAddressId, TransporterAddressDto transporterAddressDto) {
        TransporterAddress existing = transporterAddressRepository.findById(transporterAddressId)
                .orElseThrow(() -> new ResourceNotFoundException("TransporterAddress", "addressId", transporterAddressId.toString()));

        existing.setStreet(transporterAddressDto.getStreet());
        existing.setCity(transporterAddressDto.getCity());
        existing.setState(transporterAddressDto.getState());
        existing.setZipCode(transporterAddressDto.getZipCode());
        existing.setCountry(transporterAddressDto.getCountry());

        TransporterAddress updated = transporterAddressRepository.save(existing);
        return this.toDto(updated);
    }

    public TransporterAddressDto getAddressById(Long transporterAddressId) {
        TransporterAddress address = transporterAddressRepository.findById(transporterAddressId)
                .orElseThrow(() -> new ResourceNotFoundException("TransporterAddress", "addressId", transporterAddressId.toString()));
        return this.toDto(address);
    }

    public TransporterAddressDto getAddressByTransporterEmail(String emailId) {
        User transporter = userRepo.findByEmail(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "email", emailId));
        Long userId = transporter.getUserId();
        log.info("Got the transporter with userId: {}", userId);

        TransporterAddress address = transporterAddressRepository.findByUserId(userId);
        if (address == null) {
            throw new ResourceNotFoundException("TransporterAddress", "userId", userId.toString());
        }

        log.info("Found the address with transporter id: {}", userId);
        return this.toDto(address);
    }

    // VehicleDetails methods
    public VehicleDetailsDto addVehicleDetail(Long userId, VehicleDetailsDto vehicleDetailsDto) {
        User transporter = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", userId.toString()));

        VehicleDetails vehicleDetails = this.toEntity(vehicleDetailsDto);
        vehicleDetails.setUserId(userId);
        VehicleDetails saved = vehicleDetailsRepository.save(vehicleDetails);
        return this.toDto(saved);
    }

    public VehicleDetailsDto updateVehicleDetail(Long userId, VehicleDetailsDto vehicleDetailsDto) {
        if (vehicleDetailsDto.getVehicleId() == null) {
            throw new IllegalArgumentException("Vehicle ID must not be null for update");
        }
        VehicleDetails existing = vehicleDetailsRepository.findById(vehicleDetailsDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDetails", "vehicleId", vehicleDetailsDto.getVehicleId().toString()));

        if (!existing.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User ID mismatch - vehicle does not belong to this user");
        }

        existing.setVehicleType(vehicleDetailsDto.getVehicleType());
        existing.setVehicleNumber(vehicleDetailsDto.getVehicleNumber());
        existing.setVehicleMake(vehicleDetailsDto.getVehicleMake());
        existing.setVehicleModel(vehicleDetailsDto.getVehicleModel());
        existing.setOwner(vehicleDetailsDto.getOwner());

        VehicleDetails updated = vehicleDetailsRepository.save(existing);
        return this.toDto(updated);
    }

    public VehicleDetailsDto getVehicleDetailById(Long vehicleId) {
        VehicleDetails vehicleDetails = vehicleDetailsRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDetails", "vehicleId", vehicleId.toString()));
        return this.toDto(vehicleDetails);
    }

    public VehicleDetailsDto getVehicleDetailByUserEmail(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", userId.toString()));

        Optional<VehicleDetails> vehicleDetailsOpt = vehicleDetailsRepository.findAll()
                .stream()
                .filter(vd -> vd.getUserId().equals(user.getUserId()))
                .findFirst();

        if (vehicleDetailsOpt.isEmpty()) {
            throw new ResourceNotFoundException("VehicleDetails", "transporterId", user.getUserId().toString());
        }
        return this.toDto(vehicleDetailsOpt.get());
    }

    // Helper methods for TransporterAddress
    private TransporterAddressDto toDto(TransporterAddress address) {
        if (address == null) return null;
        return modelMapper.map(address, TransporterAddressDto.class);
    }

    private TransporterAddress toEntity(TransporterAddressDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, TransporterAddress.class);
    }

    // Helper methods for VehicleDetails
    private VehicleDetailsDto toDto(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) return null;
        return modelMapper.map(vehicleDetails, VehicleDetailsDto.class);
    }

    private VehicleDetails toEntity(VehicleDetailsDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, VehicleDetails.class);
    }

    // Helper methods for User
    public UserDto userToDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }

    public User DtoToUser(UserDto userDto) {
        if (userDto == null) return null;

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());

        // Convert role string to enum
        if (userDto.getRole() != null && !userDto.getRole().isEmpty()) {
            try {
                user.setRole(RoleEnum.valueOf(userDto.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Invalid role: {}, defaulting to USER", userDto.getRole());
                user.setRole(RoleEnum.USER);
            }
        } else {
            user.setRole(RoleEnum.USER); // Default role
        }

        return user;
    }
}