package com.example.cocktailtestapp.network;



public class DrinksLite {
    private String strDrink;
    private String strDrinkThumb;

    public DrinksLite(){

    }
    public DrinksLite(String strDrink, String strDrinkThumb){
        this.strDrink = strDrink;
        this.strDrinkThumb = strDrinkThumb;
    }
    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public String getStrDrink() {
        return strDrink;
    }
}
