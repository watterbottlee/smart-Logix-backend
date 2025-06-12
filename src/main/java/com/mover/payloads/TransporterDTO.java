package com.mover.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String licenseNumber;
    private VehicleDetailsDTO vehicleDetails;
    private AddressDTO address;
}