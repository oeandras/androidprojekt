package com.chtv.korsoapp.activities;

import android.databinding.DataBindingUtil;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.ViewModels.MeasurerViewModel;
import com.chtv.korsoapp.databinding.ActivityMeasurerBinding;
import com.chtv.korsoapp.fragments.CountUpFragment;
import com.chtv.korsoapp.fragments.CountdownTimerFragment;

public class MeasurerActivity extends AppCompatActivity implements CountdownTimerFragment.OnCountDownTimerFragmentInteractionListener, CountUpFragment.OnCountUpFragmentInteractionListener,MeasurerViewModel.IMeasurerView {
    Player player;
    int score; //TODO: Time format
    private MeasurerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Bundle b = getIntent().getExtras();
        if(b != null)
            this.player = b.getParcelable("player");
        viewModel = new MeasurerViewModel(player,sensorManager, this);

        ActivityMeasurerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_measurer);
        binding.setViewModel(viewModel);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CountdownTimerFragment countdownTimerFragment = CountdownTimerFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, countdownTimerFragment);
        fragmentTransaction.commit();
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
    public MeasurerViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCountDownFinished() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    @Override
    public void onStartMeasure() {
        //start fragment with chronometer
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CountUpFragment countUpFragment = CountUpFragment.newInstance("asd","jkl");
        fragmentTransaction.replace(R.id.fragment_container, countUpFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onStopMeasure() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //// TODO: torolni
    }
}
