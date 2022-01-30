package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    DrawerLayout dra;
    ActionBarDrawerToggle toggle;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tool = findViewById(R.id.toolbar2);
        setSupportActionBar(tool);
        nav = findViewById(R.id.nevi);
        dra = findViewById(R.id.Drawer);
        toggle = new ActionBarDrawerToggle(this, dra, tool, R.string.open, R.string.close);
        dra.addDrawerListener(toggle);
        toggle.syncState();
        nav.bringToFront();

        nav.setNavigationItemSelectedListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Location:
                Toast.makeText(this, "Location is open", Toast.LENGTH_SHORT).show();
                dra.closeDrawer(GravityCompat.START);
                break;
            case R.id.Settings:
                Toast.makeText(this, "Settings is open", Toast.LENGTH_SHORT).show();
                dra.closeDrawer(GravityCompat.START);
                break;
            case R.id.help:
                Toast.makeText(this, "help is open", Toast.LENGTH_SHORT).show();
                dra.closeDrawer(GravityCompat.START);
                break;
            case R.id.feedback:
                Toast.makeText(this, "feedback is open", Toast.LENGTH_SHORT).show();
                dra.closeDrawer(GravityCompat.START);
                break;



    }

        return true;
    }
    public void Room(View view){
        Intent intent=new Intent(Dashboard.this,Rooms.class);
        startActivity(intent);
    }
    public void Message(View view){
        Intent homemap = new Intent(this,Message.class);
        startActivity(homemap);
    }

}