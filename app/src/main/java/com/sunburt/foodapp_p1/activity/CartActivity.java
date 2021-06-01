package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    private Dialog dialog;
    float totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getControls();


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
                openCheckoutDialog();
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

    private void openCheckoutDialog() {
        dialog.setContentView(R.layout.checkout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = dialog.findViewById(R.id.textView8);
        Button button = dialog.findViewById(R.id.button);

        textView.setText("Total Price:"+ totalPrice+"$");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void getControls() {
        revCartItem = findViewById(R.id.revCartItem);
        txtTotalPrice = findViewById(R.id.txtToTalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnMenu = findViewById(R.id.btnMenu);
        db = new DBHelper(this);
        orders = db.getAllOrder();
        adapter = new CartItemAdapter(CartActivity.this, orders);
        revCartItem.setAdapter(adapter);
        revCartItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new Dialog(this);
    }
}