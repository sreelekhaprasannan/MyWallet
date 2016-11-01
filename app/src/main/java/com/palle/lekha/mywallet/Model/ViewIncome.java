package com.palle.lekha.mywallet.Model;

/**
 * Created by Administrator on 08-10-2016.
 */

public class ViewIncome {

    private int id;
    private  String category, description, date;
    private double amount;

    public ViewIncome(int id, String date, String category, String description, double amount) {
        this.id= id;
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
