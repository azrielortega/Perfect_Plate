package com.mobdeve.s14.group4;

public class OrderDetails {
    private String bookId;
    private int quantity;

    public OrderDetails(){

    }

    public OrderDetails(String bookId, int quantity){
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
