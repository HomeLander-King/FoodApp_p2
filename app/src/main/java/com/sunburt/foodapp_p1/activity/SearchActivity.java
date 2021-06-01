package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.adapter.ListDrinkSearchAdapter;
import com.sunburt.foodapp_p1.adapter.ListFoodSearchAdapter;
import com.sunburt.foodapp_p1.database.DBHelper;
import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Example;
import com.sunburt.foodapp_p1.model.Food;
import com.sunburt.foodapp_p1.model.Main;
import com.sunburt.foodapp_p1.model.WeatherApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private EditText editSearch;
    private ImageButton btnSearch;
    private RecyclerView revSearchDrink, revSearchFood, revResFood, revResDrink;
    private TextView txtTemp;
    private DBHelper db;
    private ListDrinkSearchAdapter drinkSearchAdapter = null;
    private ListFoodSearchAdapter foodSearchAdapter = null;
    private List<Food> foods, listFoodSearch;
    private List<Drink> drinks, listDrinkSearch;

    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String api_key = "044de447fe97e1ee78adb3422b381d35";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getControls();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reloadList();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org/data/2.5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WeatherApi weatherApi = retrofit.create(WeatherApi.class);
                Call<Example> exampleCall = weatherApi.getWeather(editSearch.getText().toString(), api_key);
                exampleCall.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.code() == 404){
                            Toast.makeText(SearchActivity.this, "No result search", Toast.LENGTH_SHORT).show();
                        }
                        Example myData = response.body();
                        Main main = myData.getMain();
                        Double temp = main.getTemp();
                        Integer temperature = (int) (temp-273.15);
                        txtTemp.setText("Temperature of "+editSearch.getText().toString()+": "+ temperature);
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                foods = db.getFoodByRegion(editSearch.getText().toString());
                foodSearchAdapter = new ListFoodSearchAdapter(SearchActivity.this, foods);
                revResFood.setAdapter(foodSearchAdapter);
                revResFood.setLayoutManager(new LinearLayoutManager(SearchActivity.this,
                        RecyclerView.HORIZONTAL,
                        false));

                foodSearchAdapter.OnRecyclerClickListener(new ListFoodSearchAdapter.OnRecyclerClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getApplicationContext(), DetailFoodActivity.class);
                        intent.putExtra("Food", foods.get(position));
                        startActivity(intent);
                    }
                });


                drinks = db.getDrinkByRegion(editSearch.getText().toString());
                drinkSearchAdapter = new ListDrinkSearchAdapter(SearchActivity.this, drinks);
                revResDrink.setAdapter(drinkSearchAdapter);
                revResDrink.setLayoutManager(new LinearLayoutManager(SearchActivity.this,
                        RecyclerView.HORIZONTAL, false));

                drinkSearchAdapter.OnRecyclerClickListener(new ListDrinkSearchAdapter.OnRecyclerClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getApplicationContext(), DetailDrinkActivity.class);
                        intent.putExtra("Drink", drinks.get(position));
                        startActivity(intent);
                    }
                });


                // reloadList();
            }
        });

        drinkSearchAdapter.OnRecyclerClickListener(new ListDrinkSearchAdapter.OnRecyclerClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailDrinkActivity.class);
                intent.putExtra("Drink", drinks.get(position));
                startActivity(intent);
            }
        });

        foodSearchAdapter.OnRecyclerClickListener(new ListFoodSearchAdapter.OnRecyclerClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailFoodActivity.class);
                intent.putExtra("Food", foods.get(position));
                startActivity(intent);
            }
        });
    }

    private void getControls() {
        editSearch = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.btnSearch);
        revSearchDrink = findViewById(R.id.revSearchDrink);
        revSearchFood = findViewById(R.id.revSearchFood);
        revResFood = findViewById(R.id.revResFood);
        revResDrink = findViewById(R.id.revResDrink);
        txtTemp = findViewById(R.id.txtTemp);
        db = new DBHelper(this);

        /**
         * set layout for list food
         */
        foods = db.getAllFood();
        foodSearchAdapter = new ListFoodSearchAdapter(SearchActivity.this, foods);
        revSearchFood.setAdapter(foodSearchAdapter);
        revSearchFood.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        /**
         * set layout for list drink
         */
        drinks = db.getAllDrink();
        drinkSearchAdapter = new ListDrinkSearchAdapter(SearchActivity.this, drinks);
        revSearchDrink.setAdapter(drinkSearchAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false);
        revSearchDrink.setLayoutManager(manager);
    }

    private void reloadList() {
        foods.clear();
        foods = db.getFoodByRegion(editSearch.getText().toString());
        foodSearchAdapter.notifyDataSetChanged();

        drinks.clear();
        drinks = db.getDrinkByRegion(editSearch.getText().toString());
        drinkSearchAdapter.notifyDataSetChanged();
    }
}