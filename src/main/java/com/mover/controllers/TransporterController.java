package com.mover.controllers;

import com.mover.entities.transporterrelated.Transporter;
import com.mover.payloads.transporterrelated.TransporterDTO;
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
    public ResponseEntity<TransporterDTO> registerTransporter(@Valid @RequestBody TransporterDTO transporterDTO) {
        TransporterDTO savedTransporter = transporterService.registerTransporter(transporterDTO);
        return new ResponseEntity<>(savedTransporter, HttpStatus.CREATED);
    }

    // GET: Retrieve transporter by email
    @GetMapping("/by-email/{email}")
    public ResponseEntity<TransporterDTO> getTransporterByEmail(@PathVariable String email) {
        TransporterDTO transporter = transporterService.getTransporterByEmail(email);
        return ResponseEntity.ok(transporter);
    }

    // GET: Retrieve transporter by ID
    @GetMapping("/{id}")
    public ResponseEntity<TransporterDTO> getTransporterById(@PathVariable Long id) {
        TransporterDTO transporter = transporterService.getTransporterById(id);
        return ResponseEntity.ok(transporter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransporterDTO> updateTransporter(@PathVariable Long id, @RequestBody TransporterDTO dto) {
        TransporterDTO updated = transporterService.updateTransporter(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransporter(@PathVariable Long id) {
        transporterService.deleteTransporter(id);
        return new ResponseEntity<>("Transporter deleted successfully.", HttpStatus.OK);
    }

}
