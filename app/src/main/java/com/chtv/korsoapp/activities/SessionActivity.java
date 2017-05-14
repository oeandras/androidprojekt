package com.chtv.korsoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class SessionActivity extends AppCompatActivity {
    Player selectedPlayer;
    String name;
    List<Player> players;
    private ContestSession session;

    private Realm realm;

    private TextView sessionName;
    private Button startButton;

    private String playerId;
    private String sessionId;
    private String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            playerId = b.getString("player");

            sessionId = b.getString("session");

            eventId = b.getString("event");
        }
        name = "";
        selectedPlayer = null;
        players = new ArrayList<Player>(); //TODO: Intent

        setContentView(R.layout.activity_session);

        sessionName = (TextView) findViewById(R.id.session_name_textview);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, MeasurerActivity.class);
                intent.putExtra("player", playerId);
                intent.putExtra("session", sessionId);
                intent.putExtra("event", eventId);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.realm = Realm.getDefaultInstance();

        selectedPlayer = realm.where(Player.class).equalTo("playerId", playerId).findFirst();
        session = realm.where(ContestSession.class).equalTo("contestSessionId", sessionId).findFirst();

        sessionName.setText(session.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.realm.close();
    }
}
