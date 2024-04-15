package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class services extends AppCompatActivity {

    public androidx.appcompat.widget.AppCompatButton backbutton;
    public LinearLayout cleaningbox;
    public LinearLayout laundarybox;
    public LinearLayout electricianbox;
    public LinearLayout pulmbing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        backbutton=findViewById(R.id.servicesbackbtn);
        cleaningbox=findViewById(R.id.cleaningblock);
        laundarybox=findViewById(R.id.laundryblock);
        electricianbox=findViewById(R.id.electricianblock);
        pulmbing=findViewById(R.id.plumbingblock);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobackpage();
            }
        });
        cleaningbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotorequestpage("Cleaning Service");
            }
        });
        laundarybox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotorequestpage("Laundary Service");
            }
        });
        electricianbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotorequestpage("Electrician");
            }
        });
        pulmbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotorequestpage("Plumber");
            }
        });
    }
    public void gotobackpage(){
        Intent intent=new Intent(this,menubar.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void gotorequestpage(String name){
        String namegotten=name;
        Intent intent=new Intent(this,storerequest.class);
        intent.putExtra("idofstore", namegotten);
        //getting the userId from menubar class
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("useridnumber", userIdp);
        startActivity(intent);
    }
}