package com.example.twiniot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        if(mesageModels.get(position).getId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return Sender_View_TYPE;

        }
        else {
             return RECIEVER_View_TYPE;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MesageModel mesageModel=mesageModels.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).sendermsg.setText(mesageModel.getMessage());

        }
        else
            ((RecieverViewHolder)holder).recievermsg.setText(mesageModel.getMessage());



    }

    @Override
    public int getItemCount() {
        return mesageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView recievermsg,recievertime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recievermsg=itemView.findViewById(R.id.rec);
            recievertime=itemView.findViewById(R.id.rectime);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView sendermsg,sendertime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendermsg=itemView.findViewById(R.id.sender);
            sendertime=itemView.findViewById(R.id.sendtime);

        }
    }

}
