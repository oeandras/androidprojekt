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

public class MainActivity extends AppCompatActivity implements MainViewModel.NewEventListener{

    TextView ido, rezges;
    Button gomb;
    RezgesTarolo Ztarolo;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onNewPracticeClick() {
        Toast.makeText(this,"TODO New Practice Click", Toast.LENGTH_SHORT).show();
        ContestEvent event = new ContestEvent("Practice", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        ContestSession session = new ContestSession(event, "Practice");
        Player player = new Player(session, "Player1");
        Intent intent = new Intent(this, MeasurerActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    @Override
    public void onNewEventClick() {
        Toast.makeText(this,"TODO New Event Click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);
    }
}
