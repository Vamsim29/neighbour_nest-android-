package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class homepage extends AppCompatActivity{

    public androidx.appcompat.widget.AppCompatButton buttonhometomenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        buttonhometomenu=findViewById(R.id.userhomebackbtn);
        buttonhometomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hometomenupage();
            }
        });
        }
        public void hometomenupage(){
            Intent intent=new Intent(this, menubar.class);
            startActivity(intent);
        }
    }
