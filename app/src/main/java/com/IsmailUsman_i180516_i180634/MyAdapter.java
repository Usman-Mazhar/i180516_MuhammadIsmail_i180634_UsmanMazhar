package com.IsmailUsman_i180516_i180634;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<Contact> ls;
    Context c;

    public MyAdapter(List<Contact> ls, Context c) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(ls.get(position).getFirstName());
        holder.profile_picture.setImageURI(ls.get(position).getProfile());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Chat.class);
                intent.putExtra("fName", ls.get(position).getFirstName());
                intent.putExtra("profileUriStr", (ls.get(position).getProfile()).toString());
                int index = position;
                ((Activity)c).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, last_mssg, time;

        ImageView profile_picture;

        RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            last_mssg = itemView.findViewById(R.id.last_mssg);
            time = itemView.findViewById(R.id.time);
            profile_picture = itemView.findViewById(R.id.profile_picture);
            rl = itemView.findViewById(R.id.rl);
        }
    }
}
