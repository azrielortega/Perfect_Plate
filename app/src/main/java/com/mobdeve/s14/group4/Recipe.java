package com.mobdeve.s14.group4;

import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class Recipe extends FirebaseRecipe
{
    private User contributor;
    private ArrayList<Ingredient> ingredientDetailsList;
    private ArrayList<Review> reviewList;

    public Recipe() {
        super();
        this.ingredientDetailsList = new ArrayList<Ingredient>();
        this.reviewList = new ArrayList<Review>();
    }

    public Recipe(FirebaseRecipe firebaseRecipe){
        setId(firebaseRecipe.getId());

        setRecipePic(firebaseRecipe.getRecipePic());
        setRecipeName(firebaseRecipe.getRecipeName());
        setDescription(firebaseRecipe.getDescription());

        setFaveCount(firebaseRecipe.getFaveCount());
        setRating(firebaseRecipe.getRating());
        setReviewCount(firebaseRecipe.getReviewCount());

        setContributorId(firebaseRecipe.getContributorId());
        contributor = DataHelper.userDatabase.findUser(getContributorId());

        setCookingTime(firebaseRecipe.getCookingTime());
        setPrepTime(firebaseRecipe.getPrepTime());
        setServings(firebaseRecipe.getServings());

        setCategory(firebaseRecipe.getCategory());
        setDifficulty(firebaseRecipe.getDifficulty());

        setIngredientsList(firebaseRecipe.getIngredientsList());
        setStepsList(firebaseRecipe.getStepsList());
        setUploadImage(firebaseRecipe.getUploadImage());
        setReviewList(firebaseRecipe.getReviewArrayList());

        this.ingredientDetailsList = DataHelper.ingredientDatabase.findIngredients(firebaseRecipe.getIngredientsList());
        this.reviewList = DataHelper.reviewDatabase.findReviews(firebaseRecipe.getIngredientsList());
    }

    //with id from database
    public Recipe(String id, int recipePic, String recipeName, int foodFave, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
                  String category, String difficulty, ArrayList<String> ingredientList, ArrayList<String> stepsList, UploadImage image, ArrayList<String> reviews){
        super(id, recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty, ingredientList, stepsList, image, reviews);
    }

    public int getContributorPic(){
        if (contributor != null){
            return contributor.getUserPic();
        }

        return R.drawable.person_gray;
    }

    public String getContributorName(){
        if (contributor != null){
            return contributor.getUsername();
        }

        return "Guest";
    }

    public String getRatingString() {
        return String.valueOf(getRating());
    }

    public ArrayList<Ingredient> getIngredientDetailsList(){
        return this.ingredientDetailsList;
    }

    public ArrayList<Review> getReviewList(){
        return this.reviewList;
    }

    public FirebaseRecipe getFirebaseRecipe(){
        FirebaseRecipe firebaseRecipe = new FirebaseRecipe();

        firebaseRecipe.setId(getId());

        firebaseRecipe.setRecipePic(getRecipePic());
        firebaseRecipe.setRecipeName(getRecipeName());
        firebaseRecipe.setDescription(getDescription());

        firebaseRecipe.setContributorId(getContributorId());

        firebaseRecipe.setFaveCount(getFaveCount());
        firebaseRecipe.setRating(getRating());
        firebaseRecipe.setReviewCount(getReviewCount());

        firebaseRecipe.setIngredientsList(getIngredientsList());
        firebaseRecipe.setStepsList(getStepsList());
        firebaseRecipe.setReviewList(getReviewArrayList());

        firebaseRecipe.setCookingTime(getCookingTime());
        firebaseRecipe.setPrepTime(getPrepTime());
        firebaseRecipe.setServings(getServings());

        firebaseRecipe.setCategory(getCategory());
        firebaseRecipe.setDifficulty(getDifficulty());

        firebaseRecipe.setUploadImage(getUploadImage());

        Log.d("myTag", String.valueOf(firebaseRecipe));

        return firebaseRecipe;
    }

    public void addIngredient(Ingredient ingredient){
        Log.d("INRECIPEADDING", "INRECIPEADDING");

        //get ingredient from db
        String id = DataHelper.ingredientDatabase.addIngredient(ingredient);

        //add ingredient to both lists
        ingredient.setId(id);
        this.ingredientDetailsList.add(ingredient);
        addIngredientId(id);
    }

    public void addReview(Review review){
        Log.d("INRECIPEADDREVIEW", "INRECIPEADDREVIEW");
        //get ingredient from db
        String id = DataHelper.reviewDatabase.addReview(review);

        //add reviews to both lists
        review.setId(id);
        this.reviewList.add(review);
        addReviewId(id);
    }

}