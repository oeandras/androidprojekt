package com.chtv.korsoapp.interfaces;


/**
 * Created by cregz on 2017.05.06..
 */

public interface IMeasurer {
    void init(long delay, long period);
    void startMeasure();
    void stopMeasure();
    long getElapsedTime();
    void reset();
}
