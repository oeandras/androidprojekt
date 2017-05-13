package com.chtv.korsoapp.ViewModels;

import io.realm.Realm;

/**
 * Created by cregz on 2017.04.25..
 */

public class MainViewModel extends BaseViewModel{

    private NewEventListener mNewEventListener;
    private Realm realm;

    public void onNewPracticeClick(){
        mNewEventListener.onNewPracticeClick();
    }

    public void onNewEventClick(){
        mNewEventListener.onNewEventClick();
    }

    public MainViewModel(NewEventListener mNewEventListener) {
        this.mNewEventListener = mNewEventListener;
    }

    @Override
    public void onResume(){
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void onPause(){
        realm.close();
    }
    public interface NewEventListener{
        void onNewPracticeClick();
        void onNewEventClick();
    }
}
