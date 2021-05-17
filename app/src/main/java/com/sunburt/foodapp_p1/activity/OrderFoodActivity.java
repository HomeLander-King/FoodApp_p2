package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Food;

public class OrderFoodActivity extends AppCompatActivity {

    private ImageView imageFood;
    private TextView txtName, txtPrice, txtOrderNum, txtDescription;
    private ImageButton btnPlus, btnSubstract, btnAdd;
    private int orderNumber;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        getControls();

        Bundle bundle = getIntent().getExtras();
        final Food food = (Food) bundle.getSerializable("mFood");
        final int src = bundle.getInt("Foodsrc");
        final float  base_rice = food.getPrice();
        imageFood.setImageResource(src);
        txtName.setText(food.getName());
        txtPrice.setText(food.getPrice()+"");
        txtDescription.setText(food.getDescription());

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
                    Toast.makeText(OrderFoodActivity.this, "Can not order less than 0",
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
        imageFood = findViewById(R.id.imgFood);
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