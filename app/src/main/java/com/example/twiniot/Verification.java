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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {
    EditText Code;
    Button Verify;
     String Phone,id;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
         Phone=getIntent().getStringExtra("number");
         Code=findViewById(R.id.Code);
         Verify=findViewById(R.id.Verify);
        mAuth = FirebaseAuth.getInstance();
        sendVerification();

         Verify.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String code=Code.getText().toString();
                 if(TextUtils.isEmpty(code)){
                     Toast.makeText(Verification.this, "Please Enter Otp", Toast.LENGTH_SHORT).show();
                 }
                 else if(code.length()!=6){
                     Toast.makeText(Verification.this, "Enter Correct Otp", Toast.LENGTH_SHORT).show();


                 }
                 else{
                     PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, Code.getText().toString());
                     signInWithPhoneAuthCredential(credential);

                 }


             }
         });
    }

    private void sendVerification() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(Phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String id, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                              Verification.this.id=id;

                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Verification.this, "Failed", Toast.LENGTH_SHORT).show();


                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(Verification.this, Dashboard.class);
                            startActivity(intent);
                            Toast.makeText(Verification.this, "Credential Successful;", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Verification.this, "Credential Failed;", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }

}