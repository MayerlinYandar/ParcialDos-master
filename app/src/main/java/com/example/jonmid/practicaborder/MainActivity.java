package com.example.jonmid.practicaborder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonmid.practicaborder.Http.UrlManager;
import com.example.jonmid.practicaborder.Models.Food;
import com.example.jonmid.practicaborder.Models.Game;
import com.example.jonmid.practicaborder.Parser.JsonFood;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    List<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }
    public void loadData(View view){
        if (isOnLine()){
            // Hacer llamado a la tarea
            MyTask task = new MyTask();
            task.execute("http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){

        Toast.makeText(this, String.valueOf(foodList.size()), Toast.LENGTH_SHORT).show();

        for(Food str : foodList) {
            textView.append(str.getTitle() + "\n");


        }
    }

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
