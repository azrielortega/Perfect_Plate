package com.mobdeve.s14.group4;

public class User {
    private String email;
    private String password;

    public User(String e, String p){
        this.email = e;
        this.password = p;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
}
