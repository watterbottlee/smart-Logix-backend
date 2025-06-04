package com.mover.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    private boolean confirmed;

    public Order() {}

    public Order(String item, boolean confirmed) {
        this.item = item;
        this.confirmed = confirmed;
    }

    public Long getId() { return id; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public boolean isConfirmed() { return confirmed; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }
}
