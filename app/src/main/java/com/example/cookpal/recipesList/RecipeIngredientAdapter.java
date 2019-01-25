package com.example.cookpal.recipesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;

import java.util.List;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.RecipeIngredientViewHolder>{

    private List<RecipeDetails.Ingredient> ingredients;
    private boolean isEditable;


    public RecipeIngredientAdapter(List<RecipeDetails.Ingredient> ingredients, boolean isEditable){
        this.ingredients = ingredients;
        this.isEditable = isEditable;
    }

    @NonNull
    @Override
    public RecipeIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_ingredient_item, viewGroup, false);
        return new RecipeIngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientViewHolder recipeIngredientViewHolder, int i) {
        RecipeDetails.Ingredient ingredient = ingredients.get(i);
        recipeIngredientViewHolder.quantity.setText(ingredient.getQuantity());
        recipeIngredientViewHolder.ingredient.setText(ingredient.getIngredient());

        if (isEditable){
            recipeIngredientViewHolder.editIcon.setVisibility(View.VISIBLE);
        }else {
            recipeIngredientViewHolder.editIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class RecipeIngredientViewHolder extends RecyclerView.ViewHolder{

        private TextView quantity, ingredient;
        private ImageView editIcon;

        public RecipeIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = (TextView) itemView.findViewById(R.id.qty);
            ingredient = (TextView) itemView.findViewById(R.id.ingredient);
            editIcon = (ImageView) itemView.findViewById(R.id.edit);
        }
    }
}
