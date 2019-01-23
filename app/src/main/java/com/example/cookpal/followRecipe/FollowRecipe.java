package com.example.cookpal.followRecipe;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cookpal.R;
import com.example.cookpal.createRecipe.CreateRecipeStep2;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.utilities.RecipeFactory;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;


public class FollowRecipe extends AppCompatActivity implements OnSeekChangeListener {

    private SpeechRecognizer speechRecognizer;
    private IndicatorSeekBar seekBar;
    private TextView stepDescription;
    private RecipeDetails recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_recipe);

        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekChangeListener(this);
        stepDescription = findViewById(R.id.step_desc);

        recipe = RecipeFactory.getSalmonRecipe();

        stepDescription.setText(recipe.getSteps().get(0).getDescription());

        initSpeechAnimation();

        setupToolbar();
    }

    private void initSpeechAnimation(){
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        RecognitionProgressView recognitionProgressView = (RecognitionProgressView) findViewById(R.id.recognition_view);
        recognitionProgressView.setSpeechRecognizer(speechRecognizer);
        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
                ///showResults(results);
            }
        });

        recognitionProgressView.play();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer.startListening(intent);

        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };
        recognitionProgressView.setColors(colors);

        int[] heights = {60, 76, 58, 80, 55};
        recognitionProgressView.setBarMaxHeightsInDp(heights);
    }

    @Override
    public void onSeeking(SeekParams seekParams) {
        System.out.println("seek param: progress:"+seekParams.progress+"; thumb pos:"+seekParams.thumbPosition);
        stepDescription.setText(recipe.getSteps().get(seekParams.progress-1).getDescription());
    }

    @Override
    public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
        System.out.println("started seeking");
    }

    @Override
    public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
        System.out.println("stopped seeking");
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Recipe steps");
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

        if (id == R.id.action_finish){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.following_recipe, menu);
        return true;
    }
}
