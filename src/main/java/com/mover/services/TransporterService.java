package com.mover.services;


import com.mover.entities.Transporter;
import com.mover.payloads.TransporterDTO;

public interface TransporterService {
    Transporter registerTransporter(TransporterDTO dto);
}