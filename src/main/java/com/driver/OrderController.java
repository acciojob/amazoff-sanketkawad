package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {


    @Autowired
    OrderServices orderServises;

    //1
    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        orderServises.addOrder(order);
        return new ResponseEntity<>("New order added successfully", HttpStatus.CREATED);
    }

    //2
    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId){
        orderServises.addPartner(partnerId);
        return new ResponseEntity<>("New delivery partner added successfully", HttpStatus.CREATED);
    }

    //3
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){

        //This is basically assigning that order to that partnerId
        orderServises.addOrderPartner(orderId,partnerId);
        return new ResponseEntity<>("New order-partner pair added successfully", HttpStatus.CREATED);
    }
    //4
    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){

        Order order= orderServises.getOrderByID(orderId);
        //order should be returned with an orderId.

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    //5
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId){

        DeliveryPartner deliveryPartner = orderServises.getPartnerByID(partnerId);;

        //deliveryPartner should contain the value given by partnerId

        return new ResponseEntity<>(deliveryPartner, HttpStatus.CREATED);
    }

    //6
    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId){

        Integer orderCount = orderServises.getOrderCountByPartnerId(partnerId);

        //orderCount should denote the orders given by a partner-id

        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
    }

    //7
    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId){
        List<String > orders = orderServises.getOrdersByPartnerId(partnerId);

        //orders should contain a list of orders by PartnerId

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders(){
        List<String> orders = null;

        //Get all orders
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders(){
        Integer countOfOrders = 0;

        //Count of orders that have not been assigned to any DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = 0;

        //countOfOrders that are left after a particular time of a DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = null;

        //Return the time when that partnerId will deliver his last delivery order.

        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }
    //12
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        orderServises.delPartner(partnerId);
        return new ResponseEntity<>(partnerId + " removed successfully", HttpStatus.CREATED);
    }

    //13
    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId){

        //Delete an order and also
        // remove it from the assigned order of that partnerId
    orderServises.delOrder(orderId);
        return new ResponseEntity<>(orderId + " removed successfully", HttpStatus.CREATED);
    }
}
