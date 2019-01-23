package com.example.cookpal.recipesList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder> {

    private Context context;
    private List<Recipe> recipeList;


    public RecipesAdapter(Context context,List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        return new RecipesAdapter.RecipesAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapterViewHolder recipesAdapterViewHolder, int i) {
        Recipe recipe = recipeList.get(i);


        recipesAdapterViewHolder.title.setText(recipe.title);
        recipesAdapterViewHolder.time.setText(recipe.time);
        recipesAdapterViewHolder.authorName.setText(recipe.authorName);

        if (recipe.cat1 == null){
            recipesAdapterViewHolder.cat1.setVisibility(View.GONE);
        }else {
            recipesAdapterViewHolder.cat1.setVisibility(View.VISIBLE);
            recipesAdapterViewHolder.cat1.setText(recipe.cat1);
        }

        if (recipe.cat2 == null){
            recipesAdapterViewHolder.cat2.setVisibility(View.GONE);
        }else {
            recipesAdapterViewHolder.cat2.setVisibility(View.VISIBLE);
            recipesAdapterViewHolder.cat2.setText(recipe.cat2);
        }


        if (recipe.cat3 == null){
            recipesAdapterViewHolder.cat3.setVisibility(View.GONE);
        }else {
            recipesAdapterViewHolder.cat3.setVisibility(View.VISIBLE);
            recipesAdapterViewHolder.cat3.setText(recipe.cat3);
        }

        Picasso.get()
                .load(recipe.imageId)
                .resize(900, 600)
                .centerCrop()
                .into(recipesAdapterViewHolder.image);

        Picasso.get()
                .load(recipe.authorImageId)
                .into(recipesAdapterViewHolder.authorImage);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipesAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView title,authorName;
        ImageView image,authorImage;
        TextView time;
        TextView cat1,cat2,cat3;

        public RecipesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.recipe_item_title);
            image = itemView.findViewById(R.id.recipe_item_image);
            time = itemView.findViewById(R.id.recipe_item_time);

            cat1 = itemView.findViewById(R.id.recipe_item_cat1);
            cat2 = itemView.findViewById(R.id.recipe_item_cat2);
            cat3 = itemView.findViewById(R.id.recipe_item_cat3);

            authorImage = itemView.findViewById(R.id.recipe_item_author_image);
            authorName = itemView.findViewById(R.id.recipe_item_author_name);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("On click Action");
                    Intent intent = new Intent(context, RecipeOverview.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
