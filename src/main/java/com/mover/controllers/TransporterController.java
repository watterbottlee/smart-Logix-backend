package com.mover.controllers;

import com.mover.entities.Transporter;
import com.mover.payloads.TransporterDTO;
import com.mover.services.TransporterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transporters")
public class TransporterController {

    @Autowired
    private TransporterService transporterService;

    // POST: Register a new transporter
    @PostMapping("/register")
    public ResponseEntity<Transporter> registerTransporter(@Valid @RequestBody TransporterDTO transporterDTO) {
        Transporter savedTransporter = transporterService.registerTransporter(transporterDTO);
        return new ResponseEntity<>(savedTransporter, HttpStatus.CREATED);
    }
}
