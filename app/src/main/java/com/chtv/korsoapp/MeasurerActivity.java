package com.chtv.korsoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;

public class MeasurerActivity extends AppCompatActivity {
    Player player;
    int score; //TODO: Time format

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        player = null; //TODO: Intent

        setContentView(R.layout.activity_measurer);
    }
}
