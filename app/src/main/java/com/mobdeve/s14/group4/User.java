package com.mobdeve.s14.group4;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class User {
    private String userId;

    private String fullName;
    private String email;
    private String password;
    private Address address;

    private String contactNo;

    private Order cart;

    private boolean isAdmin;
    private ArrayList<String> userOrdersList;

    //
    // Excluded from Firebase
    //
    private ArrayList<Order> orderHistory;


    public User(){
        this.isAdmin = false;
        this.userOrdersList = new ArrayList<String>();
        this.orderHistory = new ArrayList<Order>();
    }

    public User(String fullName, String email, String  password, Address address, String contactNo){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;

        this.isAdmin = false;
        this.userOrdersList = new ArrayList<String>();
        this.orderHistory = new ArrayList<Order>();
    }

    public User(String fullName, String email, String  password, Address address, boolean isAdmin, String contactNo){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;

        this.isAdmin = isAdmin;
        this.userOrdersList = new ArrayList<String>();
        this.orderHistory = new ArrayList<Order>();
    }

    public String getUserId(){
        return this.userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public Order getCart(){
        return this.cart;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public ArrayList<String> getUserOrdersList() {
        return this.userOrdersList;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUserOrdersList(ArrayList<String> userOrdersList) {
        this.userOrdersList = userOrdersList;
    }

    //
    // EXCLUDED FROM FIREBASE
    //
    @Exclude
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    @Exclude
    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    //
    // METHODS
    //
    public void initializeOrderLists(){
        DataHelper.orderDatabase.getOrders(this.userOrdersList, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                setOrderHistory((ArrayList<Order>) o);
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get user orders");
            }
        });
    }

    //
    // CART FUNCTIONS
    //
    public void initCart(){
        if (this.cart == null){
            this.cart = new Order(this.getFullName(), this.getAddress());
        }
        else{
            for (OrderDetails od : this.cart.getOrderDetails()){
                Book b = DataHelper.bookDatabase.findBook(od.getBookId());
                od.setBook(b);
            }
        }
    }

    public void addToCart(OrderDetails od){
        this.cart.addOrderDetail(od);
        DataHelper.userDatabase.updateCart(this.getUserId(), this.cart);
    }

    public void removeFromCart(int position){
        this.cart.removeOrderDetail(position);
        DataHelper.userDatabase.updateCart(this.getUserId(), this.cart);
    }

    //
    // ORDER FUNCTIONS
    //
    public void addOrder(Order order){
        this.orderHistory.add(order);
        this.userOrdersList.add(order.getId());
    }
}
