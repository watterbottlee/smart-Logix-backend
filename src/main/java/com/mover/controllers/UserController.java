package com.mover.controllers;

import com.mover.payloads.ApiResponse;
import com.mover.payloads.UserDto;
import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;
import com.mover.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        ApiResponse<UserDto> response = ApiResponse.created(createdUser, "User created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update-user/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId){
        UserDto updatedUser = this.userService.updateUser(userId, userDto);
        ApiResponse<UserDto> response = ApiResponse.success(updatedUser, "User updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getallusers")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(){
        List<UserDto> allUserDtos = this.userService.getAllUsers();
        ApiResponse<List<UserDto>> response = ApiResponse.success(allUserDtos, "Users fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getuserbyid/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = this.userService.getUserById(userId);
        ApiResponse<UserDto> response = ApiResponse.success(userDto, "User fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getuserbyemail/{emailId}")
    public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(@PathVariable("emailId") String emailId){
        UserDto userDto = this.userService.getUserByEmail(emailId);
        ApiResponse<UserDto> response = ApiResponse.success(userDto, "User fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
        ApiResponse<String> response = ApiResponse.success("User deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TransporterAddress operations

    @PostMapping("{transporterId}/address/add")
    public ResponseEntity<ApiResponse<TransporterAddressDto>> addAddress(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto addedAddress = this.userService.addAddress(transporterId, transporterAddressDto);
        ApiResponse<TransporterAddressDto> response = ApiResponse.created(addedAddress, "Address added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("address/update/{addressId}")
    public ResponseEntity<ApiResponse<TransporterAddressDto>> updateAddress(
            @PathVariable("addressId") Long addressId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto updatedAddress = this.userService.updateAddress(addressId, transporterAddressDto);
        ApiResponse<TransporterAddressDto> response = ApiResponse.success(updatedAddress, "Address updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyid/{addressId}")
    public ResponseEntity<ApiResponse<TransporterAddressDto>> getAddressById(@PathVariable("addressId") Long addressId) {
        TransporterAddressDto addressDto = this.userService.getAddressById(addressId);
        ApiResponse<TransporterAddressDto> response = ApiResponse.success(addressDto, "Address fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyemail/{emailId}")
    public ResponseEntity<ApiResponse<TransporterAddressDto>> getAddressByTransporterEmail(@PathVariable("emailId") String emailId) {
        TransporterAddressDto addressDto = this.userService.getAddressByTransporterEmail(emailId);
        ApiResponse<TransporterAddressDto> response = ApiResponse.success(addressDto, "Address fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // VehicleDetails operations

    @PostMapping("{transporterId}/vehicle/add")
    public ResponseEntity<ApiResponse<VehicleDetailsDto>> addVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto addedVehicle = this.userService.addVehicleDetail(transporterId, vehicleDetailsDto);
        ApiResponse<VehicleDetailsDto> response = ApiResponse.created(addedVehicle, "Vehicle details added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{transporterId}/vehicle/update")
    public ResponseEntity<ApiResponse<VehicleDetailsDto>> updateVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto updatedVehicle = this.userService.updateVehicleDetail(transporterId, vehicleDetailsDto);
        ApiResponse<VehicleDetailsDto> response = ApiResponse.success(updatedVehicle, "Vehicle details updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebyid/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleDetailsDto>> getVehicleDetailById(@PathVariable("vehicleId") Long vehicleId) {
        VehicleDetailsDto vehicleDto = this.userService.getVehicleDetailById(vehicleId);
        ApiResponse<VehicleDetailsDto> response = ApiResponse.success(vehicleDto, "Vehicle details fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebytransporter/{transporterId}")
    public ResponseEntity<ApiResponse<VehicleDetailsDto>> getVehicleDetailByTransporter(@PathVariable("transporterId") Long transporterId) {
        VehicleDetailsDto vehicleDto = this.userService.getVehicleDetailByUserEmail(transporterId);
        ApiResponse<VehicleDetailsDto> response = ApiResponse.success(vehicleDto, "Vehicle details fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}