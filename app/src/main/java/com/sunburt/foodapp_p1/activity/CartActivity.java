package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.adapter.CartItemAdapter;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView revCartItem;
    private TextView txtTotalPrice;
    private ImageButton btnCheckout, btnMenu;
    private DBHelper db;
    private List<Order> orders = new ArrayList<>();
    private CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getControls();

        orders = db.getAllOrder();
        adapter = new CartItemAdapter(CartActivity.this, orders);
        revCartItem.setAdapter(adapter);
        revCartItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        float totalPrice = 0;
        for (Order order: orders
             ) {
            totalPrice += order.getPrice();
        }

        txtTotalPrice.setText(totalPrice + "$");

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllOrder();
                adapter.notifyDataSetChanged();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getControls() {
        revCartItem = findViewById(R.id.revCartItem);
        txtTotalPrice = findViewById(R.id.txtToTalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnMenu = findViewById(R.id.btnMenu);
        db = new DBHelper(this);
    }
}