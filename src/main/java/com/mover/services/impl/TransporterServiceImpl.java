package com.mover.services.impl;

import com.mover.entities.Address;
import com.mover.entities.Transporter;
import com.mover.entities.VehicleDetails;
import com.mover.payloads.AddressDTO;
import com.mover.payloads.TransporterDTO;
import com.mover.payloads.VehicleDetailsDTO;
import com.mover.repositories.TransporterRepository;
import com.mover.services.TransporterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TransporterServiceImpl implements TransporterService {

    @Autowired
    private TransporterRepository transporterRepository;
    
@Autowired
private ModelMapper modelMapper;
    @Override
    public Transporter registerTransporter(TransporterDTO dto) {

        if (transporterRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (transporterRepository.existsByLicenseNumber(dto.getLicenseNumber())) {
            throw new RuntimeException("License number already exists!");
        }

        // Convert DTO to entity
        Transporter transporter = this.modelMapper.map(dto, Transporter.class);
        return transporterRepository.save(transporter);
    }


    private VehicleDetails toVehicleEntity(VehicleDetailsDTO dto) {
        return modelMapper.map(dto, VehicleDetails.class);
    }

    private Address toAddressEntity(AddressDTO dto) {
        return modelMapper.map(dto, Address.class);
    }


}