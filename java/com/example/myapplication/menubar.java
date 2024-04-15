package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class menubar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.drawerlayout);
        NavigationView navigationView= findViewById(R.id.nav_veiwa);
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


        if (id == R.id.nav_profile)
        {
            Intent intent=new Intent(this, profile.class);
            //getting the userId from login page
            Intent intent1 = getIntent();
            String piduser = intent1.getStringExtra("idus");
            intent.putExtra("idus", piduser);
            startActivity(intent);
        }
        else if(id == R.id.nav_store)
        {
            Intent intent=new Intent(this, store.class);
            //getting the userId from login page
            Intent intent1 = getIntent();
            String piduser = intent1.getStringExtra("idus");
            intent.putExtra("idus", piduser);
            startActivity(intent);
        }
        else if (id == R.id.nav_service)
        {
            Intent intent=new Intent(this, services.class);
            //getting the userId from login page
            Intent intent1 = getIntent();
            String piduser = intent1.getStringExtra("idus");
            intent.putExtra("idus", piduser);
            startActivity(intent);
        }
        else if(id == R.id.nav_history)
        {
            Intent intent=new Intent(this, userhistory.class);
            //getting the userId from login page
            Intent intent1 = getIntent();
            String piduser = intent1.getStringExtra("idus");
            intent.putExtra("idus", piduser);
            startActivity(intent);
        }
        else if (id == R.id.nav_donations)
        {
            Intent intent=new Intent(this, donationsreq.class);
            //getting the userId from login page
            Intent intent1 = getIntent();
            String piduser = intent1.getStringExtra("idus");
            intent.putExtra("idus", piduser);
            startActivity(intent);
        }
        else if(id == R.id.nav_logout)
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
