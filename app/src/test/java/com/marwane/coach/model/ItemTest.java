package com.marwane.coach.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    private Item item = new Item("Pizza",2000);
    private String food = "Pizza";
    private Integer cal = 2000;

    @Test
    public void getItem() {
        assertEquals(food,item.getItem());
    }

    @Test
    public void getCalorie() {
        assertEquals(cal,item.getCalorie(),0.1);
    }
}