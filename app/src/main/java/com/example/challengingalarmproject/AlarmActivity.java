package com.example.challengingalarmproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvAlarm;
    Button btnStop;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Log.d("tslil", "Alarm activity");
        tvAlarm = (TextView) findViewById(R.id.tvAlarm);
        btnStop = findViewById(R.id.btmStop);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
    //פונקציה להפעלת והפסקת המוזיקה
    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this,R.raw.moana);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.release();
    }
}
