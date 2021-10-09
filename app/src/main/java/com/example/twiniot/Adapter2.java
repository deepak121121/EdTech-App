package com.example.twiniot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder> {

    ArrayList<Users> list;
    Context mContext;


    public Adapter2(Context mContext, ArrayList<Users> list) {

        this.list = list;
        this.mContext=mContext;
    }


    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        v=layoutInflater.inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull  Adapter2.MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getUserName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Users temp=list.get(position);
                Intent intent=new Intent(mContext,Personalchat.class);
                intent.putExtra("userId",temp.getUserId());
                intent.putExtra("name",temp.getUserName( ));
                mContext.startActivity(intent);







            }
        });



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;




        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);






        }
    }


}


