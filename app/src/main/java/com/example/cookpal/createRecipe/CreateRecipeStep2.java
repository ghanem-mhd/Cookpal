package com.example.cookpal.createRecipe;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.camerakit.CameraKitView;
import com.example.cookpal.R;
import com.example.cookpal.models.Message;
import com.example.cookpal.models.User;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.mapzen.speakerbox.Speakerbox;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CreateRecipeStep2 extends AppCompatActivity {

    private static final String TAG = CreateRecipeStep2.class.getSimpleName();

    private CameraKitView cameraKitView;
    private RecognitionProgressView recognitionProgressView;
    private Speakerbox speakerbox;
    private MessagesList messagesList;
    protected ImageLoader imageLoader;
    protected MessagesListAdapter<Message> messagesAdapter;

    private User app, user;

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_step2);

        speakerbox = new Speakerbox(getApplication());

        app = new User("1", "Cookpal", "1", true);
        user = new User("0", "Wei", "0", true);

        findViews();

        setUpViews();

        setupToolbar();

        playSound(1,1);

        playSound(2,6);

        playSound(3,12);

        displayUserMessage("Okay then",17);

        playSound(4,30);

        displayUserMessage("Half tablespoon",34);

        playSound(5,50);

        displayUserMessage("1 tablespoon",54);

        playSound(6,60);

        playSound(7,75);

        displayUserMessage("3 tablespoon",79);

        playSound(8,95);

        playSound(9,105);

        displayUserMessage("Half teaspoon",104);

        playSound(10,120);

        displayUserMessage("Yes it is cooked",124);

        playSound(11,127);

        displayUserMessage("100 gram",130);

        playSound(13,140);

        displayUserMessage("two tablespoon, Mark this step as optional",143);

        playSound(14,148);

        playSound(9,155);

        displayUserMessage("I am done cooking this recipe",175);

        playSound(15,179);

        playSound(16,185);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CreateRecipeStep3.open(CreateRecipeStep2.this.getApplicationContext());
            }
        }, 1000 * 188);
    }

    private void playSound(final int sentenceID, int delayInSeconds) {
        final String sentence = appSentences[sentenceID];
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayAppMessage(sentence);
                mPlayer = MediaPlayer.create(getApplicationContext(), appSentencesSounds[sentenceID]);
                mPlayer.start();
            }
        }, 1000 * delayInSeconds);
    }

    private void displayUserMessage(final String messageString, int delayInSeconds){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayUserMessage(messageString);
            }
        }, 1000 * delayInSeconds);
    }

    private void displayAppMessage(String messageString){
        Message message = new Message(Long.toString(UUID.randomUUID().getLeastSignificantBits()), app, messageString);
        messagesAdapter.addToStart(message, true);
    }

    private void displayUserMessage(String messageString){
        Message message = new Message(Long.toString(UUID.randomUUID().getLeastSignificantBits()), user, messageString);
        messagesAdapter.addToStart(message, true);
    }


    private void findViews() {
        messagesList = findViewById(R.id.messagesList);
        recognitionProgressView = findViewById(R.id.recognition_view);
        cameraKitView = findViewById(R.id.camera);
    }

    private void setUpViews() {
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                if (url.equals("1")) {
                    imageView.setImageResource(R.drawable.test);
                } else {
                    imageView.setImageResource(R.drawable.user2);
                }
            }
        };

        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };
        recognitionProgressView.setColors(colors);
        recognitionProgressView.play();

        messagesAdapter = new MessagesListAdapter<>("0", imageLoader);
        messagesList.setAdapter(messagesAdapter);
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, CreateRecipeStep2.class);
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
        speakerbox.stop();
        if (mPlayer != null){
            mPlayer.stop();
        }
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Recording Hummus");
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

        if (id == R.id.action_finish) {
            CreateRecipeStep3.open(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_recipe_2_menu, menu);
        return true;
    }


    private void appSays(final String sentence, int delayInSeconds) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayAppMessage(sentence);
                speakerbox.play(sentence);
            }
        }, 1000 * delayInSeconds);
    }

    private void userSays(final String sentence, int delayInSeconds) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            displayUserMessage(sentence);
            }
        }, 1000 * delayInSeconds);
    }

    String[] appSentences = new String[]{"",
            "Hello I am your cookpal assistance, I will help you to create Hummus recipe",
            "Put your phone in a way where i can see what you are doing",
            "No not this way try to put your phone to 90 degree angle",
            "You added tahini into the blender, how much did you add?",
            "You added lemon into the blender, how much did you add?",
            "You processed the mixture for 20 seconds",
            "You added olive oil into the blender, how much did you add?",
            "You added salt into the blender, how much did you add?",
            "You processed the mixture for 20 seconds",
            "You added chickpeas into the blender, is this chickpeas cooked?",
            "okay, how much did you add",
            "You processed the mixture for 20 seconds",
            "You added water to the mixture, how much did you add?",
            "Okay last step marked as optional",
            "Okay I'm generating the recipe",
            "Hummus recipe is ready"};

    int [] appSentencesSounds = new int []{0,R.raw.s1,R.raw.s2,R.raw.s3,R.raw.s4,R.raw.s5,R.raw.s6,R.raw.s7,R.raw.s8,
            R.raw.s9,R.raw.s10,R.raw.s11,R.raw.s12,R.raw.s13,R.raw.s14,R.raw.s15,R.raw.s16};
}
