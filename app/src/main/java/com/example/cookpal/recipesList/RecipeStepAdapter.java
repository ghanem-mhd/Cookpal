package com.example.cookpal.recipesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.cookpal.R;
import com.example.cookpal.models.RecipeDetails;

import java.util.List;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder>{

    private List<RecipeDetails.Step> steps;

    public RecipeStepAdapter(List<RecipeDetails.Step> steps){
        this.steps = steps;
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
        recipeStepViewHolder.number.setText(step.getNumber());
        recipeStepViewHolder.step2.setText(step.getDescription());
        recipeStepViewHolder.number2.setText(step.getNumber());
        //Todo: set video resource for the snippet
        // recipeStepViewHolder.snippet
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecipeStepViewHolder extends RecyclerView.ViewHolder{

        private TextView number, step, number2, step2;
        private ImageButton arrow, arrowUp;
        private VideoView snippet;
        private LinearLayout expanded;

        private View.OnClickListener arrowDownEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setVisibility(View.GONE);
                step.setVisibility(View.GONE);
                arrow.setVisibility(View.GONE);

                expanded.setVisibility(View.VISIBLE);
            }
        };

        private View.OnClickListener arrowUpEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setVisibility(View.VISIBLE);
                step.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.VISIBLE);

                expanded.setVisibility(View.GONE);
            }
        };


        public RecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            number = (TextView)itemView.findViewById(R.id.number);
            step = (TextView)itemView.findViewById(R.id.step);
            arrow = (ImageButton)itemView.findViewById(R.id.arrow);

            expanded = itemView.findViewById(R.id.step_content);
            number2 = itemView.findViewById(R.id.number2);
            step2 = itemView.findViewById(R.id.step2);
            arrowUp = itemView.findViewById(R.id.arrow2);
            snippet = itemView.findViewById(R.id.snippet);

            arrow.setOnClickListener(arrowDownEvent);
            arrowUp.setOnClickListener(arrowUpEvent);

        }


    }
}
