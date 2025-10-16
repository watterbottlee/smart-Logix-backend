package com.mover.services;

import com.mover.payloads.UserDto;
import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto getUserByEmail(String emailId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);

    //setter and getters for addresses
    TransporterAddressDto addAddress(Long transporterId, TransporterAddressDto transporterAddressDto);
    TransporterAddressDto updateAddress(Long transporterAddressId, TransporterAddressDto transporterAddressDto);
    TransporterAddressDto getAddressById(Long transporterAddressId);
    TransporterAddressDto getAddressByTransporterEmail(String emailId);

    //setter and getter for vehicle details
    VehicleDetailsDto addVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDo);
    VehicleDetailsDto updateVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDo);
    VehicleDetailsDto getVehicleDetailById(Long vehicleId);
    VehicleDetailsDto getVehicleDetailByUserEmail(Long transporterEmail);
}
