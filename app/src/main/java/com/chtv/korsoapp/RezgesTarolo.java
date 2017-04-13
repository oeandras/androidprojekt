package com.chtv.korsoapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tata on 2017.04.13..
 */

public class RezgesTarolo {
    public final static int MAX_VALUES=50;

    public RezgesTarolo()
    {
        init();
    }

    public Float Utolsografertek() {
        return values.get(values.size()-1);
    }

    public Float Atlag()
    {
        float osszeg=0;
        for (int i=20;i<30;i++)
        {
            osszeg=osszeg+values.get(i);
        }
        return osszeg/10;
    }

    private List<Float> values;

    private void init(){
        this.values=new ArrayList<>(MAX_VALUES);
        for (int i=0; i<MAX_VALUES;i++)
            this.values.add(0f);
    }

    public void addValue(float value){
        this.values.remove(0);
        this.values.add(value);
    }
}
