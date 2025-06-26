package com.mover.entities.orderrelated;

import com.mover.entities.User;
import com.mover.entities.transporterrelated.Transporter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_location_id")
    private PickupLocation pickupLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drop_location_id")
    private DropLocation dropLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transporter_id") // foreign key column
    private Transporter transporter;


    private String deliveryType;

    private LocalDateTime scheduledPickupTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean confirmed = false; // default value for safe insert

    private BigDecimal price;
    private String city;
    private String status;

}