package com.chtv.korsoapp.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.Toast;

import com.chtv.korsoapp.AccelerometerStopwatch;
import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.ViewModels.MeasurerViewModel;
import com.chtv.korsoapp.databinding.ActivityMeasurerBinding;

import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MeasurerActivity extends AppCompatActivity implements MeasurerViewModel.IMeasurerView {
    Player player;
    ContestEvent event;
    ContestSession session;

    private MeasurerViewModel viewModel;

    Realm realm = Realm.getDefaultInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            String playerId = b.getString("player");
            if(!playerId.equals(new UUID(0,0).toString())) {
                RealmResults<Player> results = realm.where(Player.class).equalTo("playerId", playerId).findAll();
                this.player = results.first();

                String eventId = b.getString("event");
                RealmResults<ContestEvent> resultsEvent = realm.where(ContestEvent.class).equalTo("contestEventId", eventId).findAll();
                this.event = resultsEvent.first();

                String sessionId = b.getString("session");
                RealmResults<ContestSession> resultsSession = realm.where(ContestSession.class).equalTo("contestSessionId", sessionId).findAll();
                this.session = resultsSession.first();
            }
            else{
                this.event = new ContestEvent("Practice", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), new UUID(0,0).toString());
                this.session = new ContestSession(event, "Practice", new UUID(0,0).toString());
                this.player = new Player(session, "Teszt Elek", new UUID(0,0).toString());
            }
        }
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        viewModel = new MeasurerViewModel(player, this, new AccelerometerStopwatch(sensorManager));

        ActivityMeasurerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_measurer);
        binding.setViewModel(viewModel);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public void onStartCountDown() {

    }

    @Override
    public void onCountDownFinished() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    @Override
    public void onStartMeasure() {

    }

    @Override
    public void onStopMeasure() {
        Calendar cal = Calendar.getInstance().getInstance();
        cal.setTime(viewModel.PlayerScore);
        Toast.makeText(this, "Your time is: "+ cal.get(Calendar.SECOND)+" seconds.", Toast.LENGTH_SHORT).show();
        //don't save practice event
        if(!event.getContestEventId().equals(new UUID(0,0).toString())){
            realm.beginTransaction();
            Scoreboard scoreboard = realm.copyToRealm(new Scoreboard(session, "Scoreboard 1", UUID.randomUUID().toString()));
            PlayerResult result = realm.copyToRealm(new PlayerResult(player,scoreboard, viewModel.PlayerScore, UUID.randomUUID().toString()));
            player.getPlayerResults().add(result);
            scoreboard.getPlayerResults().add(result);
            realm.commitTransaction();
        }
    }

}
