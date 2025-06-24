package com.mover.payloads.apirequests;

import com.mover.payloads.orderrelated.DropLocationDto;
import com.mover.payloads.orderrelated.OrderDetailsDto;
import com.mover.payloads.orderrelated.PickupLocationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
