package com.chtv.korsoapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chtv.korsoapp.ViewModels.MainViewModel;
import com.chtv.korsoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainViewModel.NewEventListener{

    TextView ido, rezges;
    Button gomb;
    RezgesTarolo Ztarolo;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel(this));
//        setContentView(R.layout.activity_main);

//        ido=(TextView) findViewById(R.id.ido);
//        rezges=(TextView) findViewById(R.id.rezges);
//        gomb=(Button) findViewById(R.id.gomb);
//
//        Ztarolo=new RezgesTarolo();
//
//        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        final CountDownTimer asd= new CountDownTimer(5000, 50) {    //az 50 tűnik annak a bűvös számnak ahol a procit nem hajtja mint az állat és folyamatos a számláló
//
//            public void onTick(long millisUntilFinished) {
//                ido.setText("seconds remaining: "+"0"+millisUntilFinished/1000 +":"+ millisUntilFinished % 1000 );
//            }
//
//            public void onFinish() {
//                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                r.play();
//                ido.setText("done!");
//            }
//        };
//
//        gomb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                asd.start();
//            }
//        });
//
//        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();

//        sensorManager.unregisterListener(sensorListener);
    }

//    private SensorEventListener sensorListener = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            float[] values= event.values;
//            Ztarolo.addValue(values[2]);
//            if(Ztarolo.Utolsografertek()>Ztarolo.Atlag()+2 || Ztarolo.Utolsografertek()<Ztarolo.Atlag()-2)
//            {
//                rezges.setText("rezgés");
//            }
//            else
//            {
//                rezges.setText("semmi");
//            }
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//        }
//    };

    @Override
    public void onNewPracticeClick() {
        Toast.makeText(this,"TODO New Practice Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewEventClick() {
        Toast.makeText(this,"TODO New Event Click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);
    }
}
