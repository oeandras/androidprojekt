package com.chtv.korsoapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.RezgesTarolo;
import com.chtv.korsoapp.ViewModels.MainViewModel;
import com.chtv.korsoapp.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainViewModel.NewEventListener{

    private MainViewModel viewModel;

    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);
        player = new Player(null,"Teszt Elek", new UUID(0,0).toString());

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();

    }

    @Override
    public void onNewPracticeClick() {
        //todo: everything
        //a practice contestevent is created with pracitce contest session and practice player
        //not persisted to database
        ContestEvent event = new ContestEvent("Practice", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), new UUID(0,0).toString());
        ContestSession session = new ContestSession(event, "Practice", new UUID(0,0).toString());

        Intent intent = new Intent(this, MeasurerActivity.class);
        intent.putExtra("player", player.getPlayerId());
        intent.putExtra("event", event.getContestEventId());
        intent.putExtra("session", session.getContestSessionId());
        startActivity(intent);
    }

    @Override
    public void onNewEventClick() {
        Toast.makeText(this,"TODO New Event Click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("player", player.getPlayerId());
        startActivity(intent);
    }
}
