package com.example.twiniot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter{
    ArrayList<MesageModel> mesageModels;
    Context context;
    int Sender_View_TYPE=1;
    int RECIEVER_View_TYPE=2;


    public chatAdapter(ArrayList<MesageModel> mesageModels, Context context) {
        this.mesageModels = mesageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==Sender_View_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.sender,parent,false);
            return new SenderViewHolder(view);
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.reciever,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        try {
            if (mesageModels.get(position).getId().equals(FirebaseAuth.getInstance().getUid())) {

                return Sender_View_TYPE;
            } else {
                return RECIEVER_View_TYPE;

            }
        }
        catch (Exception e) {
        }


    return position ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MesageModel mesageModel=mesageModels.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).sendermsg.setText(mesageModel.getMessage());
            if(mesageModel.getMessage().equals("photo")){
                ((SenderViewHolder)holder).imageView.setVisibility(View.VISIBLE);
                ((SenderViewHolder)holder).sendermsg.setVisibility(View.GONE);
                Glide.with(context).load(mesageModel.getImageUrl()).into(((SenderViewHolder) holder).imageView);


            }


        }
        else

       if (mesageModel.getMessage().equals("photo")){
           ((RecieverViewHolder)holder).ImageView.setVisibility(View.VISIBLE);
           ((RecieverViewHolder)holder).recievermsg.setVisibility(View.GONE);
           Glide.with(context).load(mesageModel.getImageUrl()).into(((RecieverViewHolder) holder).ImageView);
       }
        if (holder instanceof RecieverViewHolder) {
            ((RecieverViewHolder)holder).recievermsg.setText(mesageModel.getMessage());
        }


    }

    @Override
    public int getItemCount() {
        return mesageModels.size();
    }

    public static class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView recievermsg,recievertime;
        ImageView ImageView;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recievermsg=itemView.findViewById(R.id.rec);
            recievertime=itemView.findViewById(R.id.rectime);
            ImageView=itemView.findViewById(R.id.imageView14);


        }
    }
    public static class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView sendermsg,sendertime;
        ImageView imageView;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendermsg=itemView.findViewById(R.id.sender);
            sendertime=itemView.findViewById(R.id.sendtime);
            imageView=itemView.findViewById(R.id.imageView13);


        }
    }

}
