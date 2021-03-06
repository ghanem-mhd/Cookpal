package com.example.cookpal.recipesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cookpal.R;
import com.example.cookpal.followRecipe.FollowRecipe;
import com.squareup.picasso.Picasso;

public class RecipeOverview extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private TabLayout tabLayout;
    private ImageView recipeImage;
    private FragmentManager fragmentManager;
    private boolean isIngredientsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_overview);

        tabLayout = findViewById(R.id.tab);
        tabLayout.addOnTabSelectedListener(this);
        recipeImage = findViewById(R.id.img);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment, new RecipeIngredientsFragment()).commit();
        isIngredientsSelected = true;

        setupToolbar();


        Picasso.get()
                .load(R.drawable.hummus)
                .resize(900, 600)
                .into(recipeImage);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hummus Recipe");
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

        if (id == R.id.action_start_cooking) {
            Intent intent = new Intent(this, FollowRecipe.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_overview, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if (tab.getText().equals("Ingredients") && !isIngredientsSelected) {

            System.out.println("TEST 1");
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, new RecipeIngredientsFragment())
                    .addToBackStack(null)
                    .commit();
            isIngredientsSelected = true;

        } else if (tab.getText().equals("Steps") && isIngredientsSelected) {

            System.out.println("TEST 2");
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, new RecipeStepsFragment())
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
