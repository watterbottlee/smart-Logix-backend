package com.mover.payloads;

import com.mover.entities.VehicleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsDTO {
    @NotNull (message = "vehicle type should not be empty")
    private VehicleType vehicleType;
    @NotEmpty (message = "vehicle number should not be empty")
    private String vehicleNumber;
    @NotNull (message = "capacity should not be null")
    private Integer capacity;










}