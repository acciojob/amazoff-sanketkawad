package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order) {
        OrderRepository.add_order(order);
    }

    public void addPartner(String partnerId) {
        OrderRepository.add_partner(partnerId);
    }

    public void addOrderPartner(String orderId, String partnerId) {
        orderRepository.addOrderPartner(orderId,partnerId);
    }

    public Order getOrderByID(String orderId) {
        return orderRepository.getOrderByID(orderId);
    }

    public DeliveryPartner getPartnerByID(String partnerId) {
        return orderRepository.getPartnerByID(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return  orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> orders = orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }

    public void delPartner(String partnerId) {
        orderRepository.delPartner(partnerId);
    }

    public void delOrder(String orderId) {
        orderRepository.delOrder(orderId);
    }
}
