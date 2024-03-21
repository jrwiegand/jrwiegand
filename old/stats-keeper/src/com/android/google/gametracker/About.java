package com.android.google.gametracker;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;

public class About extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.about);
		
		// Sending an email.
		Preference emailSuggestion = (Preference) findPreference("sendSuggestion");
		
		emailSuggestion.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				sendContent();
				return true;
			}
		});
	}
	private void sendContent() {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"jrwdeveloping@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "GameTracker Suggestion");
		i.putExtra(Intent.EXTRA_TEXT   , "");
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(About.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}	
	}
}