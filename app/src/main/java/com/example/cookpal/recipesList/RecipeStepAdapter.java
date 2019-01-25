package com.example.cookpal.recipesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;

import java.util.List;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder>{

    private List<RecipeDetails.Step> steps;
    private boolean isEditable = false;

    public RecipeStepAdapter(List<RecipeDetails.Step> steps, boolean isEditable){
        this.steps = steps;
        this.isEditable = isEditable;
    }

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_step_item, viewGroup, false);
        return new RecipeStepViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder recipeStepViewHolder, int i) {
        RecipeDetails.Step step = steps.get(i);
        recipeStepViewHolder.step.setText(step.getDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class RecipeStepViewHolder extends RecyclerView.ViewHolder{

        private TextView number, step;
        private ImageButton arrow;
        private VideoView snippet;

        private boolean isArrowDown = true;

        private View.OnClickListener arrowEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isArrowDown){
                    snippet.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    isArrowDown = false;
                }else{
                    snippet.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    isArrowDown = true;
                }

            }
        };

        public RecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            step = (TextView)itemView.findViewById(R.id.step);
            arrow = (ImageButton)itemView.findViewById(R.id.arr);

            snippet = itemView.findViewById(R.id.snippet);
            arrow.setOnClickListener(arrowEvent);
        }
    }
}
