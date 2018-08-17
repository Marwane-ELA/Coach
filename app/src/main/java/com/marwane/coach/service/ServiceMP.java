package com.marwane.coach.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.marwane.coach.R;


/***
 * Bronnen:
 * https://www.youtube.com/watch?v=p2ffzsCqrs8
 * https://developer.android.com/reference/android/app/Service
 */
public class ServiceMP extends Service {

    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player = MediaPlayer.create(this, R.raw.growup);
        player.start();

        return START_STICKY;

    }

    public MediaPlayer getPlayer() {
        return player;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.stop();
    }
}
