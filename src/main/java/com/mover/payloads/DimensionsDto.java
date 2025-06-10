package com.mover.payloads;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DimensionsDto {
    @Positive
    private Double length;

    @Positive
    private Double width;

    @Positive
    private Double height;
}
