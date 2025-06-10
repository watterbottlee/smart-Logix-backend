package com.mover.controllers;

import com.mover.payloads.OrderDto;
import com.mover.payloads.UserDto;
import com.mover.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<OrderDto> createUser(@Valid @RequestBody OrderDto orderDto){
        OrderDto createdOrder = this.orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("update-order/{orderId}")
    public ResponseEntity<OrderDto> updateUser(
            @Valid @RequestBody OrderDto orderDto, @PathVariable("orderId") Long orderId){
        OrderDto updatedOrder = this.orderService.updateOrder(orderId,orderDto);
        return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
    }
}
