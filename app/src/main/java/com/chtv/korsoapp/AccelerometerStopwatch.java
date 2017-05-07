package com.chtv.korsoapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;

import com.chtv.korsoapp.interfaces.IMeasurer;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cregz on 2017.05.06..
 */

public class AccelerometerStopwatch extends BaseMeasurer {
    private Timer timer;
    private long startTime;
    private long elapsedTime;

    private RezgesTarolo Ztarolo;

    private SensorManager sensorManager;

    public AccelerometerStopwatch(SensorManager sensorManager) {
        this.timer = new Timer();
        this.Ztarolo=new RezgesTarolo();
        this.sensorManager = sensorManager;
    }

    private void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
        setChanged();
        notifyObservers();
    }

    private void init() {
        this.elapsedTime=0;
        this.Ztarolo= new RezgesTarolo();
    }

    @Override
    public void startMeasure(long delay, long period) {
        init();
        this.startTime = SystemClock.elapsedRealtime();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setElapsedTime(SystemClock.elapsedRealtime() - startTime);
            }
        },delay, period);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void stopMeasure() {
        this.timer.cancel();
        sensorManager.unregisterListener(sensorListener);
        setChanged();
        notifyObservers(true);
    }

    @Override
    public long getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public void reset() {
        this.timer.cancel();
        init();
    }

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            Ztarolo.addValue(values[2]);
            if (Ztarolo.Utolsografertek() > Ztarolo.Atlag() + 2 || Ztarolo.Utolsografertek() < Ztarolo.Atlag() - 2) {
                stopMeasure();
            } else {
                //rezges.setText("semmi");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
