        package com.mover.controllers;

        import com.mover.exceptions.DeleteResponse;
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
            public ResponseEntity<OrderDto> createUser(@Valid @RequestBody OrderRequest orderRequest){
                OrderDto createdOrder = this.orderService.createOrder(orderRequest);
                return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
            }

            @PutMapping("update-order/{orderId}")
            public ResponseEntity<OrderDto> updateUser(
                    @Valid @RequestBody OrderDto orderDto, @PathVariable("orderId") Long orderId){
                OrderDto updatedOrder = this.orderService.updateOrder(orderId,orderDto);
                return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
            }

            @GetMapping("get-all-orders/{userId}")
            public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable("userId") Long userId){
                List<OrderDto> allOrderDtos = this.orderService.getAllOrders(userId);
                return new ResponseEntity<>(allOrderDtos,HttpStatus.OK);
            }

            @GetMapping("getorderbyid/{orderId}")
            public ResponseEntity<OrderDto> getUserById(@PathVariable("orderId") Long orderId){
                OrderDto orderDto = this.orderService.getOrderById(orderId);
                return new ResponseEntity<>(orderDto,HttpStatus.OK);
            }

            @GetMapping("/get-orders-by-city/{city}/{status}")
            public ResponseEntity<List<OrderDto>> getOrdersByCity(
                    @PathVariable("city") String city,
                    @PathVariable("status") String status) {

                List<OrderDto> orders = orderService.getAllOrdersByCity(city, status);
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }

            @DeleteMapping("delete-order/{orderId}")
            public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("orderId") Long orderId){
                this.orderService.deleteOrder(orderId);
                return new ResponseEntity<>(
                        new DeleteResponse("order deleted successfully",true),HttpStatus.OK);
            }
        }
