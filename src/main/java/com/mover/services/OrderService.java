package com.mover.services;

import com.mover.payloads.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders(Long userId);
    Void deleteOrder(Long orderId);

}