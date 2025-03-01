package com.example.fruits;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {
    private final Fruit[] fruitList;
    public FruitAdapter(Fruit[] fruitList) {
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Fruit fruit = fruitList[position];
        holder.imageView.setImageResource(fruit.getImage());
        holder.title.setText(fruit.getName());
        holder.description.setText(fruit.getDescription());
    }

    @Override
    public int getItemCount() {
        return fruitList.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, description;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.fruitName);
            description = view.findViewById(R.id.fruitDescription);
        }
    }
}
