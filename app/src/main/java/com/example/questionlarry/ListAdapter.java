package com.example.questionlarry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context context;
    private List<Form> forms;

    private OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_birthDate;
        public ImageView image;

        public MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_birthDate = view.findViewById(R.id.tv_birthDate);
            image = view.findViewById(R.id.imageView_personImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public ListAdapter(Context context, List<Form> forms) {
        this.context = context;
        this.forms = forms;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element_recycler_view, parent, false);
        return new MyViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.tv_birthDate.setText(forms.get(position).getBirthDate());
        holder.tv_name.setText(forms.get(position).getName());
        loadImage(Glide.with(MainActivity.MainActivityContext), forms.get(position).getImageUri(), holder.image);
    }

    private void loadImage(RequestManager glide, String url, ImageView view) {
        glide.load(url).into(view);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }
}
