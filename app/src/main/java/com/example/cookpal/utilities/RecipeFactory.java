package com.example.cookpal.utilities;

import com.example.cookpal.models.Recipe;
import com.example.cookpal.models.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

public class RecipeFactory {

    /**
     * Creates a fake salmon recipe.
     * @return
     */
    public static RecipeDetails getSalmonRecipe(){

        Recipe recipe = new Recipe();
        recipe.setAuthorName("Alex");
        recipe.setTitle("Salmon Roasted in Butter");

        List<RecipeDetails.Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new RecipeDetails.Ingredient("Salt", "1/2 Teaspoon"));
        ingredients.add(new RecipeDetails.Ingredient("Lemon juice", "1 Tablespoon"));
        ingredients.add(new RecipeDetails.Ingredient("Chickpeas", "100 g"));
        ingredients.add(new RecipeDetails.Ingredient("Tahini", "1/2 Tablespoon"));
        ingredients.add(new RecipeDetails.Ingredient("Oil", "3 Tablespoon"));

        List<RecipeDetails.Step> steps = new ArrayList<>();
        steps.add(new RecipeDetails.Step(1, "Hello I am your cookpal assistance, I will guide you through the cooking process if you want to hear further instruction please say next step command", 0));
        steps.add(new RecipeDetails.Step(2, "Add half a tablespoon of tahini into the blender", 0));
        steps.add(new RecipeDetails.Step(3, "Add 1 tablespoon of lemon juice into the blender",0));
        steps.add(new RecipeDetails.Step(4, "Process the mixture for 20 seconds", 0));
        steps.add(new RecipeDetails.Step(5, "Add 3 tablespoons of oil into the blender", 0));
        steps.add(new RecipeDetails.Step(6, "Add half a teaspoon of salt into the blender", 0));
        steps.add(new RecipeDetails.Step(7, "Add 100 g of chickpeas into the blender", 0));
        steps.add(new RecipeDetails.Step(8, "Process the mixture for 20-30 seconds", 0));
        return new RecipeDetails(recipe, ingredients, steps);
    }
}
