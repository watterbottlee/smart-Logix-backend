package com.mover.controllers;

import com.mover.payloads.ApiResponse;
import com.mover.payloads.orderrelated.OrderDto;
import com.mover.payloads.apirequests.OrderRequest;
import com.mover.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<OrderDto>> createUser(@Valid @RequestBody OrderRequest orderRequest){
        OrderDto createdOrder = this.orderService.createOrder(orderRequest);
        ApiResponse<OrderDto> response = ApiResponse.created(createdOrder, "Order created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update-order/{orderId}")
    public ResponseEntity<ApiResponse<OrderDto>> updateUser(
            @Valid @RequestBody OrderDto orderDto, @PathVariable("orderId") Long orderId){
        OrderDto updatedOrder = this.orderService.updateOrder(orderId, orderDto);
        ApiResponse<OrderDto> response = ApiResponse.success(updatedOrder, "Order updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all-orders/{userId}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getAllOrders(@PathVariable("userId") Long userId){
        List<OrderDto> allOrderDtos = this.orderService.getAllOrders(userId);
        ApiResponse<List<OrderDto>> response = ApiResponse.success(allOrderDtos, "Orders fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getorderbyid/{orderId}")
    public ResponseEntity<ApiResponse<OrderDto>> getUserById(@PathVariable("orderId") Long orderId){
        OrderDto orderDto = this.orderService.getOrderById(orderId);
        ApiResponse<OrderDto> response = ApiResponse.success(orderDto, "Order fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-orders-by-city/{city}/{status}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrdersByCity(
            @PathVariable("city") String city,
            @PathVariable("status") String status) {
        List<OrderDto> orders = orderService.getAllOrdersByCity(city, status);
        ApiResponse<List<OrderDto>> response = ApiResponse.success(orders, "Orders fetched by city successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete-order/{orderId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("orderId") Long orderId){
        this.orderService.deleteOrder(orderId);
        ApiResponse<String> response = ApiResponse.success("Order deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}