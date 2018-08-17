package com.marwane.coach.model;

public class Item {

    private String item;
    private int calorie;

    public Item(String item, int calorie) {
        this.item = item;
        this.calorie = calorie;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
