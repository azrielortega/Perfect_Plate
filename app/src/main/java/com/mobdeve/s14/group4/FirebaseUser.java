package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class FirebaseUser {
    private String userId;

    private String email;
    private String password;
    private String username;

    private String firstName;
    private String lastName;

    private String googleId;

    private String birthday;

    private ArrayList<String> userRecipesList;
    private ArrayList<String> faveRecipesList;

    private UploadImage profilePic;


    public FirebaseUser(){
        this.userRecipesList = new ArrayList<String>();
        this.faveRecipesList = new ArrayList<String>();
        this.profilePic = getProfile_Image();
    }

    public FirebaseUser(String id, String fName, String lName){
        this.userId = id;
        this.firstName = fName;
        this.lastName = lName;
    }

    public FirebaseUser(String email, String  password, String username, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        this.userRecipesList = new ArrayList<String>();
        this.faveRecipesList = new ArrayList<String>();
    }

    public FirebaseUser(String email, String  password, String username, String firstName, String lastName, String birthday){
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;

        this.userRecipesList = new ArrayList<String>();
        this.faveRecipesList = new ArrayList<String>();
    }

    public FirebaseUser(String googleId, String username, String firstName, String lastName){
        this.googleId = googleId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        this.userRecipesList = new ArrayList<String>();
        this.faveRecipesList = new ArrayList<String>();
    }

//    public FirebaseUser(String email, String  password, String username, String firstName, String lastName, UploadImage image){
//        this.email = email;
//        this.password = password;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//
//        this.userRecipesList = new ArrayList<String>();
//        this.faveRecipesList = new ArrayList<String>();
//        this.profilePic = image;
//    }

//    public FirebaseUser(String email, String  password, String username, String firstName, String lastName, String birthday, UploadImage image){
//        this.email = email;
//        this.password = password;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthday = birthday;
//
//        this.userRecipesList = new ArrayList<String>();
//        this.faveRecipesList = new ArrayList<String>();
//        this.profilePic = image;
//    }
//    public FirebaseUser(String googleId, String username, String firstName, String lastName, UploadImage upload){
//        this.googleId = googleId;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//
//        this.userRecipesList = new ArrayList<String>();
//        this.faveRecipesList = new ArrayList<String>();
//        this.profilePic = upload;
//    }

    public UploadImage getProfile_Image (){
        return this.profilePic;
    }

    public void setProfile_Image (UploadImage p){
        this.profilePic = p;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday (String b){
        birthday = b;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public int getUserRecipesCount(){
        return this.userRecipesList.size();
    }

    public ArrayList<String> getUserRecipesList(){
        return this.userRecipesList;
    }

    public int getFaveRecipesCount(){
        return this.faveRecipesList.size();
    }

    public ArrayList<String> getFaveRecipesList(){
        return this.faveRecipesList;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        //TODO: apply hash
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setGoogleId(String googleId){
        this.googleId = googleId;
    }

    public void setUserRecipesList(ArrayList<String> recipeList){
        this.userRecipesList = recipeList;
    }

    public void setFaveRecipesList(ArrayList<String> recipeList){
        this.faveRecipesList = recipeList;
    }

    public FirebaseUser duplicateUser(){
        FirebaseUser user = new FirebaseUser();

        user.setUserId(this.userId);

        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);

        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);

        user.setGoogleId(this.googleId);

        user.setUserRecipesList(this.userRecipesList);
        user.setFaveRecipesList(this.faveRecipesList);

        return user;
    }

    public void addUserRecipeId(String id){
        this.userRecipesList.add(id);
    }

    public void addFaveRecipeId(String id){
        this.faveRecipesList.add(id);
    }

    public void removeUserRecipeId(String id){
        int n = getUserRecipesCount();
        int removeIndex = 0;

        for (int i = 0; i < n; i++){
            if (this.userRecipesList.get(i).equals(id)){
                removeIndex = i;
            }
        }

        this.userRecipesList.remove(removeIndex);
    }

    public void removeFaveRecipeId(String id){
        int n = getFaveRecipesCount();
        int removeIndex = 0;

        for (int i = 0; i < n; i++){
            if (this.faveRecipesList.get(i).equals(id)){
                removeIndex = i;
            }
        }

        this.faveRecipesList.remove(removeIndex);
    }
}
