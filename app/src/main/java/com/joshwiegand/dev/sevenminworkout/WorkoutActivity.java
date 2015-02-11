package com.joshwiegand.dev.sevenminworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class WorkoutActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_activity);

        Intent activityThatCalled = getIntent();

        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        TextView callingActivityMessage = (TextView) findViewById(R.id.clock_text);

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

}
