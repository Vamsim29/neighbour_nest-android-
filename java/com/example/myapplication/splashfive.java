package com.example.myapplication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
public class splashfive extends AppCompatActivity {

//    public static int splash_timer=500;
private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashfive);

        final View redBox = findViewById(R.id.bluebox);
        final LinearLayout textView = findViewById(R.id.viewtext);

        // Create an animation that rotates the red box for 2 seconds
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // After the animation ends, make the text view visible and hide the red box
                textView.setVisibility(View.VISIBLE);
                redBox.setVisibility(View.INVISIBLE);

                // Start a handler to delay the transition to the next page for 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(splashfive.this, splashone.class);
                        startActivity(intent);
                    }
                }, 3000);

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Start the animation
        redBox.startAnimation(animation);
    }
}