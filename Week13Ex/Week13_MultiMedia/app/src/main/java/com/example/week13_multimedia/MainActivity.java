package com.example.week13_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    private Button play, pause, stop;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.playBtn);
        pause = findViewById(R.id.pauseBtn);
        stop = findViewById(R.id.stopBtn);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(view);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause(view);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop(view);
            }
        });
    }



    public void play(View v) {
        intent = new Intent(this, AudioService.class);
        startService(intent);
        showToast("Player Started");
    }

    public void pause(View v) {
        if(intent!=null) {
            stopService(intent);
            showToast("Player Paused");
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            showToast("Player Stopped");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    private void showToast(String arg) {
        Toast.makeText(this, arg, Toast.LENGTH_SHORT).show();
    }
}