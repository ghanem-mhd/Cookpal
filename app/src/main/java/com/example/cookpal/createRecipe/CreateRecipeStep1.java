package com.example.cookpal.createRecipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cookpal.R;

public class CreateRecipeStep1 extends AppCompatActivity {

    public static void open(Context context){
        Intent intent = new Intent(context,CreateRecipeStep1.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_step1);
    }
}
