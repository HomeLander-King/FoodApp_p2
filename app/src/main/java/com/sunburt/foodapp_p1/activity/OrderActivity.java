package com.sunburt.foodapp_p1.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.adapter.CartItemAdapter;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.database.OrderProvider;
import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Food;

public class OrderActivity extends AppCompatActivity  {

    private ImageView imageDrink;
    private TextView txtName, txtPrice, txtOrderNum, txtDescription;
    private ImageButton btnPlus, btnSubstract, btnAdd;
    private int orderNumber;
    private DBHelper db;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getControls();

        Bundle bundle = getIntent().getExtras();
        final Drink drink = (Drink) bundle.getSerializable("mDrink");
        final int src = bundle.getInt("src");
        final float  base_rice = drink.getPrice();
        imageDrink.setImageResource(src);
        txtName.setText(drink.getName());
        txtPrice.setText(drink.getPrice()+"");
        txtDescription.setText(drink.getDescription());

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumber++;
                float totalPrice = base_rice * orderNumber;
                txtPrice.setText(String.valueOf(totalPrice));
                displayOrderNumber();
            }
        });

        btnSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderNumber == 0)
                    Toast.makeText(OrderActivity.this, "Can not order less than 0",
                            Toast.LENGTH_SHORT).show();
                else{
                    orderNumber--;
                    float total_price = base_rice*orderNumber;
                    txtPrice.setText( String.valueOf(total_price));
                    displayOrderNumber();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), CartActivity.class);
                /**
                intent.putExtra("order_name", txtName.getText().toString());
                intent.putExtra("order_price", Float.parseFloat(txtPrice.getText().toString()));
                intent.putExtra("order_img", src);
                intent.putExtra("order_number", Integer.parseInt(txtOrderNum.getText().toString()));
                 **/
                boolean checkAddOrder = db.addNewOrder(txtName.getText().toString(),
                        src, Integer.parseInt(txtOrderNum.getText().toString()),
                        Float.parseFloat(txtPrice.getText().toString()));
                startActivity(intent);
            }
        });

    }


    private void displayOrderNumber() {
        txtOrderNum.setText(String.valueOf(orderNumber));
    }

    private void getControls() {
        imageDrink = findViewById(R.id.imgDrink);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtOrderNum = findViewById(R.id.txtOrderNum);
        txtDescription = findViewById(R.id.txtDescription);
        btnPlus = findViewById(R.id.btnPlus);
        btnSubstract = findViewById(R.id.btnSubstract);
        btnAdd = findViewById(R.id.btnAdd);
        db = new DBHelper(this);
    }

}