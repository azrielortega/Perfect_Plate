package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class User extends FirebaseUser{
    private ArrayList<Order> orderHistory;

    public User(){}

    public User(FirebaseUser user){
        super();

        setUserId(user.getUserId());

        setFullName(user.getFullName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setAddress(user.getAddress());

        if (user.checkAdmin())
            makeAdmin();

        setUserOrdersList(user.getUserOrdersList());

        initializeOrdersList();
    }

    public User(String fullName, String email, String  password, Address address){
        super(fullName, email, password, address);

        this.orderHistory = new ArrayList<Order>();
    }

    public User(String fullName, String email, String  password, Address address, boolean isAdmin){
        super(fullName, email, password, address, isAdmin);

        this.orderHistory = new ArrayList<Order>();
    }

    private void initializeOrdersList(){
//        BookDatabase bookDatabase = new BookDatabase();
//
//        this.faveBooks = bookDatabase.findBooks(getFaveBooksList());
//
//        DataHelper.userDatabase.updateFaveBooks(getUserId(), getFaveBooksList(), getFaveBooksCount());
    }

//    //add user recipe to current list of recipes
//    public void addUserRecipe(Book book){
//        //add to local lists
//        this.userBooks.add(book);
//        addUserOrderId(book.getId());
//    }

//    /**
//     * Removes user recipe from User and FirebaseUser
//     */
//    public void removeUserRecipe(String id){
//        int removeIndex = 0;
//
//        for (int i = 0; i < getUserRecipesCount(); i++){
//            if (this.userBooks.get(i).getId().equals(id)){
//                removeIndex = i;
//            }
//        }
//
//        removeUserRecipeId(id);
//        this.userBooks.remove(removeIndex);
//    }

//    public ArrayList<Book> getUserRecipes(){
//        return this.userBooks;
//    }

    public FirebaseUser getFirebaseUser(){
        return duplicateUser();
    }
}
