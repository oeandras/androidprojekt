package com.chtv.korsoapp.interfaces;


/**
 * Created by cregz on 2017.05.06..
 */

public interface IMeasurer {
    void startMeasure(long delay, long period);
    void stopMeasure();
    long getElapsedTime();
    void reset();
}
