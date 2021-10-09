package com.example.twiniot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button Login2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email=findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        Login2=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        Login2.setOnClickListener(v -> {
            String Email=email.getText().toString();
            String Password=password.getText().toString();
            if(TextUtils.isEmpty(Email)){
                Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;

            }
            if(TextUtils.isEmpty(Password)){
                Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;



            }
            if(Password.length()<6){
                Toast.makeText(Login.this, "Password is incorrect", Toast.LENGTH_SHORT).show();


            }
            else{
                login(Email,Password);
            }
        });


    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(mAuth.getCurrentUser().isEmailVerified()){
                             Intent intent=new Intent(Login.this, Dashboard.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Login.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    public void click(View view ){
        Intent intent=new Intent(Login.this,Registration.class);
        startActivity(intent);
    }

}