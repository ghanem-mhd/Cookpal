package com.example.cookpal.recipesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.utilities.RecipeFactory;

public class RecipeIngredientsFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView list = (RecyclerView) getView().findViewById(R.id.list);

        RecipeDetails recipe = RecipeFactory.getSalmonRecipe();
        RecyclerView.Adapter adapter = new RecipeIngredientAdapter(recipe.getIngredients());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        list.setHasFixedSize(true);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }


}
