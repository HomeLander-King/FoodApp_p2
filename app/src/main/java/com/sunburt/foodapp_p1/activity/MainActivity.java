package com.sunburt.foodapp_p1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.sunburt.foodapp_p1.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    CardView cv1, cv2, cv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getControls();

        /**
         * Set action for toolbar
         */

        setSupportActionBar(toolbar);


        /**
         * Set action for navigation view
         */
        navView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodHomeActivity.class);
                startActivity(intent);
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrinkHomeActivity.class);
                startActivity(intent);
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getControls(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawLayer);
        navView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        cv1 = (CardView) findViewById(R.id.cv1);
        cv2 = (CardView) findViewById(R.id.cv2);
        cv3 = (CardView) findViewById(R.id.cv3);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int i = menuItem.getItemId();
        Intent intent;
        switch (i){
            case R.id.nav_food:
                intent = new Intent(getApplicationContext(), FoodHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_drink:
                intent = new Intent(getApplicationContext(), DrinkHomeActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_cart:
                intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}