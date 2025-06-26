package com.mover.repositories;


import com.mover.entities.transporterrelated.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {
    boolean existsByEmail(String email);
    boolean existsByLicenseNumber(String licenseNumber);

    Optional<Transporter> findByEmail(String email);
}