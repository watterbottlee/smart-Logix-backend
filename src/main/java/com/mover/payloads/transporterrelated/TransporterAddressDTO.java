package com.mover.payloads.transporterrelated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterAddressDTO {
    @NotEmpty (message = "street should not be empty")
    private String street;
    @NotEmpty (message = "city should not be empty")
    private String city;
    @NotEmpty (message = "state should not be empty")
    private String state;
    @NotNull (message = "zip code should not be null")
    private String zipCode;
    @NotEmpty (message = "country should not be empty")
    private String country;

}