package com.ad340.nsc.soundapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String outputFile;
    private Button play;
    private ImageView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set audio stream to music
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        // find buttons for hooking to events
        play = (Button) findViewById(R.id.button_play);
        record = (ImageView) findViewById(R.id.image_record);

        // can't play audio until its recorded
        play.setEnabled(false);

        // register for touch events
        record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    // while a contact is down record audio
                    case (MotionEvent.ACTION_DOWN): {
                        try {
                            // instantiate media recorder
                            recorder = new MediaRecorder();
                            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                            recorder.setOutputFile(outputFile);
                            recorder.prepare();
                            recorder.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Recording failed to start", Toast.LENGTH_LONG).show();
                            return false;
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Recording failed to start", Toast.LENGTH_LONG).show();
                            return false;
                        }
                        // make the mic red
                        record.setColorFilter(Color.argb(255, 255, 0, 0));
                        play.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    // once the contact is released stop recording
                    case (MotionEvent.ACTION_UP):
                    case (MotionEvent.ACTION_CANCEL):
                    case (MotionEvent.ACTION_OUTSIDE): {
                        recorder.stop();
                        recorder.reset();
                        recorder.release();
                        // reset the mike to dark gray
                        record.setColorFilter(Color.argb(255, 127, 127, 127));
                        // now there is something to play
                        play.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });

        // register for the play button click
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                // create a media player to playback the recorded audio
                try {
                    // instantiate media player
                    player = new MediaPlayer();
                    player.setDataSource(outputFile);
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Playback failed to start", Toast.LENGTH_LONG).show();
                    return;
                }
                // play the audio
                play.setEnabled(false);
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        play.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Done playing audio", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
