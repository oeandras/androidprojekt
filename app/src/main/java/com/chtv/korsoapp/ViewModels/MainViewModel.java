package com.chtv.korsoapp.ViewModels;

/**
 * Created by cregz on 2017.04.25..
 */

public class MainViewModel {

    private NewEventListener mNewEventListener;

    public void onNewPracticeClick(){
        mNewEventListener.onNewPracticeClick();
    }

    public void onNewEventClick(){
        mNewEventListener.onNewEventClick();
    }

    public MainViewModel(NewEventListener mNewEventListener) {
        this.mNewEventListener = mNewEventListener;
    }

    public interface NewEventListener{
        void onNewPracticeClick();
        void onNewEventClick();
    }
}
