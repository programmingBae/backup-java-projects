package com.example.praktikum3ad.Model;

public class MenuItem {

    private String nama;
    private float price;
    private float discount;

    public MenuItem(String nama, float price, float discount) {
        this.nama = nama;
        this.price = price;
        this.discount = discount;
    }

    public MenuItem(String nama, float price) {
        this.nama = nama;
        this.price = price;
    }

    public MenuItem() {
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    private float getDiscountedPrice() {
        return this.price- (this.price * this.discount);
    }

    @Override
    public String toString() {
        if (this.discount != 0.0) {
            return nama + " " + getDiscountedPrice() + " (original price: " + price + " ) Discount: " + discount * 100 + "%";
        } else {
            return nama + " " + price;
        }
    }
}
