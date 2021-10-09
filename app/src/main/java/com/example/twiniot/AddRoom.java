package com.example.twiniot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoom extends AppCompatActivity {
    EditText Student;
    Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        Student=findViewById(R.id.student);
        Add=findViewById(R.id.Add2);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbManager db=new DbManager(AddRoom.this);
                db.add(Student.getText().toString());
                Toast.makeText(AddRoom.this, "hii ", Toast.LENGTH_SHORT).show();
                Student.setText("");



            }
        });
    }
}
