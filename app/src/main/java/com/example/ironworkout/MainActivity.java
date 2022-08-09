package com.example.ironworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int workoutSeconds = 0;
    private boolean workoutStopWatchRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workoutStopWatchRunning = true;
        runWorkoutTimer();
    }

    public void showWorkoutMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.workout_menu, popup.getMenu());
        popup.show();
    }

    private void runWorkoutTimer() {
        final TextView workoutTimeView = findViewById(R.id.workout_time);
        final Handler workoutTimeHandler = new Handler();

        // Update time every second.
        workoutTimeHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!workoutStopWatchRunning) {
                    return;
                }

                int hours = workoutSeconds / 3600;
                int minutes = (workoutSeconds % 3600) / 60;
                int seconds = workoutSeconds % 60;

                String time;
                if (hours == 0) {
                    time = String.format(Locale.getDefault(), "%d:%02d", minutes, seconds);
                } else {
                    time = String.format(
                        Locale.getDefault(), "%d:%02d:%02d", hours, minutes, seconds);
                }

                workoutTimeView.setText(time);

                ++workoutSeconds;

                workoutTimeHandler.postDelayed(this, 1000);
            }
        });
    }
}