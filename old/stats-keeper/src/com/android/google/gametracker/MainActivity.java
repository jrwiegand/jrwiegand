package com.android.google.gametracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    
	/** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /** 
         * The button that starts the game. 
         */
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				openNewSportDialog();
			}
        });
        
        /** 
         * The button that starts the sport setup. 
         */
        Button setup = (Button) findViewById(R.id.setup);
        setup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getBaseContext(), SetupSports.class));
			}
        });
    }
    
    private void openNewSportDialog() {
    	new AlertDialog.Builder(this)
    	.setTitle(R.string.sports_picker)
    	.setItems(R.array.sports,new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialoginterface,int i) {
    			switch (i) {
    			case 0:
    				startActivity(new Intent(getBaseContext(), BaseballActivity.class));
    				break;
    			case 1:
    				startActivity(new Intent(getBaseContext(), BasketballActivity.class));
    				break;
    			case 2:
    				startActivity(new Intent(getBaseContext(), FootballActivity.class));
    				break;
    			default:
    				break;
    			}
    			
    		}
    	}).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.menu_settings:
        	startActivity(new Intent(getBaseContext(), Settings.class));
            return true;
        case R.id.menu_about:
        	startActivity(new Intent(getBaseContext(), About.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    /** 
     * Called when the activity is first started.
     */
    //protected void onStart() {}
    
    /** 
     * Called when the activity is restarted by the user.
     */
    //protected void onRestart() {}
    
    /** 
     * Called when the activity is resumed.
     */
    //protected void onResume() {}
    
    /** 
     * Called when the activity is paused by the user.
     */
    //protected void onPause() {}

    /** 
     * Called when the activity is stopped by the system.
     */
    //protected void onStop() {}

    /** 
     * Called when the activity is destroyed by the system.
     */
    //protected void onDestroy() {}
}