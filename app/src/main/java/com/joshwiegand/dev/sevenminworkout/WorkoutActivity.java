package com.joshwiegand.dev.sevenminworkout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutActivity extends ActionBarActivity {

    private WorkoutTimer countDownTimer;
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

        Resources res = getResources();
        String[] exercises = res.getStringArray(R.array.exercises);

        final TextView callingActivityMessage = (TextView) findViewById(R.id.clock_text);

        callingActivityMessage.setText(exercises[0]);

    }

    public void onSendTimerData(View view) {
        TextView clockText = (TextView) findViewById(R.id.clock_text);

        String clockString = String.valueOf(clockText.getText());

        countDownTimer = new WorkoutTimer(startTime, interval);

        Intent goingBack = new Intent();

        setResult(RESULT_OK, goingBack);

        finish();
    }
}
