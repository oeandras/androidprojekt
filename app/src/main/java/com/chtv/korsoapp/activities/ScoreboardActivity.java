package com.chtv.korsoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.adapters.PlayerResultListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ScoreboardActivity extends AppCompatActivity {
    List<PlayerResult> playerResults;

    private Realm realm;

    private RecyclerView scores;
    private TextView sessionName;

    private String scoreBoardId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            scoreBoardId = b.getString("scoreboard");
            sessionId = b.getString("session");
        }



        setContentView(R.layout.activity_scoreboard);
        scores = (RecyclerView) findViewById(R.id.score_list);
        sessionName = (TextView) findViewById(R.id.scoreboard_name_textview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.realm = Realm.getDefaultInstance();
        List<PlayerResult> results = realm.where(Scoreboard.class).equalTo("scoreboardId", scoreBoardId).findFirst().getPlayerResults();
        PlayerResultListAdapter adapter = new PlayerResultListAdapter(results);
        scores.setAdapter(adapter);
        sessionName.setText(realm.where(Scoreboard.class).equalTo("scoreboardId", scoreBoardId).findFirst().getName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.realm.close();
    }
}
