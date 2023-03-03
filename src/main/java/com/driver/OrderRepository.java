package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    static HashMap<String,Order> orderMap = new HashMap<>();
    static HashMap<String,DeliveryPartner> partnerMap = new HashMap<>();
    static HashMap<String,Order> OrderPartnerMap = new HashMap<>();





    public static void add_order(Order order) {
        orderMap.put(order.getId(),order);
    }

    public static void add_partner(String partnerId) {
        if(!partnerMap.containsKey(partnerId)){
            DeliveryPartner p = new DeliveryPartner(partnerId);


            partnerMap.put(partnerId,p);
        }

    }

    public void addOrderPartner(String orderId, String partnerId) {
        if(partnerMap.containsKey(partnerId)) {
            DeliveryPartner p = partnerMap.get(partnerId);
            p.setNumberOfOrders(p.getNumberOfOrders()+1);

            partnerMap.put(partnerId,p);
            Order o = orderMap.get(orderId);

            OrderPartnerMap.put(partnerId , o);


        }
        else{
            System.out.println("Invalid partner ID");
        }

    }

    public Order getOrderByID(String orderId) {
        Order o = orderMap.get(orderId);
        return o;
    }

    public DeliveryPartner getPartnerByID(String partnerId) {

        DeliveryPartner p = partnerMap.get(partnerId);
        return  p;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        DeliveryPartner p = partnerMap.get(partnerId);

        int count = p.getNumberOfOrders();
        return count;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> orders = new ArrayList<>();
        Order o = OrderPartnerMap.get(partnerId);
        orders.add(o.getId());
        return orders;
        //here added only 1 order id not objects................................................................
    }

    public void delPartner(String partnerId) {
        partnerMap.remove(partnerId);
        OrderPartnerMap.remove((partnerId));

    }

    public void delOrder(String orderId) {
        orderMap.remove(orderId);
    }
}
