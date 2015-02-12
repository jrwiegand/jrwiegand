package com.joshwiegand.dev.sevenminworkout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutActivity extends ActionBarActivity {

    private WorkoutCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;

    private final long startTime = 50000;
    private final long interval = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_activity);

        Intent activityThatCalled = getIntent();

        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        final TextView callingActivityMessage = (TextView) findViewById(R.id.clock_text);

        callingActivityMessage.setText(" " + previousActivity);

    }

    public void onSendTimerData(View view) {
        TextView clockText = (TextView) findViewById(R.id.clock_text);

        String clockString = String.valueOf(clockText.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("clockString", clockString);
        setResult(RESULT_OK, goingBack);

        finish();
    }

    public class WorkoutCountDownTimer extends CountDownTimer
    {

        public WorkoutCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onFinish()
        {
            text.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            text.setText("Time remain:" + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
        }
    }
}
