package com.example.cookpal.createRecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.followRecipe.FollowRecipe;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.recipesList.RecipeIngredientsFragment;
import com.example.cookpal.recipesList.RecipeStepsFragment;
import com.example.cookpal.utilities.RecipeFactory;
import com.squareup.picasso.Picasso;

public class CreateRecipeStep3  extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private TabLayout tabLayout;
    private RecipeDetails recipe;

    private FragmentManager fragmentManager;
    private boolean isIngredientsSelected;

    public static void open(Context context){
        Intent intent = new Intent(context,CreateRecipeStep3.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_step3);

        tabLayout = findViewById(R.id.tab);
        tabLayout.addOnTabSelectedListener(this);
        recipe = RecipeFactory.getSalmonRecipe();

        fragmentManager = getSupportFragmentManager();
        RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
        fragment.isEditable = true;
        fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();
        isIngredientsSelected = true;

        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hummus Overview");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.action_start_cooking){
            Intent intent = new Intent(this, FollowRecipe.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.step3, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if(tab.getText().equals("Ingredients") && !isIngredientsSelected){

            RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
            fragment.isEditable = true;
            System.out.println("TEST 1");
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
            isIngredientsSelected = true;

        }else if(tab.getText().equals("Steps") && isIngredientsSelected){

            System.out.println("TEST 2");

            RecipeStepsFragment fragment = new RecipeStepsFragment();
            fragment.isEditable = true;
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
            isIngredientsSelected = false;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
