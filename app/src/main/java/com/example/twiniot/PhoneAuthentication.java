package com.example.twiniot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class PhoneAuthentication extends AppCompatActivity {
    EditText phone;
    private CountryCodePicker CCP;
    Button getOtp;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_authentication);
        phone=findViewById(R.id.phone);
        name=findViewById(R.id.name1);
        CCP=findViewById(R.id.ccp);
        CCP.registerCarrierNumberEditText(phone);
       Spinner spinner1=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>
                (PhoneAuthentication.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Program_Type));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        getOtp=findViewById(R.id.getotp);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phone=phone.getText().toString();
                String Name1=name.getText().toString();

                if(TextUtils.isEmpty(Phone)){
                    Toast.makeText(PhoneAuthentication.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
                else if(Phone.length()!=10){
                    Toast.makeText(PhoneAuthentication.this, "Enter Correct Number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Name1)){
                    Toast.makeText(PhoneAuthentication.this, "Please Enter School or Program", Toast.LENGTH_SHORT).show();

                }



                else{
                    Intent intent=new Intent(PhoneAuthentication.this,Verification.class);
                    intent.putExtra("number",CCP.getFullNumberWithPlus().replace("",""));
                    startActivity(intent);
                }


            }
        });


    }
}