package com.example.jonmid.practicaborder.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonmid.practicaborder.Models.Food;
import com.example.jonmid.practicaborder.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    List<Food> foodList;
    Context context;

    public FoodAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    // ******************************************************************************

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Asignar los valores a la vista
        holder.textViewTitleFood.setText(foodList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    // ******************************************************************************

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitleFood;


        public ViewHolder(View item) {
            super(item);

            textViewTitleFood = (TextView) item.findViewById(R.id.id_txv_food_title);
        }
    }
}