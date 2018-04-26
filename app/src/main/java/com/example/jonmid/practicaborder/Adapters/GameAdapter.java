package com.example.jonmid.practicaborder.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonmid.practicaborder.Models.Game;
import com.example.jonmid.practicaborder.R;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    List<Game> gameList;
    Context context;

    public GameAdapter(List<Game> gameList, Context context) {
        this.gameList = gameList;
        this.context = context;
    }

    // ******************************************************************************

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Asignar los valores a la vista
        holder.textViewNameGame.setText(gameList.get(position).getName());
        holder.textViewCharacterGame.setText(gameList.get(position).getCharacter());
        holder.textViewGameSeriesGame.setText(gameList.get(position).getGameseries());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    // ******************************************************************************

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNameGame;
        TextView textViewCharacterGame;
        TextView textViewGameSeriesGame;


        public ViewHolder(View item) {
            super(item);

            textViewNameGame = (TextView) item.findViewById(R.id.id_txv_game_name);
            textViewCharacterGame = (TextView) item.findViewById(R.id.id_txv_game_character);
            textViewGameSeriesGame = (TextView) item.findViewById(R.id.id_txv_game_gameseries);
        }
    }
}