package com.mover.repositories;

import com.mover.entities.transporterrelated.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {
}
