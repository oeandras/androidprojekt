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
import io.realm.RealmList;
import io.realm.RealmResults;

public class MeasurerActivity extends AppCompatActivity implements MeasurerViewModel.IMeasurerView {
    Player player;
    ContestEvent event;
    ContestSession session;

    private MeasurerViewModel viewModel;

    Realm realm = Realm.getDefaultInstance();

    private String playerId;
    private String eventId;
    private String sessionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            playerId = b.getString("player");
            eventId = b.getString("event");
            sessionId = b.getString("session");
        }

        this.realm = Realm.getDefaultInstance();

        if(!playerId.equals(new UUID(0,0).toString()))
            this.player = realm.where(Player.class).equalTo("playerId", playerId).findFirst();
        else
            this.player = new Player(session, "Practic Elek", new UUID(0,0).toString());
        if(!eventId.equals(new UUID(0,0).toString()))
            this.event = realm.where(ContestEvent.class).equalTo("contestEventId", eventId).findFirst();
        else
            this.event = new ContestEvent("Practice", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), new UUID(0,0).toString());
        if(!sessionId.equals(new UUID(0,0).toString()))
            this.session = realm.where(ContestSession.class).equalTo("contestSessionId", sessionId).findFirst();
        else
            this.session = new ContestSession(event, "Practice", new UUID(0,0).toString());

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        viewModel = new MeasurerViewModel(player, this, new AccelerometerStopwatch(sensorManager));

        ActivityMeasurerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_measurer);
        binding.setViewModel(viewModel);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
        if(realm.isClosed())
            this.realm=Realm.getDefaultInstance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
        this.realm.close();
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
            Scoreboard sb;
            boolean isThere = false;
            if(session.getScoreboards().size() > 0){
                sb = session.getScoreboards().get(session.getScoreboards().size() - 1);
                isThere = false;
            }else{
                sb = new Scoreboard(session, "Scoreboard 1", UUID.randomUUID().toString());
            }
            realm.beginTransaction();
            sb = realm.copyToRealmOrUpdate(sb);
            session.getScoreboards().add(sb);
            PlayerResult result = realm.copyToRealm(new PlayerResult(player,sb, viewModel.PlayerScore, UUID.randomUUID().toString()));

            if(player.getPlayerResults() != null)
                player.getPlayerResults().add(result);
            else
                player.setPlayerResults(new RealmList<PlayerResult>(result));

            if(sb.getPlayerResults() != null)
                sb.getPlayerResults().add(result);
            else
                sb.setPlayerResults(new RealmList<PlayerResult>(result));

            realm.commitTransaction();
        }
    }

}
