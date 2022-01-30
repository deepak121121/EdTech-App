package com.example.twiniot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText email,password,confirmPassword;
    Button register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        email=findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        confirmPassword=findViewById(R.id.txt_confirmPassword);
        register=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(v -> {

            String Email=email.getText().toString();
            String Password=password.getText().toString();
            String ConfirmPassword=confirmPassword.getText().toString();
            if(TextUtils.isEmpty(Email)){
                Toast.makeText(Registration.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;

            }
            if(TextUtils.isEmpty(Password)){
                Toast.makeText(Registration.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;

            }if(TextUtils.isEmpty(ConfirmPassword)){
                Toast.makeText(Registration.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                return;

            }
            if(Password.length()<6){
                Toast.makeText(Registration.this, "Password too short", Toast.LENGTH_SHORT).show();


            }
            if(Password.equals(ConfirmPassword)) {

                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Intent intent=new Intent(Registration.this,Dashboard2.class);
                                                        startActivity(intent);
                                                        Toast.makeText(Registration.this, "Authentication Successful. Please check your email for Verification", Toast.LENGTH_SHORT).show();

                                                    }
                                                    else{
                                                        Toast.makeText(Registration.this, "Authentication Failed", Toast.LENGTH_SHORT).show();


                                                    }

                                                }
                                            });

                                } else {
                                    Toast.makeText(Registration.this, "Authentication Failed", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });

            }
        });


    }
    public void Click(View view){
        Intent intent=new Intent(Registration.this,Login.class);
        startActivity(intent);

    }

}