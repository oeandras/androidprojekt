package com.chtv.korsoapp;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chtv.korsoapp.Models.ContestEvent;

import java.util.Date;

/**
 * Created by cregz on 2017.04.25..
 */

public class RecyclerViewBinders {

    @BindingAdapter("items")
    public static void setItems(RecyclerView view, ObservableList<ContestEvent> items){
        RecyclerView.Adapter<?> adapter = view.getAdapter();
        if(adapter == null){
            adapter = new ContestEventAdapter(items);
            view.setAdapter(adapter);
        }
        else{
            Log.d("info", "adapter is not null");
        }
    }

    @BindingConversion
    public static String convertDateToString(Date date) {
        return date.toString();
    }
}