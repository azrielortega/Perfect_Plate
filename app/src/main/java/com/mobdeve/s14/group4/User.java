package com.mobdeve.s14.group4;

public class User {
    private String email;
    private String password;
    private String username;
    private String fname;
    private String lname;


    public User(String email, String  password, String username, String fname, String lname){
        this.email = email;
        this.password = password;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
    }

    public User(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

}
