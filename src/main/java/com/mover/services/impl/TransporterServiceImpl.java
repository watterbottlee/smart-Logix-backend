package com.mover.services.impl;

import com.mover.entities.transporterrelated.Transporter;
import com.mover.entities.transporterrelated.TransporterAddress;
import com.mover.entities.transporterrelated.VehicleDetails;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.TransporterDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;
import com.mover.repositories.TransporterAddressRepository;
import com.mover.repositories.TransporterRepository;
import com.mover.repositories.VehicleDetailsRepository;
import com.mover.services.TransporterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransporterServiceImpl implements TransporterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransporterRepository transporterRepository;

    @Autowired
    private TransporterAddressRepository transporterAddressRepository;

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    // Transporter methods

    @Override
    public TransporterDto registerTransporter(TransporterDto transporterDto) {
        Transporter transporter = this.toEntity(transporterDto);
        transporter.setCreatedAt(LocalDateTime.now());
        transporter.setUpdatedAt(LocalDateTime.now());
        Transporter saved = transporterRepository.save(transporter);
        return this.toDto(saved);
    }

    @Override
    public TransporterDto updateTransporter(Long transporterId, TransporterDto transporterDto) {
        Transporter existing = transporterRepository.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterId.toString()));

        existing.setName(transporterDto.getName());
        existing.setEmail(transporterDto.getEmail());
        existing.setPassword(transporterDto.getPassword());
        existing.setPhone(transporterDto.getPhone());
        existing.setUpdatedAt(LocalDateTime.now());

        Transporter updated = transporterRepository.save(existing);
        return this.toDto(updated);
    }

    @Override
    public TransporterDto deleteTransporter(Long transporterId) {
        Transporter existing = transporterRepository.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterId.toString()));
        transporterRepository.delete(existing);
        return this.toDto(existing);
    }

    @Override
    public TransporterDto getTransporterById(Long transporterId) {
        Transporter transporter = transporterRepository.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterId.toString()));
        return this.toDto(transporter);
    }

    @Override
    public TransporterDto getTransporterByEmail(String emailId) {
        Transporter transporter = transporterRepository.findByEmail(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "email", emailId));
        return this.toDto(transporter);
    }

    // TransporterAddress methods

    @Override
    public TransporterAddressDto addAddress(Long transporterId, TransporterAddressDto transporterAddressDto) {
        Transporter transporter = transporterRepository.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterId.toString()));

        TransporterAddress address = this.toEntity(transporterAddressDto);
        address.setTransporterId(transporterId);
        TransporterAddress saved = transporterAddressRepository.save(address);
        return this.toDto(saved);
    }

    @Override
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

    @Override
    public TransporterAddressDto getAddressById(Long transporterAddressId) {
        TransporterAddress address = transporterAddressRepository.findById(transporterAddressId)
                .orElseThrow(() -> new ResourceNotFoundException("TransporterAddress", "addressId", transporterAddressId.toString()));
        return this.toDto(address);
    }

    @Override
    public TransporterAddressDto getAddressByTransporterEmail(String emailId) {
        Transporter transporter = transporterRepository.findByEmail(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "email", emailId));
        Long transporterId= transporter.getTransporterId();
        log.info("got the transporter with"+transporterId);
        TransporterAddress address = transporterAddressRepository.findByTransporterId(transporterId);
        log.info("found the address with transporter id");
        TransporterAddressDto addressDto = this.toDto(address);
        return addressDto;
    }

    // VehicleDetails methods

    @Override
    public VehicleDetailsDto addVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDto) {
        Transporter transporter = transporterRepository.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterId.toString()));

        VehicleDetails vehicleDetails = this.toEntity(vehicleDetailsDto);
        vehicleDetails.setTransporterId(transporterId);
        VehicleDetails saved = vehicleDetailsRepository.save(vehicleDetails);
        return this.toDto(saved);
    }

    @Override
    public VehicleDetailsDto updateVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDto) {
        if (vehicleDetailsDto.getVehicleId() == null) {
            throw new IllegalArgumentException("Vehicle ID must not be null for update");
        }
        VehicleDetails existing = vehicleDetailsRepository.findById(vehicleDetailsDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDetails", "vehicleId", vehicleDetailsDto.getVehicleId().toString()));

        if (!existing.getTransporterId().equals(transporterId)) {
            throw new IllegalArgumentException("Transporter ID mismatch");
        }

        existing.setVehicleType(vehicleDetailsDto.getVehicleType());
        existing.setVehicleNumber(vehicleDetailsDto.getVehicleNumber());
        existing.setVehicleMake(vehicleDetailsDto.getVehicleMake());
        existing.setVehicleModel(vehicleDetailsDto.getVehicleModel());
        existing.setOwner(vehicleDetailsDto.getOwner());

        VehicleDetails updated = vehicleDetailsRepository.save(existing);
        return this.toDto(updated);
    }

    @Override
    public VehicleDetailsDto getVehicleDetailById(Long vehicleId) {
        VehicleDetails vehicleDetails = vehicleDetailsRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDetails", "vehicleId", vehicleId.toString()));
        return this.toDto(vehicleDetails);
    }

    @Override
    public VehicleDetailsDto getVehicleDetailByTransporterEmail(Long transporterEmail) {
        // Assuming transporterEmail is the email string, but method signature uses Long - likely a mistake.
        // Adjusting to String email parameter would be better. Here, assuming Long is transporterId instead.

        Transporter transporter = transporterRepository.findById(transporterEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter", "transporterId", transporterEmail.toString()));

        Optional<VehicleDetails> vehicleDetailsOpt = vehicleDetailsRepository.findAll()
                .stream()
                .filter(vd -> vd.getTransporterId().equals(transporter.getTransporterId()))
                .findFirst();

        if (vehicleDetailsOpt.isEmpty()) {
            throw new ResourceNotFoundException("VehicleDetails", "transporterId", transporter.getTransporterId().toString());
        }
        return this.toDto(vehicleDetailsOpt.get());
    }

    // Helper methods for mapping

    private TransporterDto toDto(Transporter transporter) {
        if (transporter == null) return null;
        return modelMapper.map(transporter, TransporterDto.class);
    }

    private Transporter toEntity(TransporterDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Transporter.class);
    }

    private TransporterAddressDto toDto(TransporterAddress address) {
        if (address == null) return null;
        return modelMapper.map(address, TransporterAddressDto.class);
    }

    private TransporterAddress toEntity(TransporterAddressDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, TransporterAddress.class);
    }

    private VehicleDetailsDto toDto(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) return null;
        return modelMapper.map(vehicleDetails, VehicleDetailsDto.class);
    }

    private VehicleDetails toEntity(VehicleDetailsDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, VehicleDetails.class);
    }
}
