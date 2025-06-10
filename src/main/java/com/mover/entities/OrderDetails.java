package com.mover.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private String category;

    private Boolean isFragile;

    private String notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dimensions_id")
    private Dimensions dimensions;
}
