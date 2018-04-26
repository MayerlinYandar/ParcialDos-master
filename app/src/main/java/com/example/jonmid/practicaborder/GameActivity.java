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

import com.example.jonmid.practicaborder.Adapters.GameAdapter;
import com.example.jonmid.practicaborder.Http.UrlManager;
import com.example.jonmid.practicaborder.Models.Game;
import com.example.jonmid.practicaborder.Parser.JsonGame;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {


    FloatingActionButton Floating;
        RecyclerView recyclerView;

    TextView textView;
    List<Game> gameList = new ArrayList<>();
    GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        recyclerView = (RecyclerView) findViewById(R.id.id_rcv_game);

        // Establcer la orientacion de RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();
    }

    /*
     * ONCLICK IR A GameActivity
     */
    public void onClickgoToMain(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
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


            MyTask taskGame = new MyTask();
            taskGame.execute("http://www.amiiboapi.com/api/amiibo/");
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    // *************************************************************************************

    public void processData() {
        gameAdapter = new GameAdapter(gameList, getApplicationContext());
        recyclerView.setAdapter(gameAdapter);
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
                gameList = JsonGame.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

        }
    }

}

