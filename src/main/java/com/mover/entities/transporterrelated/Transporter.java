package com.mover.entities.transporterrelated;

import com.mover.entities.orderrelated.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transporters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transporter_id")
    private Long transporterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "vehicle_details_id",
            foreignKey = @ForeignKey(name = "fk_transporter_vehicle_details")
    )
    private VehicleDetails vehicleDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            foreignKey = @ForeignKey(name = "fk_transporter_address")
    )
    private TransporterAddress transporterAddress;

    @OneToMany(mappedBy = "transporter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;


}