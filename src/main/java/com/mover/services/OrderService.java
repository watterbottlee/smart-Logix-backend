package com.mover.services;

import com.mover.entities.Order;
import com.mover.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setConfirmed(false);
        return orderRepository.save(order);
    }

    public Order confirmOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setConfirmed(true);
            return orderRepository.save(order);
        }
        return null;
    }
}
