package com.mover.controllers;

import com.mover.exceptions.DeleteResponse;
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
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("update-user/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId){
        UserDto updatedUser = this.userService.updateUser(userId,userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("getallusers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUserDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(allUserDtos,HttpStatus.OK);
    }

    @GetMapping("getuserbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("getuserbyemail/{emailId}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("emailId") String emailId){
        UserDto userDto = this.userService.getUserByEmail(emailId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(
                new DeleteResponse("user deleted successfully",true),HttpStatus.OK);
    }

    // TransporterAddress operations

    @PostMapping("{transporterId}/address/add")
    public ResponseEntity<TransporterAddressDto> addAddress(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto addedAddress = this.userService.addAddress(transporterId, transporterAddressDto);
        return new ResponseEntity<>(addedAddress, HttpStatus.CREATED);
    }

    @PutMapping("address/update/{addressId}")
    public ResponseEntity<TransporterAddressDto> updateAddress(
            @PathVariable("addressId") Long addressId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto updatedAddress = this.userService.updateAddress(addressId, transporterAddressDto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyid/{addressId}")
    public ResponseEntity<TransporterAddressDto> getAddressById(@PathVariable("addressId") Long addressId) {
        TransporterAddressDto addressDto = this.userService.getAddressById(addressId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyemail/{emailId}")
    public ResponseEntity<TransporterAddressDto> getAddressByTransporterEmail(@PathVariable("emailId") String emailId) {
        TransporterAddressDto addressDto = this.userService.getAddressByTransporterEmail(emailId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    // VehicleDetails operations

    @PostMapping("{transporterId}/vehicle/add")
    public ResponseEntity<VehicleDetailsDto> addVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto addedVehicle = this.userService.addVehicleDetail(transporterId, vehicleDetailsDto);
        return new ResponseEntity<>(addedVehicle, HttpStatus.CREATED);
    }

    @PutMapping("{transporterId}/vehicle/update")
    public ResponseEntity<VehicleDetailsDto> updateVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto updatedVehicle = this.userService.updateVehicleDetail(transporterId, vehicleDetailsDto);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebyid/{vehicleId}")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetailById(@PathVariable("vehicleId") Long vehicleId) {
        VehicleDetailsDto vehicleDto = this.userService.getVehicleDetailById(vehicleId);
        return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebytransporter/{transporterId}")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetailByTransporter(@PathVariable("transporterId") Long transporterId) {
        VehicleDetailsDto vehicleDto = this.userService.getVehicleDetailByUserEmail(transporterId);
        return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
    }
}
