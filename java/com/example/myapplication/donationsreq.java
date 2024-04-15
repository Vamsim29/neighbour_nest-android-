package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class donationsreq extends AppCompatActivity {

    public androidx.appcompat.widget.AppCompatButton donationsreqbackbutton;

    public Button donationsreqdonbtn;
    public Button donationsreqreqbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationsreq);

        donationsreqbackbutton=findViewById(R.id.donreqbackbtn);
        donationsreqbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotomenubar();
            }
        });
        donationsreqdonbtn=findViewById(R.id.donationsbtn);
        donationsreqreqbtn=findViewById(R.id.requestsbtn);
        donationsreqdonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendonationspage();
            }
        });
        donationsreqreqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openrequestspage();
            }
        });
    }
    public void gotomenubar(){
        Intent intent=new Intent(this,menubar.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void opendonationspage() {
        Intent intent=new Intent(this,donatepage.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void openrequestspage(){
        Intent  intent=new Intent(this,requestpage.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
}