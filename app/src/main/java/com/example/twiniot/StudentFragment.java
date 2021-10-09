package com.example.twiniot;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StudentFragment extends Fragment {
    BottomNavigationView bnv1;
    RecyclerView recyclerView;
    ArrayList<Model> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate1 = inflater.inflate(R.layout.fragment_student, container, false);
        bnv1 = inflate1.findViewById(R.id.Bottom1);
        recyclerView=inflate1.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();
        Cursor cursor=  new DbManager(getContext()).fetch();

        while(cursor.moveToNext()) {

            Model model = new Model(cursor.getString(1));

            list.add(model);
        }
        Adapter adapter=new Adapter(getContext(),this.list);
        recyclerView.setAdapter(adapter);
        bnv1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Add:
                        Intent intent=new Intent(getContext(),AddRoom.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



        return inflate1;

    }
}