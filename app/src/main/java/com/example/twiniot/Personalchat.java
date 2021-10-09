package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Personalchat extends AppCompatActivity {
    TextView textView;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<MesageModel>mesageModels;
    RecyclerView recyclerView;
    EditText editText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalchat);
        Intent intent = getIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        editText = findViewById(R.id.edit1);
        imageView=findViewById(R.id.messagesend);
        textView = findViewById(R.id.text12);
        String sender = auth.getUid();
        String reciever = getIntent().getStringExtra("userId");
        textView.setText(intent.getStringExtra("name"));
        recyclerView = findViewById(R.id.recycler2);
        mesageModels = new ArrayList<>();
        chatAdapter chat = new chatAdapter(mesageModels, this);
        recyclerView.setAdapter(chat);
        final String senderRoom=sender+reciever;
        final String recieverRoom=reciever+sender;

       firebaseDatabase.getReference().child("chat")
               .child(senderRoom)
               .addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       mesageModels.clear();
                       for(DataSnapshot snapshot1:snapshot.getChildren()){
                           MesageModel mesageModel=snapshot1.getValue(MesageModel.class);
                           mesageModels.add(mesageModel);

                       }
                       chat.notifyDataSetChanged();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=editText.getText().toString();

                final MesageModel mesageModel=new MesageModel(sender,message);
                mesageModel.setTimestamp(new Date().getTime());
                editText.setText("");

                firebaseDatabase.getReference().child("chat")
                        .child(senderRoom).push()
                        .setValue(mesageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void avoid) {
                        firebaseDatabase.getReference().child("chat")
                                .child(recieverRoom).push()
                                .setValue(mesageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                            }
                        });

                    }
                });


            }
        }) ;



    }
    public void arrow(View view) {
        Intent intent=new Intent(Personalchat.this,Message.class);
        startActivity(intent);
    }




}