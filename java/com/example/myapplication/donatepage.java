package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class donatepage extends AppCompatActivity {

    public Button donatepageinbox;
    public Button donatepageyourdonations;
    //back button creation
    public androidx.appcompat.widget.AppCompatButton donationsreqbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donatepage);
        donatepageinbox=findViewById(R.id.dinbox);
        donatepageyourdonations=findViewById(R.id.dyourdonations);
        donatepageinbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openinboxdonations();
            }
        });
        donatepageyourdonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openyourdonations();
            }
        });
        //back button intialization
        donationsreqbackbutton=findViewById(R.id.donreqbackbtn);
        donationsreqbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobackpage();
            }
        });
    }
    public void openinboxdonations(){
        Intent intent =new Intent(this,inboxdonations.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void openyourdonations(){
        Intent intent=new Intent(this,yourdonations.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }

    //back button function
    public void gotobackpage(){
        Intent intent=new Intent(this,donationsreq.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
}