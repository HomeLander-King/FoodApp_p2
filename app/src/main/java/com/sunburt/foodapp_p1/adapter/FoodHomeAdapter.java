package com.sunburt.foodapp_p1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.activity.DrinkHomeActivity;
import com.sunburt.foodapp_p1.activity.FoodHomeActivity;
import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodHomeAdapter extends RecyclerView.Adapter<FoodHomeAdapter.FoodHomeHolder> {

    private List<Food> foods = new ArrayList<>();
    private FoodHomeActivity foodHomeActivity;
    private OnRecyclerFoodClickListener listener;

    public interface OnRecyclerFoodClickListener{
        void onItemClick(int position);
    }

    public void OnRecyclerFoodClickListener(OnRecyclerFoodClickListener mListener){
        this.listener = mListener;
    }

    public FoodHomeAdapter(FoodHomeActivity activity, List<Food> list){
        this.foodHomeActivity = activity;
        this.foods = list;
    }
    @NonNull
    @Override
    public FoodHomeAdapter.FoodHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_food_home,
                parent, false);
        return new FoodHomeHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHomeAdapter.FoodHomeHolder holder, int position) {
        Food food = foods.get(position);
        holder.imgFood.setImageResource(food.getImgsrc());
        holder.txtFoodName.setText(food.getName());
        holder.txtFoodDescription.setText(food.getDescription());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodHomeHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood;
        private TextView txtFoodName, txtFoodDescription;
        public FoodHomeHolder(@NonNull View itemView, final OnRecyclerFoodClickListener listener) {
            super(itemView);

            imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
            txtFoodName = (TextView) itemView.findViewById(R.id.txtName);
            txtFoodDescription = (TextView) itemView.findViewById(R.id.txtDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
