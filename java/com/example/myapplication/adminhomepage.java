package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class adminhomepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhomepage);

        Toolbar toolbar = findViewById(R.id.admiintoolbar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.admindrawerlayout);
        NavigationView navigationView= findViewById(R.id.adminnavview);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id= item.getItemId();


        if (id == R.id.adminnavmanagehomes)
        {
            startActivity(new Intent(this, adminmanagerooms.class));
        }
        else if(id == R.id.adminnavmanageservices)
        {
            startActivity(new Intent(this, adminmanageservices.class));
        }

        else if(id == R.id.adminnavlogout)
        {
            startActivity(new Intent(this, login.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
