package com.mover.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterDTO {
    @NotEmpty (message = "name should not be empty")
    private String name;
    @NotEmpty(message = "email should not be empty")
    private String email;
    private String password;
    @NotEmpty(message = "phone number should not be empty")
    private String phone;
    @NotEmpty(message = "license number should not be empty")
    private String licenseNumber;

    @Valid
    @NotNull(message = "vehicle details should not be null")
    private VehicleDetailsDTO vehicleDetails;
    @Valid
    @NotNull(message = "address should not be null")
    private AddressDTO address;
}