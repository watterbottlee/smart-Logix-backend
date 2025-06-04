package com.mover.controllers;

import com.mover.entities.Order;
import com.mover.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}/confirm")
    public Order confirmOrder(@PathVariable Long id) {
        return orderService.confirmOrder(id);
    }
}
