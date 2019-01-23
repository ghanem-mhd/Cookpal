package com.example.cookpal.recipesList;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.utilities.RecipeFactory;

import java.util.List;

public class RecipeOverview extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener, View.OnClickListener {

    private RecyclerView list;
    private Button btnStartCooking;
    private TabLayout tabLayout;
    private TextView title;
    private RecipeDetails recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_overview);
     //   Toolbar toolbar = findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        list = (RecyclerView) findViewById(R.id.list);
        btnStartCooking = (Button) findViewById(R.id.btn_start_cooking);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.addOnTabSelectedListener(this);
        title = (TextView) findViewById(R.id.title);

        recipe = RecipeFactory.getSalmonRecipe();
        title.setText(recipe.getRecipe().title);

        btnStartCooking.setOnClickListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if(tab.getText().equals("Ingredients")){
            List<RecipeDetails.Ingredient> ingredients = recipe.getIngredients();
            list.setAdapter(new RecipeIngredientAdapter(ingredients));

        }else if(tab.getText().equals("Steps")){
            List<RecipeDetails.Step> steps = recipe.getSteps();
            list.setAdapter(new RecipeStepAdapter(steps));
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
        //Todo: navigate to another view
    }
}
