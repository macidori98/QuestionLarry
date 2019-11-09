package com.example.questionlarry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_birthDate;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_birthDate = view.findViewById(R.id.tv_birthDate);
        }
    }

    public ListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element_recycler_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.tv_birthDate.setText("1998");
        holder.tv_name.setText("Szabo Dorottya");
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
