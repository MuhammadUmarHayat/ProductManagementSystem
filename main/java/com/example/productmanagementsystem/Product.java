package com.example.productmanagementsystem;

public class Product
{
    int id;
    String name;
    int qty;
    int price;

    public Product() {
    }

    public Product(int id, String name, int qty, int price)
    {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public Product(String name, int qty, int price) {
        this.name = name;
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
