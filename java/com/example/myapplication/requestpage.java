package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class requestpage extends AppCompatActivity {

    public Button requestpageinbox;
    public Button requestpageyourrequests;

    //back button creation
    public androidx.appcompat.widget.AppCompatButton donationsreqbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestpage);
        requestpageinbox=findViewById(R.id.inboxreq);
        requestpageyourrequests=findViewById(R.id.yourreq);
        requestpageinbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openinboxrequests();
            }
        });
        requestpageyourrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openyourrequests();
            }
        });
        //back button intialization
        donationsreqbackbutton=findViewById(R.id.reqbackbtn);
        donationsreqbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoback();
            }
        });

    }

    public void openinboxrequests(){
        Intent intent =new Intent(this,inboxrequests.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void openyourrequests(){
        Intent intent=new Intent(this,yourrequests.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    //back button function
    public void gotoback(){
        Intent intent=new Intent(this,donationsreq.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
}