package com.chtv.korsoapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.ViewModels.EventListViewModel;
import com.chtv.korsoapp.databinding.ActivityEventListBinding;
import com.chtv.korsoapp.events.ContestEventSelectedEvent;
import com.chtv.korsoapp.events.NewEventClick;
import com.chtv.korsoapp.events.ShowToastEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class EventListActivity extends AppCompatActivity {
    RealmList<ContestEvent> contestEvents;
    String name;
    Date from;
    Date until;

    private EventListViewModel viewModel;

    private Realm realm;

    private String playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        Bundle b = getIntent().getExtras();
        if(b != null) {
            playerId = b.getString("player");
        }
        contestEvents = new RealmList<ContestEvent>(realm.where(ContestEvent.class).findAll().toArray(new ContestEvent[realm.where(ContestEvent.class).findAll().size()]));
        name = "";
        from = null;
        until = null;

        //createMockData();

        ActivityEventListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_list);

        this.viewModel = new EventListViewModel(contestEvents);
        binding.setViewModel(viewModel);

        //setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onResume(){
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Subscribe
    public void onShowToastEvent(ShowToastEvent event){
        Toast.makeText(this, event.getMessage(), event.getIsLong() ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onContestEventSelected(ContestEventSelectedEvent contestEvent){
        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("contestEvent", contestEvent.getContestEvent().getContestEventId());
        intent.putExtra("player", playerId);
        startActivity(intent);

    }

    @Subscribe
    public void onNewEventClick(NewEventClick event){
        Intent intent = new Intent(this, NewEventActivity.class);
        startActivity(intent);
    }

    /*Testing only*/
//    void createMockData() {
//        Calendar from = Calendar.getInstance();
//        from.set(Calendar.YEAR, 2017);
//        from.set(Calendar.MONTH, Calendar.APRIL);
//        from.set(Calendar.DAY_OF_MONTH, 18);
//        Date fromRepresentation = from.getTime();
//
//        Calendar until = Calendar.getInstance();
//        until.set(Calendar.YEAR, 2017);
//        until.set(Calendar.MONTH, Calendar.APRIL);
//        until.set(Calendar.DAY_OF_MONTH, 21);
//        Date untilRepresentation = until.getTime();
//
//        contestEvents.add(new ContestEvent("Test event 1", fromRepresentation, untilRepresentation));
//
//        List<ContestSession> testSessions = new ArrayList<ContestSession>();
//
//        testSessions.add(new ContestSession(contestEvents.get(0), "Test session 1"));
//        testSessions.add(new ContestSession(contestEvents.get(0), "Test session 2"));
//
//        List<Player> testPlayersForFirstSession = new ArrayList<Player>();
//        testPlayersForFirstSession.add(new Player(testSessions.get(0), "Test player 1"));
//        testPlayersForFirstSession.add(new Player(testSessions.get(0), "Test player 2"));
//        List<Player> testPlayersForSecondSession = new ArrayList<Player>();
//        testPlayersForFirstSession.add(new Player(testSessions.get(1), "Test player 3"));
//        testPlayersForFirstSession.add(new Player(testSessions.get(1), "Test player 4"));
//
//        List<Scoreboard> testScoreboardsForFirstSession = new ArrayList<Scoreboard>();
//        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(0), "Test Scoreboard 1"));
//        List<Scoreboard> testScoreboardsForSecondSession = new ArrayList<Scoreboard>();
//        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(1), "Test Scoreboard 2"));
//        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(1), "Test Scoreboard 3"));
//
//        List<PlayerResult> testPlayerResults = new ArrayList<PlayerResult>();
//        testPlayerResults.add(new PlayerResult(testPlayersForFirstSession.get(0), testScoreboardsForFirstSession.get(0), new Date(1L)));
//        testPlayersForFirstSession.get(0).setPlayerResults(testPlayerResults);
//
//        testPlayerResults.add(new PlayerResult(testPlayersForFirstSession.get(1), testScoreboardsForFirstSession.get(0), new Date(2L)));
//        testScoreboardsForFirstSession.get(0).setPlayerResults(testPlayerResults);
//
//        testPlayerResults.remove(0);
//        testPlayersForFirstSession.get(1).setPlayerResults(testPlayerResults);
//
//        testSessions.get(0).setScoreboards(testScoreboardsForFirstSession);
//        testSessions.get(0).setPlayers(testPlayersForFirstSession);
//        testSessions.get(1).setScoreboards(testScoreboardsForSecondSession);
//        testSessions.get(1).setPlayers(testPlayersForSecondSession);
//
//        contestEvents.get(0).setContestSessions(testSessions);
//
//        this.viewModel = new EventListViewModel((ObservableList<ContestEvent>) contestEvents);
//    }
}
