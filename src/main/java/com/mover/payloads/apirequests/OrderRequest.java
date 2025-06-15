package com.mover.payloads.apirequests;

import com.mover.payloads.DropLocationDto;
import com.mover.payloads.OrderDetailsDto;
import com.mover.payloads.PickupLocationDto;
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
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
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
}
