package com.mover.services.impl;

import com.mover.entities.Address;
import com.mover.entities.Transporter;
import com.mover.entities.VehicleDetails;
import com.mover.entities.VehicleDimensions;
import com.mover.payloads.AddressDTO;
import com.mover.payloads.TransporterDTO;
import com.mover.payloads.VehicleDetailsDTO;
import com.mover.payloads.VehicleDimensionDTO;
import com.mover.repositories.TransporterRepository;
import com.mover.services.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TransporterServiceImpl implements TransporterService {

    @Autowired
    private TransporterRepository transporterRepository;
    


    @Override
    public Transporter registerTransporter(TransporterDTO dto) {
        // Validate email and license number
        if (transporterRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (transporterRepository.existsByLicenseNumber(dto.getLicenseNumber())) {
            throw new RuntimeException("License number already exists!");
        }

        // Convert DTO to entity
        Transporter transporter = new Transporter();
        transporter.setName(dto.getName());
        transporter.setEmail(dto.getEmail());// Secure password storage
        transporter.setPhone(dto.getPhone());
        transporter.setLicenseNumber(dto.getLicenseNumber());
        transporter.setVehicleDetails(convertToEntity(dto.getVehicleDetails()));
        transporter.setAddress(convertToEntity(dto.getAddress()));

        return transporterRepository.save(transporter);
    }


    private VehicleDetails convertToEntity(VehicleDetailsDTO dto) {
        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setVehicleType(dto.getVehicleType());
        vehicleDetails.setVehicleNumber(dto.getVehicleNumber());
        vehicleDetails.setCapacity(dto.getCapacity());
        vehicleDetails.setDimensions(convertToDimensions(dto.getDimensions()));

        return vehicleDetails;
    }
    
    private VehicleDimensions convertToDimensions(VehicleDimensionDTO dto) {
        if (dto == null) {
            return null;
        }
        VehicleDimensions dimensions = new VehicleDimensions();
        dimensions.setLength(dto.getLength());
        dimensions.setWidth(dto.getWidth());
        dimensions.setHeight(dto.getHeight());
        return dimensions;
    }

    private Address convertToEntity(AddressDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setCountry(dto.getCountry());
        return address;
    }
    

}