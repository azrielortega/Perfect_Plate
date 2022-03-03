package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminViewHolder>{

    private ArrayList<User> admins;

    public AdminAdapter(ArrayList<User> a){
        this.admins = a;
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.admin_list_template, parent, false);

        AdminViewHolder holder = new AdminViewHolder(itemView);

        holder.getIBDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete Admin
                Toast.makeText(parent.getContext(), "Admin Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        holder.setTvEmail(admins.get(position).getEmail());
        holder.setTvName(admins.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return admins.size();
    }
}
