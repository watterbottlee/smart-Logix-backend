package com.mover.services;

import com.mover.payloads.OrderDto;
import com.mover.payloads.apirequests.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderRequest orderRequest);
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders(Long userId);
    void deleteOrder(Long orderId);

}