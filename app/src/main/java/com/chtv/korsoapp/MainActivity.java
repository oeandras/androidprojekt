package com.chtv.korsoapp;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView ido=(TextView) findViewById(R.id.ido);
        Button gomb=(Button) findViewById(R.id.gomb);
        final CountDownTimer asd= new CountDownTimer(5000, 50) {    //az 50 tűnik annak a bűvös számnak ahol a procit nem hajtja mint az állat és folyamatos a számláló

            public void onTick(long millisUntilFinished) {
                ido.setText("seconds remaining: "+"0"+millisUntilFinished/1000 +":"+ millisUntilFinished % 1000 );
            }

            public void onFinish() {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                ido.setText("done!");
            }
        };

        gomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd.start();
            }
        });





    }
}
