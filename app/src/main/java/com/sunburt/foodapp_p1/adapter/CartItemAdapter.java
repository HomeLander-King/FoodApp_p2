package com.sunburt.foodapp_p1.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.activity.CartActivity;
import com.sunburt.foodapp_p1.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemHolder> {

    private List<Order> orders = new ArrayList<>();
    private CartActivity cartActivity;

    public CartItemAdapter(CartActivity activity, List<Order> orderList){
        this.cartActivity = activity;
        this.orders = orderList;
    }


    @NonNull
    @Override
    public CartItemAdapter.CartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent,
                false);
        return new CartItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.CartItemHolder holder, int position) {
        Order order = orders.get(position);
        holder.imgItem.setImageResource(order.getImgsrc());
        holder.txtName.setText(order.getName());
        holder.txtPrice.setText(order.getPrice()+"");
        holder.txtOrderNum.setText(order.getNumber()+"");
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class CartItemHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView txtName, txtPrice, txtOrderNum;

        public CartItemHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtOrderNum = itemView.findViewById(R.id.txtOrderNum);
        }
    }
}
