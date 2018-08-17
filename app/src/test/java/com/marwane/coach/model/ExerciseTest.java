package com.marwane.coach.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseTest {

    private Exercise exercise = new Exercise(15,"cobra");
    private Integer id = 15;
    private String name = "cobra";

    @Test
    public void getImage_id() {
        assertEquals(id,exercise.getImage_id(),0.1);
    }

    @Test
    public void getName() {
        assertEquals(name,exercise.getName());
    }
}