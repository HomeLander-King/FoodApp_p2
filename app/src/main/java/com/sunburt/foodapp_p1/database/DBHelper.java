package com.sunburt.foodapp_p1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sunburt.foodapp_p1.model.Drink;
import com.sunburt.foodapp_p1.model.Food;
import com.sunburt.foodapp_p1.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "food_app.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
        SQLiteDatabase MyDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE tbl_food(food_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "food_name TEXT, food_imgsrc INT, food_region TEXT, food_description TEXT," +
                "food_price FLOAT)");

        MyDB.execSQL("CREATE TABLE tbl_drink(drink_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "drink_name TEXT, drink_imgsrc INT, drink_region TEXT, drink_description TEXT," +
                "drink_price FLOAT)");

        MyDB.execSQL("CREATE TABLE tbl_order(order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "order_name TEXT, order_img INT,order_number INT, order_price FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_food");
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_drink");
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_order");
    }

    // Add new food
    public boolean addNewFood(String name, int imgsrc, String region, String description, float price){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_name", name);
        contentValues.put("food_imgsrc", imgsrc);
        contentValues.put("food_region", region);
        contentValues.put("food_description", description);
        contentValues.put("food_price", price);
        long result = MyDB.insert("tbl_food", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Add new Drink
    public boolean addNewDrink(String name, int imgsrc, String region, String description, float price){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("drink_name", name);
        contentValues.put("drink_imgsrc", imgsrc);
        contentValues.put("drink_region", region);
        contentValues.put("drink_description", description);
        contentValues.put("drink_price", price);
        long result = MyDB.insert("tbl_drink", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Add new order
    public boolean addNewOrder(String name, int imgsrc, int number, float price){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_name", name);
        contentValues.put("order_img", imgsrc);
        contentValues.put("order_number", number);
        contentValues.put("order_price", price);
        long result = MyDB.insert("tbl_order", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Get All Food
    public List<Food> getAllFood(){
        List<Food> foods = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM tbl_food", null);
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            int imgsrc = cursor.getInt(2);
            String region = cursor.getString(3);
            String description = cursor.getString(4);
            float price = cursor.getFloat(5);
            Food food = new Food(name, imgsrc, region, description, price);
            foods.add(food);
        }
        return foods;
    }

    // Get all drink
    public List<Drink> getAllDrink(){
        List<Drink> drinks = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM tbl_drink", null);
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            int imgsrc = cursor.getInt(2);
            String region = cursor.getString(3);
            String description = cursor.getString(4);
            float price = cursor.getFloat(5);
            Drink drink = new Drink(name, imgsrc, region, description, price);
            drinks.add(drink);
        }
        return drinks;
    }

    // Get all order
    public List<Order> getAllOrder(){
        List<Order> listOrder = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT *FROM tbl_order", null);
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            int imgsrc = cursor.getInt(2);
            int number = cursor.getInt(3);
            float price = cursor.getFloat(4);
            Order order = new Order(name, imgsrc, number, price);
            listOrder.add(order);
        }

        return listOrder;
    }

    // Get  price
    public Float getOrderPrice(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT *FROM tbl_order", null);
        float price = 0 ;
        while (cursor.moveToNext()){
            price = cursor.getFloat(4);
        }
        return price;
    }

    // Update food
    public boolean updateFood(String name, int imgsrc, String region, String description, float price){
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_name", name);
        contentValues.put("food_imgsrc", imgsrc);
        contentValues.put("food_region", region);
        contentValues.put("food_description", description);
        contentValues.put("food_price", price);
        MyDB.update("tbl_food", contentValues, "food_name = ?", new String[]{name});
        return true;
    }


    //Update drink
    public boolean updateDrink(String name, int imgsrc, String region, String description, float price){
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("drink_name", name);
        contentValues.put("drink_imgsrc", imgsrc);
        contentValues.put("drink_region", region);
        contentValues.put("drink_description", description);
        contentValues.put("drink_price", price);
        MyDB.update("tbl_drink", contentValues, "drink_name = ?", new String[]{name});
        return true;
    }

    // Delete food
    public Integer deleteFood(String food_id){
        SQLiteDatabase MyDB = getWritableDatabase();
        return MyDB.delete("tbl_food", "ID = ?", new String[]{food_id});
    }

    // Delete drink
    public Integer deleteDrink(String drink_id){
        SQLiteDatabase MyDB = getWritableDatabase();
        return MyDB.delete("tbl_drink", "ID = ?", new String[]{drink_id});
    }

    // Delete all order
    public void deleteAllOrder(){
        SQLiteDatabase MyDB = getWritableDatabase();
        MyDB.execSQL("DELETE FROM tbl_order");
    }
}
