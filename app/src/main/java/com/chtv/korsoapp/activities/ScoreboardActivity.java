package com.chtv.korsoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    List<PlayerResult> playerResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerResults = new ArrayList<PlayerResult>(); //TODO: Intent

        setContentView(R.layout.activity_scoreboard);
    }
}
