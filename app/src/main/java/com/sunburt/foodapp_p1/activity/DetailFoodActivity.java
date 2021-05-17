package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Food;

public class DetailFoodActivity extends AppCompatActivity {

    private ImageView imgFood;
    private TextView txtName;
    private EditText editDescr, editRegion, editPrice;
    private Button btnUpdate, btnAddToCart, btnBackHomePage;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        getControls();

        Bundle bundle = getIntent().getExtras();
        Food food = (Food) bundle.getSerializable("Food");
        txtName.setText(food.getName().toString());
        editDescr.setText(food.getDescription().toString());
        imgFood.setImageResource(food.getImgsrc());
        editRegion.setText(food.getRegion());
        editPrice.setText(String.valueOf(food.getPrice()));

        final  int src = food.getImgsrc();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String description = editDescr.getText().toString();
                String region = editRegion.getText().toString();
                float price = Float.parseFloat(editPrice.getText().toString());
                boolean isUpdated =  db.updateFood(name, src, region, description, price);
                if (isUpdated = true)
                    Toast.makeText(DetailFoodActivity.this, "Updated successfully",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailFoodActivity.this, "Updated fail", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderFoodActivity.class);
                Food food = new Food(txtName.getText().toString(), src, editRegion.getText().toString(),
                        editDescr.getText().toString(), Float.parseFloat(editPrice.getText().toString()));
                intent.putExtra("mFood", food);
                intent.putExtra("Foodsrc", src);
                startActivity(intent);
            }
        });

        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void getControls() {
        imgFood = findViewById(R.id.imgFood);
        txtName = findViewById(R.id.txtName);
        editDescr = findViewById(R.id.editDescription);
        editRegion = findViewById(R.id.editRegion);
        editPrice = findViewById(R.id.editPrice);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBackHomePage = findViewById(R.id.btnBackHome);
        db = new DBHelper(this);
    }
}