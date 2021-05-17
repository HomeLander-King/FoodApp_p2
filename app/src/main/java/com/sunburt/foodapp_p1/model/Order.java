package com.sunburt.foodapp_p1.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String name;
    private int imgsrc;
    private int number;
    private float price;

    public Order() {
    }

    public Order(int id, String name, int imgsrc, int number, float price) {
        this.id = id;
        this.name = name;
        this.imgsrc = imgsrc;
        this.number = number;
        this.price = price;
    }

    public Order(String name, int imgsrc, int number, float price) {
        this.name = name;
        this.imgsrc = imgsrc;
        this.number = number;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(int imgsrc) {
        this.imgsrc = imgsrc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
