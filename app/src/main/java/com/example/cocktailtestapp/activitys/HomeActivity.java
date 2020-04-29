package com.example.cocktailtestapp.activitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailtestapp.R;
import com.example.cocktailtestapp.network.BDHelper;
import com.example.cocktailtestapp.network.Drinks;
import com.example.cocktailtestapp.network.DrinksLite;
import com.example.cocktailtestapp.views.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, View.OnClickListener {
    private MyRecyclerViewAdapter adapter;
    private ImageButton searchButton;
    private BDHelper bdHelper;
    private TextView historyIsEnpty;
    private MyTask myTask;
    ArrayList<DrinksLite> cocktailsList = new ArrayList<>();
    SQLiteDatabase db;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        historyIsEnpty = findViewById(R.id.history_is_empty);

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(this::onClick);
        myTask = new MyTask();
        myTask.execute();


    }
    public void addDrink(ArrayList<DrinksLite> cocktailsList){
        if (cocktailsList.size()>0){
            historyIsEnpty.setVisibility(View.INVISIBLE);
        }else {
            historyIsEnpty.setVisibility(View.VISIBLE);
        }
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new MyRecyclerViewAdapter(this, cocktailsList, 0);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        CocktailActivity.NAMECOCKTAIL = adapter.getItem(position);
        Intent intent = new Intent(HomeActivity.this, CocktailActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == searchButton.getId()){
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        openQuitDialog();
    }
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                HomeActivity.this);
        quitDialog.setTitle("Exit: Are you sure?");

        quitDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();

            }
        });

        quitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }
    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            bdHelper = new BDHelper(HomeActivity.this);
            db = bdHelper.getWritableDatabase();




            Cursor c = db.query("history", null, null, null, null, null, null);
            if (c.moveToFirst()) {
                int nameColIndex = c.getColumnIndex("name");
                int imageColIndex = c.getColumnIndex("image");
                do {
                    
                    cocktailsList.add(new DrinksLite(c.getString(nameColIndex), "https://www.thecocktaildb.com/images/media/drink/"+c.getString(imageColIndex)));

                } while (c.moveToNext());
            }
            c.close();
            bdHelper.close();
            for (int i = 0; i < 16; i++) {
                //cocktailsList.add(new DrinksLite("Margarita", "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"));
            }
            addDrink(cocktailsList);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }
}
