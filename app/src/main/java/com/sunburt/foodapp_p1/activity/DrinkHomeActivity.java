package com.sunburt.foodapp_p1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.adapter.DrinkHomeAdapter;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkHomeActivity extends AppCompatActivity {

    private RecyclerView revDrink;
    private BottomNavigationView btmNavigationDrink;
    private DrinkHomeAdapter adapter = null;
    private List<Drink> drinks = new ArrayList<>();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_home);

        getControls();

         //addData();

        // listen handle

        btmNavigationDrink.setSelectedItemId(R.id.nav_drink);

        btmNavigationDrink.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_food:
                        Intent intent1 = new Intent(getApplicationContext(), FoodHomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_cart:
                        Intent intent2 = new Intent(getApplicationContext(), CartActivity.class);
                        startActivity(intent2);
                        break;
//                    case R.id.nav_drink:
//                        Intent intent3 = new Intent(getApplicationContext(), DrinkHomeActivity.class);
//                        startActivity(intent3);
//                        break;
                }
                return false;
            }
        });

        btmNavigationDrink.setSelectedItemId(R.id.nav_drink);

        adapter.OnRecyclerClickListener(new DrinkHomeAdapter.OnRecyclerClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailDrinkActivity.class);
                if (drinks.get(position) != null && drinks.size() != 0){
                    intent.putExtra("Drink", drinks.get(position));
                }
                startActivity(intent);
            }
        });

    }

    private void addData()  {

        boolean checkAdd1 = db.addNewDrink("Lemon Juice", R.drawable.img_lemon_juice, "South Africa",
                "la nuoc trai cay lam tu qua chanh, mang lai cam giac sang khoai sau khi uong",
                12);

        boolean checkAdd2 = db.addNewDrink("Cocktail", R.drawable.img_cocktail, "France",
                "cocktail kombucha" + "ket hop voi chut ruou vang la  thuc uong tuyet voi",
                13);

        boolean checkAdd3 = db.addNewDrink("Coffee", R.drawable.img_coffee, "England",
                "Greate coffe from" +"England",14);

        boolean checkAdd4 = db.addNewDrink("Green Tea", R.drawable.img_greentea, "VietNam",
                "tra xanh giup" + "thanh loc co the mang lai cam giac de chiu", 15);

        boolean checkAdd5 = db.addNewDrink("Beer", R.drawable.img_beer,"Germany", "" +
                "chat luong cua bia duc luon la dang cap cua the gioi",16);

        boolean checkAdd6 = db.addNewDrink("Wine", R.drawable.img_wine, "American", "Wine" +
                "is one of the most favourite drink for men", 17);

         boolean checkAdd7 = db.addNewDrink("Cocacola", R.drawable.drink_coca, "America",
         "One of the most drink in the world", 12);

         boolean checkAdd8 = db.addNewDrink("Tequila", R.drawable.drink_tequila, "Mexico",
         "The most famous drink in Mexico", 13);

         boolean checkAdd9 = db.addNewDrink("Sujeonggwa", R.drawable.drink_sujeonggwa, "Korea",
         "A greate traditional drink of korean",21);

         boolean checkAdd10 = db.addNewDrink("Sangria", R.drawable.drink_sangria, "Spain",
         "It depending on whether the wine used in the preparation is white or red", 14);

         boolean checkAdd11 = db.addNewDrink("Cendol", R.drawable.drink_cendol, "Indonesia",
         "Centol is made from coconut milk, cakes with artificial pandan flavor and jaggery."
                 , 12);

         boolean checkAdd12 = db.addNewDrink("Eggnog", R.drawable.drink_eggnog, "England",
         "It is a cocktail sugar and egg yolks that can be combined with incense, cinnamon ",
         14);

    }

    private void getControls() {
        revDrink = (RecyclerView) findViewById(R.id.revDrink);
        btmNavigationDrink = (BottomNavigationView) findViewById(R.id.btm_nav_drink);
        db = new DBHelper(this);drinks = db.getAllDrink();
        adapter = new DrinkHomeAdapter(DrinkHomeActivity.this, drinks);
        revDrink.setAdapter(adapter);
        revDrink.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}