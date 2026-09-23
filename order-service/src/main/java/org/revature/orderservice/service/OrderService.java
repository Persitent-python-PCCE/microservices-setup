package org.revature.orderservice.service;

import org.revature.orderservice.models.Order;
import org.revature.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository repo;

    public OrderService(OrderRepository repo){
        this.repo = repo;
    }

    public Order saveOrder(Order o){
        return repo.save(o);
    }

    public List<Order> getAllOrders(){
        return repo.findAll();
    }

}
