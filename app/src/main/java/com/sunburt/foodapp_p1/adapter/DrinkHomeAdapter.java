package com.sunburt.foodapp_p1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.activity.DrinkHomeActivity;
import com.sunburt.foodapp_p1.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkHomeAdapter extends RecyclerView.Adapter<DrinkHomeAdapter.DrinkHomeHolder> {

    private List<Drink> drinks = new ArrayList<>();
    private DrinkHomeActivity drinkHomeActivity;
    private OnRecyclerClickListener listener;

    /**
     * interface listen action from recyclerview
     */
    public interface OnRecyclerClickListener{
        void onItemClick(int position);
    }

    public void OnRecyclerClickListener(OnRecyclerClickListener mListener){
        this.listener = mListener;
    }

    public DrinkHomeAdapter(DrinkHomeActivity activity, List<Drink> list){
        this.drinkHomeActivity = activity;
        this.drinks = list;
    }



    @NonNull
    @Override
    public DrinkHomeAdapter.DrinkHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_drink_home,
            parent, false);
        return new DrinkHomeHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkHomeAdapter.DrinkHomeHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.imgDrink.setImageResource(drink.getImgsrc());
        holder.txtDrinkName.setText(drink.getName());
        holder.txtDrinkDescription.setText(drink.getDescription());
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class DrinkHomeHolder extends RecyclerView.ViewHolder {
        private ImageView imgDrink;
        private TextView txtDrinkName, txtDrinkDescription;

        public DrinkHomeHolder(@NonNull View itemView, final OnRecyclerClickListener listener) {
            super(itemView);

            imgDrink = (ImageView) itemView.findViewById(R.id.imgDrink);
            txtDrinkName = (TextView) itemView.findViewById(R.id.txtName);
            txtDrinkDescription = (TextView) itemView.findViewById(R.id.txtDescription);

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
