package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity4 extends AppCompatActivity {
    EditText name, email1, password1, confirmPassword1;
    Button register2;
    private FirebaseAuth mAuth2;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        name = findViewById(R.id.txt_name);
        email1 = findViewById(R.id.txt_email1);
        password1 = findViewById(R.id.txt_password1);
        mAuth2 = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        confirmPassword1 = findViewById(R.id.txt_confirmPassword1);
        register2 = findViewById(R.id.Register2);
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Email1 = email1.getText().toString();
                String Password1 = password1.getText().toString();
                String ConfirmPassword1 = confirmPassword1.getText().toString();
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(MainActivity4.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(Email1)) {
                    Toast.makeText(MainActivity4.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(Password1)) {
                    Toast.makeText(MainActivity4.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(ConfirmPassword1)) {
                    Toast.makeText(MainActivity4.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (Password1.length() < 6) {
                    Toast.makeText(MainActivity4.this, "Password too short", Toast.LENGTH_SHORT).show();


                }
                if (Password1.equals(ConfirmPassword1)) {

                    mAuth2.createUserWithEmailAndPassword(Email1, Password1)
                            .addOnCompleteListener(MainActivity4.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Users users=new Users(Name,Email1,Password1);
                                     String id=task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(users);

                                        mAuth2.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Intent intent = new Intent(MainActivity4.this,PhoneAuthentication.class);
                                                            startActivity(intent);
                                                            Toast.makeText(MainActivity4.this, "Success", Toast.LENGTH_SHORT).show();

                                                        } else {
                                                            Toast.makeText(MainActivity4.this, "Authentication Failed", Toast.LENGTH_SHORT).show();


                                                        }

                                                    }
                                                });

                                    } else {
                                        Toast.makeText(MainActivity4.this, "Authentication Failed", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            });
                }
            }
        });
    }
}
