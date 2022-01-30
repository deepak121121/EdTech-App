package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    DrawerLayout dra;
    ActionBarDrawerToggle toggle2;
    Toolbar tool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard2 );
        tool = findViewById( R.id.toolbar3 );
        setSupportActionBar( tool );
        nav = findViewById( R.id.nevi2 );
        dra = findViewById( R.id.Drawer2 );
        toggle2 = new ActionBarDrawerToggle( this, dra, tool, R.string.open, R.string.close );
        dra.addDrawerListener( toggle2 );
        toggle2.syncState();
        nav.bringToFront();
        nav.setNavigationItemSelectedListener( this );
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Location:
                Toast.makeText( this, "Location is open", Toast.LENGTH_SHORT ).show();
                dra.closeDrawer( GravityCompat.START );
                break;
            case R.id.Settings:
                Toast.makeText( this, "Settings is open", Toast.LENGTH_SHORT ).show();
                dra.closeDrawer( GravityCompat.START );
                break;
            case R.id.help:
                Toast.makeText( this, "help is open", Toast.LENGTH_SHORT ).show();
                dra.closeDrawer( GravityCompat.START );
                break;
            case R.id.feedback:
                Toast.makeText( this, "feedback is open", Toast.LENGTH_SHORT ).show();
                dra.closeDrawer( GravityCompat.START );
                break;


        }


        return true;
    }



}