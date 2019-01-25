package com.example.cookpal.followRecipe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.cookpal.R;
import com.example.cookpal.createRecipe.CreateRecipeStep2;
import com.example.cookpal.models.Message;
import com.example.cookpal.models.RecipeDetails;
import com.example.cookpal.utilities.RecipeFactory;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.UUID;


public class FollowRecipe extends AppCompatActivity implements OnSeekChangeListener {

    private SpeechRecognizer speechRecognizer;
    private IndicatorSeekBar seekBar;
    private TextView stepDescription;
    private RecipeDetails recipe;
    private MediaPlayer mPlayer;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_recipe);

        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekChangeListener(this);
        stepDescription = findViewById(R.id.step_desc);
        icon = findViewById(R.id.icon);

        recipe = RecipeFactory.getSalmonRecipe();

        stepDescription.setText(recipe.getSteps().get(0).getDescription());

        initSpeechAnimation();

        setupToolbar();

        playSound(R.raw.ss1,1,1);

        playSound(R.raw.ss2,10,2);

        playSound(R.raw.ss3,25,3);

        playSound(R.raw.ss4,40,4);

        playSound(R.raw.ss5,55,8);

        playVideo(57);
    }

    private void playVideo(int delayInSeconds) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                icon.setVisibility(View.GONE);
                VideoView videoView = findViewById(R.id.snippet);
                Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video1);
                videoView.setVideoURI(uri);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setVolume(0,0);
                    }
                });
                videoView.start();
            }
        }, 1000 * delayInSeconds);
    }

    private void initSpeechAnimation(){
        RecognitionProgressView recognitionProgressView = (RecognitionProgressView) findViewById(R.id.recognition_view);
        recognitionProgressView.play();
        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };
        recognitionProgressView.setColors(colors);
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
            actionBar.setTitle("Hummus Recipe steps");
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

    private void playSound(final int sentenceID, final int delayInSeconds, final int step) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (step != -0)
                    seekBar.setProgress(step);
                mPlayer = MediaPlayer.create(getApplicationContext(), sentenceID);
                mPlayer.start();
            }
        }, 1000 * delayInSeconds);
    }
}
