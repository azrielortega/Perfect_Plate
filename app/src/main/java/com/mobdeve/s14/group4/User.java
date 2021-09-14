package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class User extends FirebaseUser{
    private ArrayList<Recipe> userRecipes; //TODO: get recipes from db
    private ArrayList<Recipe> faveRecipes; //TODO: get recipes from db

    public User(){}

    public User(FirebaseUser user){
        user.setUserId(getUserId());

        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setPassword(getPassword());

        user.setUserPic(getUserPic());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());

        user.setGoogleId(getGoogleId());

        user.setUserRecipesList(getUserRecipesList());
        user.setFaveRecipesList(getFaveRecipesList());

        this.userRecipes = new ArrayList<Recipe>();
        this.faveRecipes = new ArrayList<Recipe>();
    }

    public User(String id, String fName, String lName){
        super(id, fName, lName);
    }

    public User(String email, String  password, String username, String firstName, String lastName){
        super(email, password, username, firstName, lastName);

        this.userRecipes = new ArrayList<Recipe>();
        this.faveRecipes = new ArrayList<Recipe>();
    }

    public User(String email, String  password, String username, String firstName, String lastName, String birthday){
        super(email, password, username, firstName, lastName, birthday);

        this.userRecipes = new ArrayList<Recipe>();
        this.faveRecipes = new ArrayList<Recipe>();
    }

    public User(String googleId, String username, String firstName, String lastName){
        super(googleId, username, firstName, lastName);

        this.userRecipes = new ArrayList<Recipe>();
        this.faveRecipes = new ArrayList<Recipe>();
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    //add user recipe to current list of recipes
    public void addUserRecipe(Recipe recipe){
        //add to local lists
        this.userRecipes.add(recipe);
        addUserRecipeId(recipe.getId());
    }

    //TODO: add recipe to db list
    public void addFaveRecipe(Recipe recipe){
        this.faveRecipes.add(recipe);
        addFaveRecipeId(recipe.getId());

        // add to db
    }

    //TODO: remove from db list
    public void removeUserRecipe(Recipe recipe){
        int removeIndex = 0;

        for (int i = 0; i < getUserRecipesCount(); i++){
            if (this.userRecipes.get(i).getId().equals(recipe.getId())){
                removeIndex = i;
            }
        }

        removeUserRecipeId(recipe.getId());
        this.userRecipes.remove(removeIndex);
    }

    //TODO: remove from db list
    public void removeFaveRecipe(Recipe recipe){
        int removeIndex = 0;

        for (int i = 0; i < getUserRecipesCount(); i++){
            if (this.faveRecipes.get(i).getId().equals(recipe.getId())){
                removeIndex = i;
            }
        }

        removeFaveRecipeId(recipe.getId());
        this.faveRecipes.remove(removeIndex);
    }

    public ArrayList<Recipe> getUserRecipes(){
        return this.userRecipes;
    }

    public ArrayList<Recipe> getFaveRecipes(){
        return this.faveRecipes;
    }

    public FirebaseUser getFirebaseUser(){
        return duplicateUser();
    }
}
