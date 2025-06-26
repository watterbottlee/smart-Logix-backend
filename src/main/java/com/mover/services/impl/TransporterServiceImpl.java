package com.mover.services.impl;

import com.mover.entities.transporterrelated.TransporterAddress;
import com.mover.entities.transporterrelated.Transporter;
import com.mover.entities.transporterrelated.VehicleDetails;
import com.mover.payloads.transporterrelated.TransporterAddressDTO;
import com.mover.payloads.transporterrelated.TransporterDTO;
import com.mover.payloads.transporterrelated.VehicleDetailsDTO;
import com.mover.repositories.TransporterRepository;
import com.mover.services.TransporterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransporterServiceImpl implements TransporterService {

    @Autowired
    private TransporterRepository transporterRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TransporterDTO registerTransporter(TransporterDTO dto) {
        if (transporterRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        if (transporterRepository.existsByLicenseNumber(dto.getLicenseNumber())) {
            throw new RuntimeException("License number already exists!");
        }

        Transporter transporter = modelMapper.map(dto, Transporter.class);
        Transporter saved = transporterRepository.save(transporter);

        return modelMapper.map(saved, TransporterDTO.class);
    }

    @Override
    public TransporterDTO getTransporterByEmail(String email) {
        Transporter transporter = transporterRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transporter not found with email: " + email));

        return modelMapper.map(transporter, TransporterDTO.class);
    }

    @Override
    public TransporterDTO getTransporterById(Long id) {
        Transporter transporter = transporterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transporter not found : "+ id));


        return modelMapper.map(transporter, TransporterDTO.class);
    }



    @Override
    public TransporterDTO updateTransporter(Long id, TransporterDTO dto) {
        Transporter existing = transporterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transporter not found with ID: " + id));

        // Only update fields you want to allow updating
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setLicenseNumber(dto.getLicenseNumber());
        existing.setPhone(dto.getPhone());
        existing.setVehicleDetails(modelMapper.map(dto.getVehicleDetails(), VehicleDetails.class));
        existing.setTransporterAddress(modelMapper.map(dto.getAddress(), TransporterAddress.class));

        Transporter updated = transporterRepository.save(existing);
        return modelMapper.map(updated, TransporterDTO.class);
    }

    @Override
    public void deleteTransporter(Long id) {
        Transporter transporter = transporterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transporter not found with ID: " + id));

        transporterRepository.delete(transporter);
    }

    private VehicleDetails toVehicleEntity(VehicleDetailsDTO dto) {
        return modelMapper.map(dto, VehicleDetails.class);
    }

    private TransporterAddress toAddressEntity(TransporterAddressDTO dto) {
        return modelMapper.map(dto, TransporterAddress.class);
    }





}











