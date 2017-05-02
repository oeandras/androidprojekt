package com.chtv.korsoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.R;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends AppCompatActivity {
    Player selectedPlayer;
    String name;
    List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = "";
        selectedPlayer = null;
        players = new ArrayList<Player>(); //TODO: Intent

        setContentView(R.layout.activity_session);
    }
}
