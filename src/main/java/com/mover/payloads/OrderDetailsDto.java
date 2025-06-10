package com.mover.payloads;

import com.mover.entities.Dimensions;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    @NotEmpty
    private String itemName;

    private String description;

    @NotEmpty
    private String category;

    @NotNull
    private Boolean isFragile;

    private String notes;

    private DimensionsDto dimensions;
}
