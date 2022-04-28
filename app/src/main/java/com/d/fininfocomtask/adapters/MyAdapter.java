package com.d.fininfocomtask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d.fininfocomtask.R;
import com.d.fininfocomtask.models.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.recycleritem, parent, false );
        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get( position );

        holder.uName.setText( user.getUserName() );
        holder.uAge.setText( user.getUserAge() );
        holder.uCity.setText( user.getUserCity() );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView uName, uCity, uAge;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            uName = itemView.findViewById( R.id.uName );
            uAge = itemView.findViewById( R.id.uAge );
            uCity = itemView.findViewById( R.id.uCity );

        }
    }
}

