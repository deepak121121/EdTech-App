package com.example.twiniot;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
    }
    public void signup(View v){
        Intent intent=new Intent(MainActivity.this,Dashboard.class);
        startActivity(intent);

    }
    public void signin(View v){
        Intent intent=new Intent(MainActivity.this,Login.class);
        startActivity(intent);
    }


}