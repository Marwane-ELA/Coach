package com.marwane.coach.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Marwane El Aissati on 01/05/2018
 */

public class ProfilTest {

    // creation profil
    private Profil profil = new Profil(new Date(),67,165,35,0);
    //resultat IMG
    private float img = (float)32.2;
    //message
    private String message = "trop élevé";


    @Test
    public void getImg() throws Exception {
        assertEquals(img, profil.getImg(),(float)0.1);
    }

    @Test
    public void getMessage() throws Exception {
        assertEquals(message,profil.getMessage());
    }
}