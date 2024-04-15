package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.Slide;

public class splashsix extends AppCompatActivity {

    public static int splash_timer = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashsix);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create a Slide transition that slides the new screen in from the right
                Slide slide = new Slide(Gravity.RIGHT);
                slide.setDuration(500);

                // Set the transition manager's target view to the root view
                TransitionManager.beginDelayedTransition((ViewGroup) findViewById(android.R.id.content), slide);

                // Start the activity transition to splashfive
                Intent intent = new Intent(splashsix.this, splashfive.class);
                startActivity(intent);

                // Finish the current activity after the transition completes
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
            }
        }, splash_timer);
    }
}
