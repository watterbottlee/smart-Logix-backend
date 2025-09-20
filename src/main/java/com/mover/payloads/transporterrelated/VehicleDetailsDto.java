package com.mover.payloads.transporterrelated;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetailsDto {

    private Long vehicleId;

    @NotNull
    private Long userId;

    @NotNull
    //truck , pickup etc
    private String vehicleType;

    @NotNull
    private String vehicleNumber;

    @NotNull
    private String vehicleMake;

    @NotNull
    private String vehicleModel;

    //self, rental
    @NotNull
    private String owner;

}
