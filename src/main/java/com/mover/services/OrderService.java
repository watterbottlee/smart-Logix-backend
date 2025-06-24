package com.mover.services;

import com.mover.payloads.orderrelated.OrderDto;
import com.mover.payloads.apirequests.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderRequest orderRequest);
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders(Long userId);
    List<OrderDto> getAllOrdersBYCity(String city, String status);
    void deleteOrder(Long orderId);

}