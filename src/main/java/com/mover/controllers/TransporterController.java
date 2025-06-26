package com.mover.controllers;

import com.mover.exceptions.DeleteResponse;
import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.TransporterDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;
import com.mover.services.TransporterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transporters")
public class TransporterController {

    @Autowired
    private TransporterService transporterService;

    @PostMapping("register")
    public ResponseEntity<TransporterDto> registerTransporter(@Valid @RequestBody TransporterDto transporterDto) {
        TransporterDto registeredTransporter = this.transporterService.registerTransporter(transporterDto);
        return new ResponseEntity<>(registeredTransporter, HttpStatus.CREATED);
    }

    @PutMapping("update-transporter/{transporterId}")
    public ResponseEntity<TransporterDto> updateTransporter(
            @Valid @RequestBody TransporterDto transporterDto, @PathVariable("transporterId") Long transporterId) {
        TransporterDto updatedTransporter = this.transporterService.updateTransporter(transporterId, transporterDto);
        return new ResponseEntity<>(updatedTransporter, HttpStatus.OK);
    }

    @GetMapping("gettransporterbyid/{id}")
    public ResponseEntity<TransporterDto> getTransporterById(@PathVariable("id") Long transporterId) {
        TransporterDto transporterDto = this.transporterService.getTransporterById(transporterId);
        return new ResponseEntity<>(transporterDto, HttpStatus.OK);
    }

    @GetMapping("gettransporterbyemail/{emailId}")
    public ResponseEntity<TransporterDto> getTransporterByEmail(@PathVariable("emailId") String emailId) {
        TransporterDto transporterDto = this.transporterService.getTransporterByEmail(emailId);
        return new ResponseEntity<>(transporterDto, HttpStatus.OK);
    }

    @DeleteMapping("delete-transporter/{transporterId}")
    public ResponseEntity<DeleteResponse> deleteTransporter(@PathVariable("transporterId") Long transporterId) {
        this.transporterService.deleteTransporter(transporterId);
        return new ResponseEntity<>(
                new DeleteResponse("transporter deleted successfully", true), HttpStatus.OK);
    }

    // TransporterAddress operations

    @PostMapping("{transporterId}/address/add")
    public ResponseEntity<TransporterAddressDto> addAddress(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto addedAddress = this.transporterService.addAddress(transporterId, transporterAddressDto);
        return new ResponseEntity<>(addedAddress, HttpStatus.CREATED);
    }

    @PutMapping("address/update/{addressId}")
    public ResponseEntity<TransporterAddressDto> updateAddress(
            @PathVariable("addressId") Long addressId,
            @Valid @RequestBody TransporterAddressDto transporterAddressDto) {
        TransporterAddressDto updatedAddress = this.transporterService.updateAddress(addressId, transporterAddressDto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyid/{addressId}")
    public ResponseEntity<TransporterAddressDto> getAddressById(@PathVariable("addressId") Long addressId) {
        TransporterAddressDto addressDto = this.transporterService.getAddressById(addressId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @GetMapping("address/getaddressbyemail/{emailId}")
    public ResponseEntity<TransporterAddressDto> getAddressByTransporterEmail(@PathVariable("emailId") String emailId) {
        TransporterAddressDto addressDto = this.transporterService.getAddressByTransporterEmail(emailId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    // VehicleDetails operations

    @PostMapping("{transporterId}/vehicle/add")
    public ResponseEntity<VehicleDetailsDto> addVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto addedVehicle = this.transporterService.addVehicleDetail(transporterId, vehicleDetailsDto);
        return new ResponseEntity<>(addedVehicle, HttpStatus.CREATED);
    }

    @PutMapping("{transporterId}/vehicle/update")
    public ResponseEntity<VehicleDetailsDto> updateVehicleDetail(
            @PathVariable("transporterId") Long transporterId,
            @Valid @RequestBody VehicleDetailsDto vehicleDetailsDto) {
        VehicleDetailsDto updatedVehicle = this.transporterService.updateVehicleDetail(transporterId, vehicleDetailsDto);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebyid/{vehicleId}")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetailById(@PathVariable("vehicleId") Long vehicleId) {
        VehicleDetailsDto vehicleDto = this.transporterService.getVehicleDetailById(vehicleId);
        return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
    }

    @GetMapping("vehicle/getvehiclebytransporter/{transporterId}")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetailByTransporter(@PathVariable("transporterId") Long transporterId) {
        VehicleDetailsDto vehicleDto = this.transporterService.getVehicleDetailByTransporterEmail(transporterId);
        return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
    }
}