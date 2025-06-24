package com.mover.payloads.orderrelated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DropLocationDto {

    @NotBlank
    private String address;

    @NotNull
    private Long pincode;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    @NotBlank
    private String contactPerson;

    @NotNull
    private Long contactPhone;

    private String instructions;
}
