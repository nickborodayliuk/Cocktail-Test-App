package com.example.cocktailtestapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Post {
    private Drinks[] drinks;

    public Drinks[] getDrinks ()
    {
        return drinks;
    }

    public void setDrinks (Drinks[] drinks)
    {
        this.drinks = drinks;
    }



}
