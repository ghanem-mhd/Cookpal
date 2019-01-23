package com.example.cookpal.recipesList;

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
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.utilities.RecipeFactory;
import com.squareup.picasso.Picasso;

public class RecipeOverview extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener, View.OnClickListener {

    private Button btnStartCooking;
    private TabLayout tabLayout;
    private TextView title;
    private RecipeDetails recipe;
    private ImageView recipeImage;

    private FragmentManager fragmentManager;
    private boolean isIngredientsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_overview);

        btnStartCooking = findViewById(R.id.btn_start_cooking);
        tabLayout = findViewById(R.id.tab);
        tabLayout.addOnTabSelectedListener(this);
        title = findViewById(R.id.title);
        recipeImage = findViewById(R.id.img);
        recipe = RecipeFactory.getSalmonRecipe();
        title.setText(recipe.getRecipe().title);
        btnStartCooking.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment, new RecipeIngredientsFragment()).commit();
        isIngredientsSelected = true;

        setupToolbar();


        Picasso.get()
                .load(R.drawable.recipe1)
                .resize(900,600)
                .into(recipeImage);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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
            //Todo: navigate to another view
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

        if(tab.getText().equals("Ingredients") && !isIngredientsSelected){

            System.out.println("TEST 1");
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, new RecipeIngredientsFragment())
                    .addToBackStack(null)
                    .commit();
            isIngredientsSelected = true;

        }else if(tab.getText().equals("Steps") && isIngredientsSelected){

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

    @Override
    public void onClick(View view) {

    }
}
