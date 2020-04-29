package com.example.cocktailtestapp.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailtestapp.R;
import com.example.cocktailtestapp.network.BDHelper;
import com.example.cocktailtestapp.network.Drinks;
import com.example.cocktailtestapp.network.DrinksLite;
import com.example.cocktailtestapp.network.NetworkService;
import com.example.cocktailtestapp.network.Post;
import com.example.cocktailtestapp.views.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, SearchView.OnQueryTextListener {

    private MyRecyclerViewAdapter adapter;
    private BDHelper dbHelper;
    private TextView enterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("");
        dbHelper = new BDHelper(this);
        enterText = findViewById(R.id.enter_text);
    }

    @Override
    public void onItemClick(View view, int position) {
        CocktailActivity.NAMECOCKTAIL = adapter.getItem(position);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("history", null, null, null, null, null, null);
        boolean b = true;
        if (c.moveToFirst()) {

            int nameColIndex = c.getColumnIndex("name");
            do {
                if (adapter.getItem(position).equals(c.getString(nameColIndex))){
                    b = false;
                }
            } while (c.moveToNext());
        }else {

        }
        c.close();
        if (b) {
            String s = adapter.getImage(position);
            s = s.replaceAll("https://www.thecocktaildb.com/images/media/drink/", "");
            ContentValues cv = new ContentValues();

            Log.e("es", s+" ");
            cv.put("name", adapter.getItem(position));
            cv.put("image", s);
            long rowID = db.insert("history", null, cv);
        }


        Intent intent = new Intent(SearchActivity.this, CocktailActivity.class);
        startActivity(intent);

    }

    public void addDrink(ArrayList<Drinks> cocktailsList){
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new MyRecyclerViewAdapter(this, cocktailsList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!query.equals("")) {
            if (query.charAt(query.length()-1)==32){
               query = removeLastCharOptional(query);
            }
            NetworkService.getInstance().getJSONApi().getPostByS(query).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                    Post post = response.body();
                    ArrayList<Drinks> cocktailsList = new ArrayList<>();
                    if (post.getDrinks()!=null) {
                        enterText.setVisibility(View.INVISIBLE);

                        for (int i = 0; i < post.getDrinks().length; i++) {
                            cocktailsList.add(post.getDrinks()[i]);
                        }
                        addDrink(cocktailsList);
                    }else {
                        enterText.setText("No cocktails found");
                        enterText.setVisibility(View.VISIBLE);
                        addDrink(cocktailsList);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }});
        }else {
            enterText.setText("Enter text to start search");
            enterText.setVisibility(View.VISIBLE);
        }
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String removeLastCharOptional(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }



}
