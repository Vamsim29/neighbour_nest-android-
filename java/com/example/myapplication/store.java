package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class store extends AppCompatActivity {

    public androidx.appcompat.widget.AppCompatButton backstoretomenu;

    public LinearLayout storebuttoncylinder;
    public LinearLayout storebuttonwater;
    public LinearLayout storebuttonrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        backstoretomenu=findViewById(R.id.storetomenubackbtn);
        storebuttoncylinder=findViewById(R.id.cylinderblock);
        storebuttonwater=findViewById(R.id.watercanblock);
        storebuttonrice=findViewById(R.id.ricebagblock);
        backstoretomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotomenu();
            }
        });
        storebuttoncylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobookcylinder();
            }
        });
        storebuttonwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobookwatercan();
            }
        });
        storebuttonrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobookricebag();
            }
        });
    }
    public void gotomenu(){
        Intent intent=new Intent(this,menubar.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void gotobookwatercan(){
        Intent intent=new Intent(this,storerequest.class);
        String storeid = "Watercan";
        intent.putExtra("idofsotre", storeid);
        //getting the userId from menubar class
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("useridnumber", userIdp);
        startActivity(intent);
    }
    public void gotobookcylinder(){
        Intent intent=new Intent(this,storerequest.class);
        String storeid = "GasCylinder";
        intent.putExtra("idofstore", storeid);
        //getting the userId from menubar class
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("useridnumber", userIdp);
        startActivity(intent);
    }
    public void gotobookricebag(){
        Intent intent=new Intent(this,storerequest.class);
        String storeid = "RiceBag";
        intent.putExtra("idofstore", storeid);
        //getting the userId from menubar class
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("useridnumber", userIdp);
        startActivity(intent);
    }
}