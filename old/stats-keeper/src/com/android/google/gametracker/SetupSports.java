package com.android.google.gametracker;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class SetupSports extends PreferenceActivity{
	
	/**
	 * Called when the activity is first created.
	 */
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setup);
		
		Preference addHomePlayer = (Preference) findPreference("baseballAddHomePlayer");
		addHomePlayer.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				
				return true;
			}
			
		});
    }
}