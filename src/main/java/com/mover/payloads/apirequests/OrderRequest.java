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

    private Long id; // Usually ignored during creation, optional

    @NotNull(message = "User ID is required")
    private Long userId;

    // Optional: transporter can be null during order creation
    private Long transporterId;

    @NotNull(message = "Pickup location is required")
    @Valid
    private PickupLocationDto pickupLocation;

    @NotNull(message = "Drop location is required")
    @Valid
    private DropLocationDto dropLocation;

    @NotNull(message = "Order details are required")
    @Valid
    private OrderDetailsDto orderDetails;

    @NotEmpty(message = "Delivery type is required")
    private String deliveryType;

    @NotNull(message = "Scheduled pickup time is required")
    private LocalDateTime scheduledPickupTime;

    // These should be auto-managed in service layer, but allowed here if needed
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
