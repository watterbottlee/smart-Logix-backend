package com.mover.services.impl;

import com.mover.entities.*;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.DropLocationDto;
import com.mover.payloads.OrderDetailsDto;
import com.mover.payloads.OrderDto;
import com.mover.payloads.PickupLocationDto;
import com.mover.repositories.OrderRepository;
import com.mover.repositories.UserRepository;
import com.mover.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = this.toOrder(orderDto);
        Order savedOrder = this.orderRepo.save(order);
        return this.toOrderDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("order","orderId",String.valueOf(orderId)));

        Order updatedOrder = this.toOrder(orderDto);
        updatedOrder.setId(orderId);
        updatedOrder.setCreatedAt(order.getCreatedAt());
        updatedOrder.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepo.save(updatedOrder);
        return toOrderDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("order","orderId",String.valueOf(orderId)));
        return this.toOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders(Long userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));
        List<Order> allOrders = this.orderRepo.findByUserId(userId);
        List<OrderDto> allOrderDtos = allOrders.stream().map(this::toOrderDto).toList();
        return allOrderDtos;
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("order","orderId",String.valueOf(orderId)));
        this.orderRepo.delete(order);
    }

    private OrderDto toOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setDeliveryType(order.getDeliveryType());
        orderDto.setScheduledPickupTime(order.getScheduledPickupTime());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());

        // Map User ID (assuming User entity has getId() method)
        if (order.getUser() != null) {
            orderDto.setUserID(order.getUser().getUserId());
        }
        if (order.getPickupLocation() != null) {
            orderDto.setPickupLocation(modelMapper.map(order.getPickupLocation(),(Type) PickupLocationDto.class));
        }
        if (order.getDropLocation() != null) {
            orderDto.setDropLocation(modelMapper.map(order.getDropLocation(), (Type) DropLocationDto.class));
        }
        if (order.getOrderDetails() != null) {
            orderDto.setOrderDetails(modelMapper.map(order.getOrderDetails(), (Type) OrderDetailsDto.class));
        }
        return orderDto;
    }

    // Convert OrderDto to Order entity
    private Order toOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        Order order = new Order();

        if (orderDto.getId() != null) {
            order.setId(orderDto.getId());
        }
        order.setDeliveryType(orderDto.getDeliveryType());
        order.setScheduledPickupTime(orderDto.getScheduledPickupTime());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setUpdatedAt(orderDto.getUpdatedAt());

        if (orderDto.getUserID() != null) {
            User user = userRepo.findById(orderDto.getUserID())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDto.getUserID()));
            order.setUser(user);
        }

        if (orderDto.getPickupLocation() != null) {
            order.setPickupLocation(modelMapper.map(orderDto.getPickupLocation(), PickupLocation.class));
        }

        if (orderDto.getDropLocation() != null) {
            order.setDropLocation(modelMapper.map(orderDto.getDropLocation(), DropLocation.class));
        }

        if (orderDto.getOrderDetails() != null) {
            order.setOrderDetails(modelMapper.map(orderDto.getOrderDetails(), OrderDetails.class));
        }
        return order;
    }
}