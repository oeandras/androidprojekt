package com.chtv.korsoapp.ViewModels;

import android.databinding.Bindable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;

import com.android.databinding.library.baseAdapters.BR;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.RezgesTarolo;

/**
 * Created by cregz on 2017.05.02..
 */

public class MeasurerViewModel extends BaseViewModel {
    private Player player;
    private SensorManager sensorManager;
    private CountDownTimer countdownTimer;
    private long remainingTime;
    private IMeasurerView view;
    private RezgesTarolo Ztarolo;
    private MeasurerViewModelState state;


    public String getPlayerName() {
        return player.getName();
    }

    @Bindable
    public String getRemainingTime() {
        return "0"+remainingTime/1000;
    }

    public MeasurerViewModel(Player player, SensorManager sensorManager, IMeasurerView iMeasurerView) {
        this.player = player;
        this.remainingTime = 0;
        this.sensorManager = sensorManager;
        this.view = iMeasurerView;
        this.countdownTimer = new CountDownTimer(5000, 1000) {    //az 50 tűnik annak a bűvös számnak ahol a procit nem hajtja mint az állat és folyamatos a számláló
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                notifyPropertyChanged(BR.remainingTime);
            }

            @Override
            public void onFinish() {
                remainingTime=0;
                notifyPropertyChanged(BR.remainingTime);
                view.onCountDownFinished();
            }

        };
        this.Ztarolo = new RezgesTarolo();
        state = MeasurerViewModelState.COUNT_DOWN;
    }

    @Override
    public void onResume() {
        countdownTimer.start();
    }

    @Override
    public void onDestroy() {
        player = null;
        sensorManager = null;
        countdownTimer = null;
        view = null;
    }

    @Override
    public void onPause() {
        if(state == MeasurerViewModelState.MEASURE) {
            sensorManager.unregisterListener(sensorListener);
        }
    }

    private void startMeasure(){
        state = MeasurerViewModelState.MEASURE;
        view.onStartMeasure();
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stopMeasure(){

    }

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            Ztarolo.addValue(values[2]);
            if (Ztarolo.Utolsografertek() > Ztarolo.Atlag() + 2 || Ztarolo.Utolsografertek() < Ztarolo.Atlag() - 2) {
                view.onStopMeasure();
            } else {
                //rezges.setText("semmi");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public enum MeasurerViewModelState{
        COUNT_DOWN,
        MEASURE
    }

    //nincs oteletem hogy kene hivni
    public interface IMeasurerView {
        void onCountDownFinished();

        void onStartMeasure();
        void onStopMeasure();

    }
}
