package com.example.jonmid.practicaborder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonmid.practicaborder.Adapters.FoodAdapter;
import com.example.jonmid.practicaborder.Http.UrlManager;
import com.example.jonmid.practicaborder.Models.Food;
import com.example.jonmid.practicaborder.Models.Game;
import com.example.jonmid.practicaborder.Parser.JsonFood;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Atributos de clase iniciales
    FloatingActionButton Floating;
    //TextView textView;
    RecyclerView recyclerView;

    List<Food> foodList = new ArrayList<>();

    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.id_rcv_food);


        // Establcer la orientacion de RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();
    }

    /*
     * ONCLICK IR A GameActivity
     */
    public void onClickgoToGame(View view) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }


    // Metodo para validar la conexion a internet
    public Boolean isOnLine() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * EJECUTAR LA TAREA Y TRAER TODOS LOS DATOS DE LA TAREA ASINCRONA
     */
    public void loadData() {
        if (isOnLine()) {


            MyTask taskFood = new MyTask();
            taskFood.execute("http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3");
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    // *************************************************************************************

    public void processData() {
        foodAdapter = new FoodAdapter(foodList, getApplicationContext());
        recyclerView.setAdapter(foodAdapter);
    }

    // Tarea para traer los datos de post
    public class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String content = null;
            try {
                content = UrlManager.getDataJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                foodList = JsonFood.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

        }
    }


}