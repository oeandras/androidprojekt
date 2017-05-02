package com.chtv.korsoapp.ViewModels;

import android.databinding.BaseObservable;

/**
 * Created by cregz on 2017.05.02..
 */

public abstract class BaseViewModel extends BaseObservable {
    public void onCreate(){}
    public void onStart(){}
    public void onRestart(){}
    public void onResume(){}
    public void onPause(){}
    public void onStop(){}
    public void onDestroy(){}

}
