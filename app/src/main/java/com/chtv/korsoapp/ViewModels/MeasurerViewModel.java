package com.chtv.korsoapp.ViewModels;

import android.databinding.Bindable;
import android.os.CountDownTimer;

import com.android.databinding.library.baseAdapters.BR;
import com.chtv.korsoapp.BaseMeasurer;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.interfaces.IMeasurer;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cregz on 2017.05.02..
 */

public class MeasurerViewModel extends BaseViewModel implements Observer {
    private Player player;
    private CountDownTimer countdownTimer;
    private long remainingTime;

    private IMeasurerView view;

    private long elapsedTime;

    private BaseMeasurer sw;

    //temporary
    //todo: remove
    public Date PlayerScore;

    private MeasurerViewModelState state;


    public String getPlayerName() {
        return player.getName();
    }

    @Bindable
    public String getRemainingTime() {
        return "0"+remainingTime/1000;
    }

    @Bindable
    public MeasurerViewModelState getState() {
        return state;
    }

    @Bindable
    public String getElapsedTimeString() {
        long elapsed = sw.getElapsedTime();
        return String.format("%02d:%02d.%03d",elapsed / 60000,(elapsed % 60000)/1000, (elapsed % 60000)%1000);
    }

    private void setState(MeasurerViewModelState state) {
        this.state = state;
        switch (state){
            case COUNT_DOWN:
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
                        setState(MeasurerViewModelState.MEASURE);
                        startMeasure();
                    }

                };
                countdownTimer.start();
                break;
            case MEASURE:
                startMeasure();
                break;
        }
        notifyPropertyChanged(BR.state);
    }

    private void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;

        notifyPropertyChanged(BR.elapsedTimeString);

    }

    public MeasurerViewModel(Player player, IMeasurerView iMeasurerView, BaseMeasurer stopwatch) {
        this.player = player;
        this.remainingTime = 5;
        this.view = iMeasurerView;
        this.sw = stopwatch;
        setState(MeasurerViewModelState.IDLE);

        //setState(MeasurerViewModelState.COUNT_DOWN);
    }

    @Override
    public void onResume() {
        sw.addObserver(this);
        setState(MeasurerViewModelState.COUNT_DOWN);
    }

    @Override
    public void onDestroy() {
        player = null;
        sw = null;
        countdownTimer = null;
        view = null;
    }

    @Override
    public void onPause() {
        sw.deleteObserver(this);
        if(state == MeasurerViewModelState.MEASURE) {
            sw.stopMeasure();
        }
    }

    private void startMeasure(){
        sw.startMeasure(0,50);
    }

    //force stop e.g activity loses focus..
    private void stopMeasure(){
        view.onStopMeasure();
    }

    //sensorlistener stops measure
    private void measureFinished(){
        //TODO: save result for player
        PlayerScore = new Date(sw.getElapsedTime());
        view.onStopMeasure();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o==sw){
            notifyPropertyChanged(BR.elapsedTimeString);
            if(arg != null && arg.getClass() == Boolean.class && (Boolean) arg){
                measureFinished();
            }
        }
    }


    public enum MeasurerViewModelState{
        COUNT_DOWN,
        MEASURE,
        IDLE
    }

    //nincs oteletem hogy kene hivni
    public interface IMeasurerView {
        void onStartCountDown();
        void onCountDownFinished();

        void onStartMeasure(long base);
        void onStopMeasure();

    }

}
