package com.driver;

import java.lang.reflect.Parameter;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        orderMap.put(order.getId(), order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        partnerMap.put(partnerId,partner);
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            //private HashMap<String, HashSet<String>> partnerToOrderMap;

            //partnerToOrderMap
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            if(orderSet==null)
            {
                orderSet=new HashSet<>();
            }
            orderSet.add(orderId);
            partnerToOrderMap.put(partnerId,orderSet);

            //increase order count of partner
            DeliveryPartner partner= partnerMap.get(partnerId);
            partner.setNumberOfOrders(partner.getNumberOfOrders()+1);
            partnerMap.put(partnerId,partner);

            //private HashMap<String, String> orderToPartnerMap;
            //orderToPartnerMap
            orderToPartnerMap.put(orderId,partnerId);
        }
    }

    public Order findOrderById(String orderId)
    {
        // your code here
        if(orderMap.containsKey(orderId))
        {
            return orderMap.get(orderId);
        }
        return null;
    }

    public DeliveryPartner findPartnerById(String partnerId)
    {
        // your code here
        if(partnerMap.containsKey(partnerId))
        {
            return partnerMap.get(partnerId);
        }
        return null;
    }

    public Integer findOrderCountByPartnerId(String partnerId)
    {
        // your code here
        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            return orderSet.size();
        }
        return 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId)
    {
        // your code here
        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            return new ArrayList<String>(orderSet);
        }
        return new ArrayList<String>();
    }

    public List<String> findAllOrders()
    {
        // your code here
        // return list of all orders
        List<String> orderIds = new ArrayList<>();
        for(String orderId:orderMap.keySet())
        {
            orderIds.add(orderId);
        }
        return orderIds;
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            for(String orderId:orderSet)
            {
                orderToPartnerMap.remove(orderId); //unassigned orders
            }
            partnerToOrderMap.remove(partnerId); //removed partner
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        if(orderToPartnerMap.containsKey(orderId))
        {
            String partnerId=orderToPartnerMap.get(orderId);

            //removed order
            orderToPartnerMap.remove(orderId);

            //remove order from the list of partner
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            orderSet.remove(orderId);
            partnerToOrderMap.put(partnerId,orderSet);
        }
    }

    public Integer findCountOfUnassignedOrders()
    {
        // your code here
        int totalOrders=orderMap.size();
        int orderAssigned=orderToPartnerMap.size();
        return totalOrders-orderAssigned;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId)
    {
        int time=0;
        // your code here
        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            for(String orderId:orderSet)
            {
                Order order =orderMap.get(orderId);
                int deliveryTime=order.getDeliveryTime();

                int hour=Integer.parseInt(timeString.substring(0,2));
                int min=Integer.parseInt(timeString.substring(3));
                int timeReached=hour*60+min;
                if(timeReached<deliveryTime)
                {
                    time++;
                }
            }
        }
        return time;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        String time="";
        // your code here
        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> orderSet=partnerToOrderMap.get(partnerId);
            int maxDeliveryTime=Integer.MIN_VALUE;
            for(String orderId:orderSet)
            {
                Order order =orderMap.get(orderId);
                int deliveryTime=order.getDeliveryTime();
                if(maxDeliveryTime<deliveryTime)
                {
                    maxDeliveryTime=deliveryTime;
                }
            }
            int hour=maxDeliveryTime/60;
            int min=maxDeliveryTime%60;
            time=hour+":"+min;
        }
        return time;
    }
}