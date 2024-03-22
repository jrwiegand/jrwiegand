package com.joshwiegand.dev.sevenminworkout;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutTimer extends CountDownTimer
{
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private long startTime;
    private long interval;

    public WorkoutTimer(long startTime, long interval)
    {
        super(startTime, interval);

        this.startTime = startTime;
        this.interval = interval;
    }

    @Override
    public void onFinish()
    {
        text.setText("Time's up!");
        timeElapsedView.setText("Time Elapsed: " + String.valueOf(this));
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        text.setText("Time remaining:" + millisUntilFinished);
        timeElapsed = startTime - millisUntilFinished;
        timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}