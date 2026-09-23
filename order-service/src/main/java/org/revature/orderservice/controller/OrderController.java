package org.revature.orderservice.controller;


import org.aspectj.weaver.ast.Or;
import org.revature.orderservice.models.Order;
import org.revature.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService os){
        this.service = os;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(service.saveOrder(order), HttpStatus.CREATED);
    }
}
