package com.mover.repositories;


import com.mover.entities.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {
    boolean existsByEmail(String email);
    boolean existsByLicenseNumber(String licenseNumber);
}