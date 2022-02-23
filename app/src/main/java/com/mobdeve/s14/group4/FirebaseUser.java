package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class FirebaseUser {
    private String userId;

    private String fullName;
    private String email;
    private String password;
    private Address address;

    private boolean isAdmin;
    private ArrayList<String> userOrdersList;


    public FirebaseUser(){
        this.isAdmin = false;
        this.userOrdersList = new ArrayList<String>();
    }

    public FirebaseUser(String fullName, String email, String  password, Address address){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;

        this.isAdmin = false;
        this.userOrdersList = new ArrayList<String>();
    }

    public FirebaseUser(String fullName, String email, String  password, Address address, boolean isAdmin){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;

        this.isAdmin = isAdmin;
        this.userOrdersList = new ArrayList<String>();
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

    public boolean checkAdmin() {
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

    public void makeAdmin(){
        this.isAdmin = true;
    }

    public void removeAdmin() { this.isAdmin = false; }

    public void setUserOrdersList(ArrayList<String> userOrdersList) {
        this.userOrdersList = userOrdersList;
    }

    public FirebaseUser duplicateUser(){
        FirebaseUser user = new FirebaseUser();

        user.setUserId(this.userId);

        user.setFullName(this.fullName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setAddress(this.address);

        if (this.checkAdmin()){
            user.makeAdmin();
        }

        user.setUserOrdersList(this.userOrdersList);

        return user;
    }
}
