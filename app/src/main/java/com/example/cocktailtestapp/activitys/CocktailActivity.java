package com.example.cocktailtestapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cocktailtestapp.R;
import com.example.cocktailtestapp.network.Drinks;
import com.example.cocktailtestapp.network.NetworkService;
import com.example.cocktailtestapp.network.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailActivity extends AppCompatActivity {
    public static String NAMECOCKTAIL;
    private ImageView coctailImage;
    private TextView nameStr;
    private TextView alcoholicStr;
    private TextView glassStr;
    private ArrayList<TextView> ingredientsText = new ArrayList<>();
    private ArrayList<TextView> proportionText = new ArrayList<>();
    private LinearLayout linearLayoutL;
    private LinearLayout linearLayoutR;
    private TextView instructionsText;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(NAMECOCKTAIL);
        setContentView(R.layout.activity_cocktail);
        coctailImage = findViewById(R.id.image_cocktail2);
        nameStr = findViewById(R.id.name_str);
        alcoholicStr = findViewById(R.id.alcoholic_str);
        glassStr = findViewById(R.id.glass_str);
        linearLayoutL = findViewById(R.id.ingredients1);
        linearLayoutR = findViewById(R.id.ingredients2);
        instructionsText = findViewById(R.id.instructions_str);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NetworkService.getInstance().getJSONApi().getPostByS(NAMECOCKTAIL).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                Post post = response.body();
                Drinks drinks = post.getDrinks()[0];
                setViewInfo(drinks.getStrDrink(), drinks.getStrAlcoholic(), drinks.getStrGlass(), drinks.getStrDrinkThumb());
                setViewIngredients(getIngredients(drinks), getProportions(drinks, getIngredients(drinks).size()));
                instructionsText.setText(drinks.getStrInstructions());
            }
            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                t.printStackTrace();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void setViewInfo(String name, String alcoholic, String glass, String imageStr){
        Glide
                .with(CocktailActivity.this)
                .load(imageStr)
                .into(coctailImage);
        nameStr.setText(name);
        alcoholicStr.setText(alcoholic);
        glassStr.setText(glass);
    }

    public void setViewIngredients(ArrayList<String> ingredients, ArrayList<String> proportions){
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientsText.add(new TextView(this));
            ingredientsText.get(i).setText(i + 1 + ". "+ingredients.get(i));
            ingredientsText.get(i).setTextSize(16);
            int dip = 28;
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,  getResources().getDisplayMetrics());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) px);
            layoutParams.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15,  getResources().getDisplayMetrics());
            ingredientsText.get(i).setLayoutParams(layoutParams);
            linearLayoutL.addView(ingredientsText.get(i));
            ingredientsText.get(i).setTextColor( -16777216);
            ingredientsText.get(i).setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));

            proportionText.add(new TextView(this));
            proportionText.get(i).setText(proportions.get(i));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) px);
            proportionText.get(i).setLayoutParams(layoutParams2);
            linearLayoutR.addView(proportionText.get(i));
            proportionText.get(i).setTextColor( -16777216);
            proportionText.get(i).setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        }
    }

    public ArrayList<String> getIngredients(Drinks drinks){
        ArrayList<String> ingredients = new ArrayList<>();

        if (drinks.getStrIngredient1()!=(null)){
            ingredients.add(drinks.getStrIngredient1());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient2()!=(null)){
            ingredients.add(drinks.getStrIngredient2());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient3()!=(null)){
            ingredients.add(drinks.getStrIngredient3());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient4()!=(null)){
            ingredients.add(drinks.getStrIngredient4());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient5()!=(null)){
            ingredients.add(drinks.getStrIngredient5());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient6()!=(null)){
            ingredients.add(drinks.getStrIngredient6());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient7()!=(null)){
            ingredients.add(drinks.getStrIngredient7());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient8()!=(null)){
            ingredients.add(drinks.getStrIngredient8());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient9()!=(null)){
            ingredients.add(drinks.getStrIngredient9());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient10()!=(null)){
            ingredients.add(drinks.getStrIngredient10());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient11()!=(null)){
            ingredients.add(drinks.getStrIngredient11());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient12()!=(null)){
            ingredients.add(drinks.getStrIngredient12());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient13()!=(null)){
            ingredients.add(drinks.getStrIngredient13());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient14()!=(null)){
            ingredients.add(drinks.getStrIngredient14());
        }else {
            return ingredients;
        }

        if (drinks.getStrIngredient15()!=(null)){
            ingredients.add(drinks.getStrIngredient15());
        }else {
            return ingredients;
        }

        return ingredients;
    }

    public ArrayList<String> getProportions(Drinks drinks, int length){
        ArrayList<String> proportions = new ArrayList<>();

        if (length>=1) {
            if (drinks.getStrMeasure1()!=(null)) {
                proportions.add(drinks.getStrMeasure1());
            }else {
                proportions.add("");
            }

        }else {
            return proportions;
        }
        if (length>=2) {
            if (drinks.getStrMeasure2()!=(null)) {
                proportions.add(drinks.getStrMeasure2());
            }else {
                proportions.add("");
            }

        }else {
            return proportions;
        }
        if (length>=3) {
            if (drinks.getStrMeasure3()!=(null)) {
                proportions.add(drinks.getStrMeasure3());
            }else {
                proportions.add("");
            }

        }else {
            return proportions;
        }
        if (length>=4) {
            if (drinks.getStrMeasure4()!=(null)) {
                proportions.add(drinks.getStrMeasure4());
            }else {
                proportions.add("");
            }

        }else {
            return proportions;
        }
        if (length>=5) {
            if (drinks.getStrMeasure5()!=(null)) {
                proportions.add(drinks.getStrMeasure5());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=6) {
            if (drinks.getStrMeasure6()!=(null)) {
                proportions.add(drinks.getStrMeasure6());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=7) {
            if (drinks.getStrMeasure7()!=(null)) {
                proportions.add(drinks.getStrMeasure7());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=8) {
            if (drinks.getStrMeasure8()!=(null)) {
                proportions.add(drinks.getStrMeasure8());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=9) {
            if (drinks.getStrMeasure9()!=(null)) {
                proportions.add(drinks.getStrMeasure9());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=10) {
            if (drinks.getStrMeasure10()!=(null)) {
                proportions.add(drinks.getStrMeasure10());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=11) {
            if (drinks.getStrMeasure11()!=(null)) {
                proportions.add(drinks.getStrMeasure11());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=12) {
            if (drinks.getStrMeasure12()!=(null)) {
                proportions.add(drinks.getStrMeasure12());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=13) {
            if (drinks.getStrMeasure13()!=(null)) {
                proportions.add(drinks.getStrMeasure13());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=14) {
            if (drinks.getStrMeasure14()!=(null)) {
                proportions.add(drinks.getStrMeasure14());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        if (length>=15) {
            if (drinks.getStrMeasure15()!=(null)) {
                proportions.add(drinks.getStrMeasure15());
            }else {
                proportions.add("");
            }
        }else {
            return proportions;
        }
        return proportions;
    }

}
