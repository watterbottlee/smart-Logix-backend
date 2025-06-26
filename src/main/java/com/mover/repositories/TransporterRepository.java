package com.mover.repositories;

import com.mover.entities.transporterrelated.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransporterRepository extends JpaRepository<Transporter, Long> {
    Optional<Transporter> findByEmail(String emailId);
}
