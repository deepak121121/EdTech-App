package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Rooms extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bnv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame,new StudentFragment()).commit();

        setContentView(R.layout.activity_rooms);
        bnv=findViewById(R.id.Bottom);
        bnv.setOnNavigationItemSelectedListener(this);

    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment temp=null;
        switch (item.getItemId()) {
            case R.id.Students: temp=new StudentFragment();
                break;
            case R.id.Attendences: temp=new AttendenceFragment();
                break;
            case R.id.Calander: temp= new calenderFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame,temp).commit();

        return true;
    }
}