package com.mover.payloads.orderrelated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    @NotNull
    private Long userID;

    @NotNull
    @Valid
    private PickupLocationDto pickupLocation;

    @NotNull
    @Valid
    private DropLocationDto dropLocation;

    @NotNull
    @Valid
    private OrderDetailsDto orderDetails;

    @NotEmpty
    private String deliveryType;

    @NotNull
    private LocalDateTime scheduledPickupTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal price;
    private String status;
}