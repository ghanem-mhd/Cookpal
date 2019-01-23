package com.example.cookpal.createRecipe;

import android.content.Context;
import android.content.Intent;
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

    private User app,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_step2);

        speakerbox = new Speakerbox(getApplication());

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                if (url.equals("1")){
                    imageView.setImageResource(R.drawable.user1);
                }else {
                    imageView.setImageResource(R.drawable.user2);
                }
            }
        };

        app = new User("1","Cookpal","1",true);
        user = new User("0","Wei","0",true);

        findViews();

        setUpViews();

        setupToolbar();

        initAdapter();

        appSays("Hello I am your cooking assistance I will help you cooking this recipe", 2);
        appSays("How much salt did you add", 10);
        userSays("I have added 10 gram of salt",12);
        appSays("Okay",13);
    }



    private void findViews() {
        messagesList = findViewById(R.id.messagesList);
        recognitionProgressView = findViewById(R.id.recognition_view);
        cameraKitView = findViewById(R.id.camera);
    }

    private void setUpViews() {
        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };
        recognitionProgressView.setColors(colors);
        recognitionProgressView.play();
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
            actionBar.setTitle("Recording");
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
                Message message = new Message(Long.toString(UUID.randomUUID().getLeastSignificantBits()),app,sentence);
                messagesAdapter.addToStart(message, true);
                speakerbox.play(sentence);
            }
        }, 1000 * delayInSeconds);
    }

    private void userSays(final String sentence, int delayInSeconds) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message(Long.toString(UUID.randomUUID().getLeastSignificantBits()),user,sentence);
                messagesAdapter.addToStart(message, true);
            }
        }, 1000 * delayInSeconds);
    }

    private void initAdapter() {
        messagesAdapter = new MessagesListAdapter<>("0",imageLoader);
        messagesList.setAdapter(messagesAdapter);
    }
}
