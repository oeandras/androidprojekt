package com.chtv.korsoapp.events;

import android.widget.Toast;

/**
 * Created by cregz on 2017.05.07..
 */

public class ShowToastEvent {
    private String message;
    private boolean isLong;

    public String getMessage() {
        return message;
    }

    public boolean getIsLong() {
        return isLong;
    }

    public ShowToastEvent(String message, boolean isLong){
        this.message = message;
        this.isLong = isLong;
    }
}
