package com.abednego.entity;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * @author AbednegoSteven-1972009
 */
public class Items {
    private int id;
    private String name;
    private double price;
    private String currency;
    private String description;
    private Category category;

    public Items() {
    }

    public Items(int id, String name, double price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public String getCurrency(){
        Locale usa = new Locale("en", "US");
// Create a Currency instance for the Locale
        Currency dollars = Currency.getInstance(usa);
// Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
        String currency = dollarFormat.format(price);
        return currency;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
