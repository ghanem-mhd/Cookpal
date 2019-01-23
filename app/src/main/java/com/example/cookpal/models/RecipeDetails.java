package com.example.cookpal.models;

import java.util.List;

/**
 * Extension for Recipe
 */
public class RecipeDetails {

    private List<Ingredient> ingredients;
    private List<Step> steps;
    private final Recipe recipe;

    public RecipeDetails(Recipe recipe){
        this.recipe = recipe;
    }

    public RecipeDetails(Recipe recipe, List<Ingredient> ingredients, List<Step> steps){
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * A class representing ingredients
     */
    public static class Ingredient{
        private String ingredient;
        private String quantity;

        public Ingredient(){}

        public Ingredient(String ing, String qty){
            this.ingredient = ing;
            this.quantity = qty;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

    /**
     * Class representing a step;
     */
    public static class Step{
        private int number;
        private String description;
        private int videoResource;

        public Step(){}

        public Step(int num, String desc, int vidRes){
            this.number = num;
            this.description = desc;
            this.videoResource = vidRes;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getVideoResource() {
            return videoResource;
        }

        public void setVideoResource(int videoResource) {
            this.videoResource = videoResource;
        }
    }
}
