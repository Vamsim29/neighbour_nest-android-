package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class splashzero extends AppCompatActivity {

    public LinearLayout btngetstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashzero);
        btngetstarted=findViewById(R.id.getstartedbtn);
        btngetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotostartpage();
            }
        });
    }

    public void gotostartpage(){
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }
}