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
        ingredients.add(new RecipeDetails.Ingredient("salmon fillet", "0.5 kg"));
        ingredients.add(new RecipeDetails.Ingredient("cloves minced garlic", "3"));
        ingredients.add(new RecipeDetails.Ingredient("lemon juice", "1.5 tbsp"));
        ingredients.add(new RecipeDetails.Ingredient("honey", "1.5 tbsp"));
        ingredients.add(new RecipeDetails.Ingredient("melted butter", "2 tbsp"));
        ingredients.add(new RecipeDetails.Ingredient("cayenne pepper", "3 dashes"));
        ingredients.add(new RecipeDetails.Ingredient("salt", "1/4 tsp"));
        ingredients.add(new RecipeDetails.Ingredient("chopped parseley", "1 tbsp"));
        ingredients.add(new RecipeDetails.Ingredient("lemon", "1 slice"));

        List<RecipeDetails.Step> steps = new ArrayList<>();
        steps.add(new RecipeDetails.Step(1, "Preheat oven to 200° C", 0));
        steps.add(new RecipeDetails.Step(2, "Add garlic, lemon juice, butter, honey and " +
                                                        "spices into a small bowl and whisk to combine",0));
        steps.add(new RecipeDetails.Step(3, "Rinse the salmon with cold water and pat dry with paper towels", 0));
        steps.add(new RecipeDetails.Step(4, "Transfer the salmon to a big sheet of aluminum foil and" +
                                                        " drizzle the garlic lemon butter mixture on the salmon.", 0));
        steps.add(new RecipeDetails.Step(5, "Top with parsley and wrap the salmon up tightly.", 0));
        steps.add(new RecipeDetails.Step(6, "Bake it for 20 minutes, or until the salmon is cooked through.", 0));

        return new RecipeDetails(recipe, ingredients, steps);
    }
}
