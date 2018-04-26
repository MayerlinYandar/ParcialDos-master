package com.example.jonmid.practicaborder.Parser;

import com.example.jonmid.practicaborder.Models.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonGame {
    public static List<Game> getData(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);
        List<Game> gameList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject item = jsonArray.getJSONObject(i);

            Game game = new Game();
            game.setName(item.getString("name"));
            game.setCharacter(item.getString("character"));
            game.setGameseries(item.getString("gameseries"));


            gameList.add(game);

        }

        return gameList;
    }

}
