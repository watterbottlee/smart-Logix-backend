package com.mover.services.impl;

import com.mover.entities.*;
import com.mover.entities.orderrelated.DropLocation;
import com.mover.entities.orderrelated.Order;
import com.mover.entities.orderrelated.OrderDetails;
import com.mover.entities.orderrelated.PickupLocation;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.orderrelated.DropLocationDto;
import com.mover.payloads.orderrelated.OrderDetailsDto;
import com.mover.payloads.orderrelated.OrderDto;
import com.mover.payloads.orderrelated.PickupLocationDto;
import com.mover.payloads.apirequests.OrderRequest;
import com.mover.repositories.OrderRepository;
import com.mover.repositories.UserRepository;
import com.mover.services.OrderService;
import com.mover.services.PriceGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PriceGenerator priceGenerator;

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
    public List<OrderDto> getAllOrdersByCity(String city, String status) {
        // Input validation
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }


        String trimmedCity = city.trim();
        String trimmedStatus = status.trim();
        log.info("got a city with: "+trimmedCity);

        List<Order> listOfOrders = this.orderRepo.findOrdersByPickupCityAndStatus(trimmedCity, trimmedStatus);
        log.info("found something in the db");

        List<OrderDto> listOfOrderDto = listOfOrders.stream()
                .map(this::toOrderDto)
                .toList();
        log.info("made a list of orders with city");

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
        orderDto.setTransporterId(order.getTransporterId());
        log.info("set transporterId to null in order->orderDto");

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
        if(orderDto.getTransporterId()!=null){
            User transporter = userRepo.findById(orderDto.getTransporterId())
                    .orElseThrow(() -> new EntityNotFoundException("transporter not found with id: " + orderDto.getTransporterId()));
            order.setTransporterId(transporter.getUserId());
            log.info("set transporterId orderDto->order");
        }
        if(orderDto.getTransporterId()==null){
            order.setTransporterId(null);
            log.info("set transporterId to null in orderDto->order");
        }

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

    private OrderDto OrderRequestToOrderDto(OrderRequest orderRequest){
        if (orderRequest == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderRequest.getId());
        orderDto.setDeliveryType(orderRequest.getDeliveryType());
        orderDto.setScheduledPickupTime(orderRequest.getScheduledPickupTime());
        orderDto.setCreatedAt(orderRequest.getCreatedAt());
        orderDto.setUpdatedAt(orderRequest.getUpdatedAt());
        orderDto.setPrice(priceGenerator.generatePrice(orderRequest));
        orderDto.setStatus("active");
        orderDto.setTransporterId(null);
        log.info("set transporterId to null in request->orderDto");

        // Map User ID (assuming User entity has getId() method)
        if (orderRequest.getUserID() != null) {
            orderDto.setUserID(orderRequest.getUserID());
        }
        if (orderRequest.getPickupLocation() != null) {
            orderDto.setPickupLocation(modelMapper.map(orderRequest.getPickupLocation(),(Type) PickupLocationDto.class));
        }
        if (orderRequest.getDropLocation() != null) {
            orderDto.setDropLocation(modelMapper.map(orderRequest.getDropLocation(), (Type) DropLocationDto.class));
        }
        if (orderRequest.getOrderDetails() != null) {
            orderDto.setOrderDetails(modelMapper.map(orderRequest.getOrderDetails(), (Type) OrderDetailsDto.class));
        }
        return orderDto;

    }
}