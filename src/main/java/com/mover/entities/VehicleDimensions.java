package com.mover.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDimensions {


    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;
}