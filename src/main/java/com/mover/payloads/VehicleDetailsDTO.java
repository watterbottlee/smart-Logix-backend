package com.mover.payloads;

import com.mover.entities.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsDTO {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private Integer capacity;
    private VehicleDimensionDTO dimensions;









}