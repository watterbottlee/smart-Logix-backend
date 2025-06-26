package com.mover.services;


import com.mover.entities.transporterrelated.Transporter;
import com.mover.payloads.transporterrelated.TransporterDTO;



public interface TransporterService {
    TransporterDTO  registerTransporter(TransporterDTO transporter);

    TransporterDTO getTransporterByEmail(String email); //
    TransporterDTO getTransporterById(Long id);

    TransporterDTO updateTransporter(Long id, TransporterDTO dto);
    void deleteTransporter(Long id);


}