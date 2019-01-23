package com.example.cookpal.createRecipe;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.camerakit.CameraKitView;
import com.example.cookpal.R;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;
import com.mapzen.speakerbox.Speakerbox;
import com.maxwell.speechrecognition.OnSpeechRecognitionListener;
import com.maxwell.speechrecognition.OnSpeechRecognitionPermissionListener;
import com.maxwell.speechrecognition.SpeechRecognition;
import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;

import java.util.List;

public class CreateRecipeStep2 extends AppCompatActivity implements OnDSListener, OnSpeechRecognitionPermissionListener, OnSpeechRecognitionListener {

    private static final String TAG = CreateRecipeStep2.class.getSimpleName();

    private CameraKitView cameraKitView;
    private RecognitionProgressView recognitionProgressView;
    private DroidSpeech droidSpeech;
    private Speakerbox speakerbox;
    private  SpeechRecognition speechRecognition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_step2);

        recognitionProgressView = findViewById(R.id.recognition_view);
        cameraKitView = findViewById(R.id.camera);

        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };

        recognitionProgressView.setColors(colors);

        setupToolbar();

        startListening();

        speakerbox = new Speakerbox(getApplication());

        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //audio.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    private void startListening() {
        recognitionProgressView.play();
        droidSpeech = new DroidSpeech(this, null);
        droidSpeech.setOnDroidSpeechListener(this);
        droidSpeech.startDroidSpeechRecognition();

        speechRecognition = new SpeechRecognition(this);
        speechRecognition.setSpeechRecognitionPermissionListener(this);
        speechRecognition.setSpeechRecognitionListener(this);
        //speechRecognition.startSpeechRecognition();
    }

    public static void open(Context context){
        Intent intent = new Intent(context,CreateRecipeStep2.class);
        context.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        droidSpeech.closeDroidSpeechOperations();
        speechRecognition.stopSpeechRecognition();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Recording");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if ( id == android.R.id.home ) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_recipe_2_menu, menu);
        return true;
    }

    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages) {

    }

    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue) {

    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult) {
        //System.out.println("CreateRecipeStep2.onDroidSpeechLiveResult  " + liveSpeechResult);
    }

    @Override
    public void onDroidSpeechFinalResult(String finalSpeechResult) {
        System.out.println("CreateRecipeStep2.onDroidSpeechFinalResult " + finalSpeechResult);
        speakerbox.play(finalSpeechResult);
    }

    @Override
    public void onDroidSpeechClosedByUser() {

    }

    @Override
    public void onDroidSpeechError(String errorMsg) {
        System.out.println("CreateRecipeStep2.onDroidSpeechError " + errorMsg);
    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void OnSpeechRecognitionStarted() {

    }

    @Override
    public void OnSpeechRecognitionStopped() {

    }

    @Override
    public void OnSpeechRecognitionFinalResult(String s) {
        System.out.println("CreateRecipeStep2.OnSpeechRecognitionFinalResult " + s);
        speakerbox.play(s);
    }

    @Override
    public void OnSpeechRecognitionCurrentResult(String s) {

    }

    @Override
    public void OnSpeechRecognitionError(int i, String s) {
        System.out.println("CreateRecipeStep2.OnSpeechRecognitionError " + s);
    }
}
