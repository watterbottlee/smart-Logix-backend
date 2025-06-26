package com.mover.services;

import com.mover.payloads.transporterrelated.TransporterAddressDto;
import com.mover.payloads.transporterrelated.TransporterDto;
import com.mover.payloads.transporterrelated.VehicleDetailsDto;

public interface TransporterService {
    TransporterDto registerTransporter(TransporterDto transporterDto);
    TransporterDto updateTransporter(Long transporterId, TransporterDto transporterDto);
    TransporterDto deleteTransporter(Long transporterId);
    TransporterDto getTransporterById(Long transporterId);
    TransporterDto getTransporterByEmail(String emailId);

    //setter and getters for addresses
    TransporterAddressDto addAddress(Long transporterId, TransporterAddressDto transporterAddressDto);
    TransporterAddressDto updateAddress(Long transporterAddressId, TransporterAddressDto transporterAddressDto);
    TransporterAddressDto getAddressById(Long transporterAddressId);
    TransporterAddressDto getAddressByTransporterEmail(String emailId);

    //setter and getter for vehicle details
    VehicleDetailsDto addVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDo);
    VehicleDetailsDto updateVehicleDetail(Long transporterId, VehicleDetailsDto vehicleDetailsDo);
    VehicleDetailsDto getVehicleDetailById(Long vehicleId);
    VehicleDetailsDto getVehicleDetailByTransporterEmail(Long transporterEmail);
}
