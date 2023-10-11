package com.example.week13_multimedia;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class AudioService extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.demo2);
        player.setLooping(true); // You can set this to loop if needed
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!player.isPlaying()) {
            player.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
