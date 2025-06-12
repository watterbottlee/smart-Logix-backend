package com.mover.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDimensionDTO {
    private Double length;
    private Double width;
    private Double height;
}