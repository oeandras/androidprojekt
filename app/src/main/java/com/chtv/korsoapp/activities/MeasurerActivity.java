package com.chtv.korsoapp.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.Toast;

import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.ViewModels.MeasurerViewModel;
import com.chtv.korsoapp.databinding.ActivityMeasurerBinding;

import java.util.Calendar;

public class MeasurerActivity extends AppCompatActivity implements MeasurerViewModel.IMeasurerView {
    Player player;
    int score; //TODO: Time format
    private MeasurerViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null)
            this.player = b.getParcelable("player");
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        viewModel = new MeasurerViewModel(player, sensorManager, this);

        ActivityMeasurerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_measurer);
        binding.setViewModel(viewModel);

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
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public void onStartCountDown() {

    }

    @Override
    public void onCountDownFinished() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    @Override
    public void onStartMeasure(long base) {

    }

    @Override
    public void onStopMeasure() {
        Calendar cal = Calendar.getInstance().getInstance();
        cal.setTime(viewModel.PlayerScore);
        Toast.makeText(this, "Your time is: "+ cal.get(Calendar.SECOND)+" seconds.", Toast.LENGTH_SHORT).show();
    }

}
