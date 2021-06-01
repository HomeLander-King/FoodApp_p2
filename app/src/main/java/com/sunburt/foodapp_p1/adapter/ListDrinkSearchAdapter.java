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
import com.sunburt.foodapp_p1.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class ListDrinkSearchAdapter extends RecyclerView.Adapter<ListDrinkSearchAdapter.ListDrinkSearchHolder> {

    private List<Drink> drinks = new ArrayList<>();
    private SearchActivity searchActivity;
    private OnRecyclerClickListener listener;

    public ListDrinkSearchAdapter(SearchActivity activity, List<Drink> list){
        this.drinks = list;
        this.searchActivity = activity;
    }

    /**
     * interface listen action from list drink
     */

    public interface OnRecyclerClickListener{
        void onItemClick(int position);
    }

    public void OnRecyclerClickListener(OnRecyclerClickListener mListener){
        this.listener = mListener;
    }

    @NonNull
    @Override
    public ListDrinkSearchAdapter.ListDrinkSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_drink_search,
                parent,false);
        return new ListDrinkSearchHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDrinkSearchAdapter.ListDrinkSearchHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.imgDrink.setImageResource(drink.getImgsrc());
        holder.txtName.setText(drink.getName());
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class ListDrinkSearchHolder extends RecyclerView.ViewHolder {
        private ImageView imgDrink;
        private TextView txtName;

        public ListDrinkSearchHolder(@NonNull View itemView, final OnRecyclerClickListener listener) {
            super(itemView);
            imgDrink = (itemView).findViewById(R.id.imgDrink);
            txtName = (itemView).findViewById(R.id.txtName);

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
