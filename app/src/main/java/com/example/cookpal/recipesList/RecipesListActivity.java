package com.example.cookpal.recipesList;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.cookpal.R;
import com.example.cookpal.createRecipe.CreateRecipeStep1;
import com.example.cookpal.createRecipe.CreateRecipeStep2;
import com.example.cookpal.models.Recipe;
import com.example.cookpal.utilities.EqualSpacingItemDecoration;
import com.example.cookpal.utilities.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class RecipesListActivity extends AppCompatActivity {

    RecyclerView recipesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        findViews();

        setupToolbar();

        setUpRecyclerView();
    }

    private void findViews() {
        recipesRecyclerView = (RecyclerView) findViewById(R.id.activity_recipes_list_rv);
    }

    public void AddButtonClick(View view) {
        CreateRecipeStep1.open(this);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipes_list_menu, menu);
        return true;
    }

    private void setUpRecyclerView() {
        List<Recipe> recipes = new ArrayList<>();

        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Salmon Roasted in Butter");
        recipe1.setImageId(R.drawable.recipe1);
        recipe1.setTime("15 minutes");
        recipe1.setCat1("Fish");
        recipe1.setCat2("Easy");
        recipe1.setCat3("Dinner");
        recipe1.setAuthorImageId(R.drawable.user1);
        recipe1.setAuthorName("Created by Alex");
        recipes.add(recipe1);


        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Slow Cooker Chicken Thighs");
        recipe2.setImageId(R.drawable.recipe2);
        recipe2.setTime("20 minutes");
        recipe2.setCat1("Chicken");
        recipe2.setCat2("Dinner");
        recipe2.setAuthorImageId(R.drawable.user2);
        recipe2.setAuthorName("Created by Mhd");
        //recipes.add(recipe2);


        Recipe recipe3 = new Recipe();
        recipe3.setTitle("Hummus");
        recipe3.setImageId(R.drawable.hummus);
        recipe3.setTime("10 minutes");
        recipe3.setCat1("Side Dish");
        recipe3.setCat2("Breakfast");
        recipe3.setAuthorImageId(R.drawable.user3);
        recipe3.setAuthorName("Created by Anxh");
        recipes.add(recipe3);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecipesAdapter recipesAdapter = new RecipesAdapter(this,recipes);

        recipesRecyclerView.addItemDecoration(new EqualSpacingItemDecoration(ScreenUtils.convertDpToPixel(8f, this), EqualSpacingItemDecoration.VERTICAL));
        recipesRecyclerView.setLayoutManager(linearLayoutManager);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }
}
