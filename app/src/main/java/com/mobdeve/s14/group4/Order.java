package com.mobdeve.s14.group4;

import com.google.firebase.database.ServerValue;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private ArrayList<OrderDetails> orderDetails;
    private Object timestamp;

    //customer details
    private String customer;
    private Address address;

    //order status
    private boolean paid;
    private boolean delivered;

    public Order(){
        this.orderDetails = new ArrayList<OrderDetails>();
        this.paid = false;
        this.delivered = false;
    }

    public Order(String customer, Address address){
        this.customer = customer;
        this.address = address;
        this.orderDetails = new ArrayList<OrderDetails>();
        this.timestamp = ServerValue.TIMESTAMP;
        this.paid = false;
        this.delivered = false;
    }

    public String getId() {
        return orderId;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public String getCustomer() {
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}