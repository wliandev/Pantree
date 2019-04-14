package com.watermelons.pantree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private ArrayList<Food> groceryList;

    public PantryAdapter(ArrayList<Food> groceryList, Context context) {
        this.groceryList = groceryList;
    }

    @Override
    public PantryAdapter.PantryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_info,parent,false);
        PantryViewHolder viewHolder = new PantryViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PantryAdapter.PantryViewHolder holder, int position) {
        holder.name.setText(groceryList.get(position).getName());
        holder.age.setText(Long.toString(groceryList.get(position).getAge()) + " days old");
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView age;

        public PantryViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
        }
    }

}
