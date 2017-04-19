package com.chtv.korsoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.Models.Scoreboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventListActivity extends AppCompatActivity {
    List<ContestEvent> contestEvents;
    String name;
    Date from;
    Date until;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contestEvents = new ArrayList<ContestEvent>();
        name = "";
        from = null;
        until = null;

        createMockData();

        setContentView(R.layout.activity_event_list);
    }

    /*Testing only*/
    void createMockData() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.YEAR, 2017);
        from.set(Calendar.MONTH, Calendar.APRIL);
        from.set(Calendar.DAY_OF_MONTH, 18);
        Date fromRepresentation = from.getTime();

        Calendar until = Calendar.getInstance();
        until.set(Calendar.YEAR, 2017);
        until.set(Calendar.MONTH, Calendar.APRIL);
        until.set(Calendar.DAY_OF_MONTH, 21);
        Date untilRepresentation = until.getTime();

        contestEvents.add(new ContestEvent("Test event 1", fromRepresentation, untilRepresentation));

        List<ContestSession> testSessions = new ArrayList<ContestSession>();

        testSessions.add(new ContestSession(contestEvents.get(0), "Test session 1"));
        testSessions.add(new ContestSession(contestEvents.get(0), "Test session 2"));

        List<Player> testPlayersForFirstSession = new ArrayList<Player>();
        testPlayersForFirstSession.add(new Player(testSessions.get(0), "Test player 1"));
        testPlayersForFirstSession.add(new Player(testSessions.get(0), "Test player 2"));
        List<Player> testPlayersForSecondSession = new ArrayList<Player>();
        testPlayersForFirstSession.add(new Player(testSessions.get(1), "Test player 3"));
        testPlayersForFirstSession.add(new Player(testSessions.get(1), "Test player 4"));

        List<Scoreboard> testScoreboardsForFirstSession = new ArrayList<Scoreboard>();
        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(0), "Test Scoreboard 1"));
        List<Scoreboard> testScoreboardsForSecondSession = new ArrayList<Scoreboard>();
        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(1), "Test Scoreboard 2"));
        testScoreboardsForFirstSession.add(new Scoreboard(testSessions.get(1), "Test Scoreboard 3"));

        List<PlayerResult> testPlayerResults = new ArrayList<PlayerResult>();
        testPlayerResults.add(new PlayerResult(testPlayersForFirstSession.get(0), testScoreboardsForFirstSession.get(0), 1));
        testPlayersForFirstSession.get(0).setPlayerResults(testPlayerResults);

        testPlayerResults.add(new PlayerResult(testPlayersForFirstSession.get(1), testScoreboardsForFirstSession.get(0), 2));
        testScoreboardsForFirstSession.get(0).setPlayerResults(testPlayerResults);

        testPlayerResults.remove(0);
        testPlayersForFirstSession.get(1).setPlayerResults(testPlayerResults);

        testSessions.get(0).setScoreboards(testScoreboardsForFirstSession);
        testSessions.get(0).setPlayers(testPlayersForFirstSession);
        testSessions.get(1).setScoreboards(testScoreboardsForSecondSession);
        testSessions.get(1).setPlayers(testPlayersForSecondSession);

        contestEvents.get(0).setContestSessions(testSessions);
    }
}
