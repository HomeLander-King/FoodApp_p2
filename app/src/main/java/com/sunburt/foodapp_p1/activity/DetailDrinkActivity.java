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

public class DetailDrinkActivity extends AppCompatActivity {

    private ImageView imgDrink;
    private TextView txtName;
    private EditText editDescr, editRegion, editPrice;
    private Button btnUpdate, btnAddToCart, btnBackHomePage;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drink);

        getControls();

        Bundle bundle = getIntent().getExtras();
        Drink drink = (Drink) bundle.getSerializable("Drink");
        txtName.setText(drink.getName().toString());
        editDescr.setText(drink.getDescription().toString());
        imgDrink.setImageResource(drink.getImgsrc());
        editRegion.setText(drink.getRegion());
        editPrice.setText(String.valueOf(drink.getPrice()));

        final  int src = drink.getImgsrc();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String description = editDescr.getText().toString();
                String region = editRegion.getText().toString();
                float price = Float.parseFloat(editPrice.getText().toString());
                boolean isUpdated =  db.updateDrink(name, src, region, description, price);
                if (isUpdated = true)
                    Toast.makeText(DetailDrinkActivity.this, "Updated successfully",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailDrinkActivity.this, "Updated fail", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                Drink drink1 = new Drink(txtName.getText().toString(), src, editRegion.getText().toString(),
                        editDescr.getText().toString(), Float.parseFloat(editPrice.getText().toString()));
                intent.putExtra("mDrink", drink1);
                intent.putExtra("src", src);
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
        imgDrink = findViewById(R.id.imgDrink);
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