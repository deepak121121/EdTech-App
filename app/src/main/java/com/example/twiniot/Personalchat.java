 package com.example.twiniot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Personalchat extends AppCompatActivity {
    TextView textView;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<MesageModel>mesageModels;
    RecyclerView recyclerView;
    EditText editText;
    ImageView imageView,Gallery,camera,Video;
    ProgressDialog dialog;
    FirebaseStorage storage;
    String sender,reciever;
     String senderRoom;
     String recieverRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalchat);
        Intent intent = getIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        editText = findViewById(R.id.edit1);
        Video=findViewById(R.id.video);
        imageView=findViewById(R.id.messagesend);
        Gallery=findViewById(R.id.gallery);
        camera=findViewById(R.id.camera);
        textView = findViewById(R.id.text12);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Uploading Image...");
        dialog.setCancelable(false);
        storage=FirebaseStorage.getInstance();
        sender =auth.getUid();
         reciever = getIntent().getStringExtra("userId");
        textView.setText(intent.getStringExtra("name"));
        recyclerView = findViewById(R.id.recycler2);
        mesageModels = new ArrayList<>();
        chatAdapter chat = new chatAdapter(mesageModels, this);
        recyclerView.setAdapter(chat);
         senderRoom=sender+reciever;
         recieverRoom=reciever+sender;

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
        Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




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

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1,25);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==25){
            if(data !=null){
                if(data.getData()!=null){
                    Uri SelectImage=data.getData();
                    Calendar calender=Calendar.getInstance();
                    dialog.show();
                    StorageReference reference=storage.getReference().child("chats").child(calender.getTimeInMillis()+"");
                    reference.putFile(SelectImage). addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            dialog.dismiss();
                            if(task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(@NonNull Uri uri) {
                                        String filePath=uri.toString();
                                        String message=editText.getText().toString();

                                        final MesageModel mesageModel=new MesageModel(sender,message);
                                        mesageModel.setMessage("photo");
                                        mesageModel.setImageUrl(filePath);
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
                                        Toast.makeText(Personalchat.this, filePath, Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                        }
                    });
                }
            }
        }
    }

    public void arrow(View view) {
        Intent intent=new Intent(Personalchat.this,Message.class);
        startActivity(intent);
    }




}