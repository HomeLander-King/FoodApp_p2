package com.sunburt.foodapp_p1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.activity.SearchActivity;
import com.sunburt.foodapp_p1.model.Food;

import java.util.List;

public class ListFoodSearchAdapter extends RecyclerView.Adapter<ListFoodSearchAdapter.ListFoodSearchHolder> {

    private SearchActivity searchActivity;
    private List<Food> foods;
    private OnRecyclerClickListener listener;

    public ListFoodSearchAdapter(SearchActivity activity, List<Food> list){
        this.searchActivity = activity;
        this.foods = list;
    }

    /**
     *interface listen handle of recycler
     */

    public interface OnRecyclerClickListener{
        void onItemClick(int position);
    }

    public void OnRecyclerClickListener(OnRecyclerClickListener mListener){
        this.listener = mListener;
    }

    @NonNull
    @Override
    public ListFoodSearchAdapter.ListFoodSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_food_search, parent,
                false);
        return new ListFoodSearchHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodSearchAdapter.ListFoodSearchHolder holder, int position) {
        Food food = foods.get(position);
        holder.imgFood.setImageResource(food.getImgsrc());
        holder.txtName.setText(food.getName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ListFoodSearchHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood;
        private TextView txtName;
        public ListFoodSearchHolder(@NonNull View itemView, final OnRecyclerClickListener listener) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtName = itemView.findViewById(R.id.txtName);

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
