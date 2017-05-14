package com.chtv.korsoapp.app;

import android.app.Application;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.Models.Scoreboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

/**
 * Created by cregz on 2017.05.13..
 */

public class KorsoApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        final Calendar cal = Calendar.getInstance();
        final Random r = new Random();

        Realm.Transaction initialTransaction = new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Player player = realm.createObject(Player.class, UUID.randomUUID().toString());
                player.setName("Teszt Elek");


                Player p2 = realm.createObject(Player.class,UUID.randomUUID().toString());
                p2.setName("Iksz Dénes");


                Player p3 = realm.createObject(Player.class, UUID.randomUUID().toString());
                p3.setName("Nullre Ferenc");




                ContestEvent event1 = realm.createObject(ContestEvent.class, UUID.randomUUID().toString());
                event1.setName("Greatest event");
                event1.setFrom(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, 1);
                event1.setUntil(cal.getTime());


                ContestEvent event2 = realm.createObject(ContestEvent.class,UUID.randomUUID().toString());
                event2.setName("Greatest'er event");
                event2.setFrom(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, 1);
                event2.setUntil(cal.getTime());


                ContestEvent event3 = realm.createObject(ContestEvent.class,UUID.randomUUID().toString());
                event3.setName("Greatest'est event");
                event3.setFrom(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, 1);
                event3.setUntil(cal.getTime());

                ContestSession cs1 = realm.createObject(ContestSession.class,UUID.randomUUID().toString());
                cs1.setName("Session 1");
                cs1.setContestEvent(event1);

                cs1.setPlayers(new RealmList<Player>(player, p2, p3));

                ContestSession cs2 = realm.createObject(ContestSession.class,UUID.randomUUID().toString());
                cs2.setName("Session 2");
                cs2.setContestEvent(event1);
                cs2.setPlayers(new RealmList<Player>(player, p2, p3));

                event1.setContestSessions(new RealmList<ContestSession>(cs1, cs2));


                for(int i = 0; i<3; i++) {
                    Scoreboard sb = realm.createObject(Scoreboard.class, UUID.randomUUID().toString());
                    sb.setContestSession(cs1);
                    sb.setName("Scoreboard "+i);


                    ArrayList<PlayerResult> results = new ArrayList<>();

                    for(int j = 0; j < 5; j++) {
                        PlayerResult r1 = realm.createObject(PlayerResult.class, UUID.randomUUID().toString());
                        r1.setName(player.getName() + " " + i);
                        r1.setPlayer(player);
                        r1.setScoreboard(sb);
                        r1.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r1);


                        PlayerResult r2 = realm.createObject(PlayerResult.class, UUID.randomUUID().toString());
                        r2.setName(p2.getName() + " " + i);
                        r2.setPlayer(p2);
                        r2.setScoreboard(sb);
                        r2.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r2);

                        PlayerResult r3 = realm.createObject(PlayerResult.class,UUID.randomUUID().toString());
                        r3.setName(p3.getName() + " " + i);
                        r3.setPlayer(p3);
                        r3.setScoreboard(sb);
                        r3.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r3);
                    }

                    sb.setPlayerResults(new RealmList<PlayerResult>(results.toArray(new PlayerResult[results.size()])));
                }

                for(int i = 0; i<3; i++) {
                    Scoreboard sb = realm.createObject(Scoreboard.class, UUID.randomUUID().toString());
                    sb.setContestSession(cs2);
                    sb.setName("Scoreboard "+i);

                    ArrayList<PlayerResult> results = new ArrayList<>();

                    for(int j = 0; j < 5; j++) {
                        PlayerResult r1 = realm.createObject(PlayerResult.class, UUID.randomUUID().toString());
                        r1.setName(player.getName() + " " + i);
                        r1.setPlayer(player);
                        r1.setScoreboard(sb);
                        r1.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r1);


                        PlayerResult r2 = realm.createObject(PlayerResult.class, UUID.randomUUID().toString());
                        r2.setName(p2.getName() + " " + i);
                        r2.setPlayer(p2);
                        r2.setScoreboard(sb);
                        r2.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r2);

                        PlayerResult r3 = realm.createObject(PlayerResult.class, UUID.randomUUID().toString());
                        r3.setName(p3.getName() + " " + i);
                        r3.setPlayer(p3);
                        r3.setScoreboard(sb);
                        r3.setTime(new Date(r.nextInt(20000)+1000));
                        results.add(r3);
                    }

                    sb.setPlayerResults(new RealmList<PlayerResult>(results.toArray(new PlayerResult[results.size()])));
                }


            }
        };
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("TestRealm7")
                .initialData(initialTransaction)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
