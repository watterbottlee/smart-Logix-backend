package com.mover.services.impl;

import com.mover.entities.*;
import com.mover.entities.orderrelated.DropLocation;
import com.mover.entities.orderrelated.Order;
import com.mover.entities.orderrelated.OrderDetails;
import com.mover.entities.orderrelated.PickupLocation;
import com.mover.entities.transporterrelated.Transporter;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.orderrelated.DropLocationDto;
import com.mover.payloads.orderrelated.OrderDetailsDto;
import com.mover.payloads.orderrelated.OrderDto;
import com.mover.payloads.orderrelated.PickupLocationDto;
import com.mover.payloads.apirequests.OrderRequest;
import com.mover.repositories.OrderRepository;
import com.mover.repositories.TransporterRepository;
import com.mover.repositories.UserRepository;
import com.mover.services.OrderService;
import com.mover.services.PriceGenerator;
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

    @Autowired
    private PriceGenerator priceGenerator;

    @Autowired
    private TransporterRepository transporterRepo;

    @Override
    public OrderDto createOrder(OrderRequest orderRequest) {
        OrderDto orderDto = this.OrderRequestToOrderDto(orderRequest);
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
    public List<OrderDto> getAllOrdersBYCity(String city, String status) {
        List<Order> listOfOrders = this.orderRepo.findOrderByCity(city,status);
        List<OrderDto> listOfOrderDto = listOfOrders.stream().map(this::toOrderDto).toList();
        return listOfOrderDto;
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
        orderDto.setPrice(order.getPrice());
        orderDto.setStatus(order.getStatus());


        // Map User ID (assuming User entity has getId() method)
        if (order.getUser() != null) {
            orderDto.setUserID(order.getUser().getUserId());
        }
        if (order.getTransporter() != null) {
            orderDto.setTransporterId(order.getTransporter().getTransporterId());
        }



        if (order.getPickupLocation() != null) {
            orderDto.setPickupLocation(modelMapper.map(order.getPickupLocation(), PickupLocationDto.class));
        }
        if (order.getDropLocation() != null) {
            orderDto.setDropLocation(modelMapper.map(order.getDropLocation(), DropLocationDto.class));
        }
        if (order.getOrderDetails() != null) {
            orderDto.setOrderDetails(modelMapper.map(order.getOrderDetails(), OrderDetailsDto.class));
        }

        return orderDto;
    }

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
        order.setPrice(orderDto.getPrice());
        order.setStatus(orderDto.getStatus());

        if (orderDto.getUserID() != null) {
            User user = userRepo.findById(orderDto.getUserID())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDto.getUserID()));
            order.setUser(user);
        }
        if (orderDto.getTransporterId() != null) {
            Transporter transporter = transporterRepo.findById(orderDto.getTransporterId())
                    .orElseThrow(() -> new EntityNotFoundException("Transporter not found with ID: " + orderDto.getTransporterId()));
            order.setTransporter(transporter);
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

    private OrderDto OrderRequestToOrderDto(OrderRequest orderRequest){
        if (orderRequest == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderRequest.getId());
        orderDto.setTransporterId(orderRequest.getTransporterId());

        orderDto.setDeliveryType(orderRequest.getDeliveryType());
        orderDto.setScheduledPickupTime(orderRequest.getScheduledPickupTime());
        orderDto.setCreatedAt(orderRequest.getCreatedAt());
        orderDto.setUpdatedAt(orderRequest.getUpdatedAt());
       orderDto.setPrice(priceGenerator.generatePrice(orderRequest));
        orderDto.setStatus("active");

        // Map User ID (assuming User entity has getId() method)
        if (orderRequest.getUserId() != null) {
            orderDto.setUserID(orderRequest.getUserId());
        }
        if (orderRequest.getPickupLocation() != null) {
            orderDto.setPickupLocation(modelMapper.map(orderRequest.getPickupLocation(), PickupLocationDto.class));
        }
        if (orderRequest.getDropLocation() != null) {
            orderDto.setDropLocation(modelMapper.map(orderRequest.getDropLocation(),  DropLocationDto.class));
        }
        if (orderRequest.getOrderDetails() != null) {
            orderDto.setOrderDetails(modelMapper.map(orderRequest.getOrderDetails(),  OrderDetailsDto.class));
        }
        return orderDto;

    }
}