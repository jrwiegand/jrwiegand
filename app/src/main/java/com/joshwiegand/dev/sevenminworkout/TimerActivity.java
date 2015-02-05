package com.joshwiegand.dev.sevenminworkout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TimerActivity extends ActionBarActivity {

    private boolean CheckboxPreference;
    private String ListPreference;
    private String editTextPreference;
    private String ringtonePreference;
    private String secondEditTextPreference;
    private String customPref;
    private TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
    private Button startButton = (Button) findViewById(R.id.start_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Activity startActivty = new Activity();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** TODO: create a new Intent in source and the manifest */
                //Intent workoutActivity = new Intent(getBaseContext(), WorkoutActivity.class);
                //startActivity(workoutActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStartClick(View view) {
        Toast.makeText(this, "Starting now...", Toast.LENGTH_SHORT).show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_timer, container, false);
        }
    }

    /** Get the preferences of the user. */
    private void getPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        CheckboxPreference = prefs.getBoolean("checkboxPref", true);
        ListPreference = prefs.getString("listPref", "nr1");
        editTextPreference = prefs.getString("editTextPref", "Nothing has been entered");
        ringtonePreference = prefs.getString("ringtonePref", "DEFAULT_RINGTONE_URI");
        secondEditTextPreference = prefs.getString("SecondEditTextPref", "Nothing has been entered");

        // Get the custom preference
        SharedPreferences mySharedPreferences = getSharedPreferences("myCustomSharedPrefs", Activity.MODE_PRIVATE);
        customPref = mySharedPreferences.getString("myCusomPref", "");
    }
}
