package com.example.lab2_ninemenmorris;

import com.lab2.graphical.Gameboard;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private SharedPreferences myPreferences;
	private static final String filename = "savedGameSession";
	private static final String PREFERENCES_NAME="SaveFile";
	private static final String IS_SAVED="FileSaved";
	private TextView txtSavedGame=null;
	private Button btnStartGame=null;
	private Button btnResetSavedGame=null;
	boolean isSaved=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStartGame = (Button) findViewById(R.id.btnStartGame);
		txtSavedGame = (TextView) findViewById(R.id.txtSavedGame);
		btnResetSavedGame = (Button) findViewById(R.id.btnResetSavedGame);
		
		myPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
		
		isSaved = myPreferences.getBoolean(IS_SAVED, false);
		
		if(isSaved){
			btnStartGame.setText("Resume game");
			txtSavedGame.setText("Game in progress");
			btnResetSavedGame.setVisibility(View.VISIBLE);
		}else{
			btnStartGame.setText("Start game");
			txtSavedGame.setText("");
		}
		
	}
	
	public void startGame(View v){
		switch(v.getId()){
		case R.id.btnStartGame:
			Intent i;
			i=new Intent(this,Gameboard.class);
			startActivity(i);
			//startActivityForResult(i,1);
			break;
		case R.id.btnResetSavedGame:
			SharedPreferences.Editor editor=myPreferences.edit();
			editor.putBoolean(IS_SAVED, false);
			editor.commit();
			btnResetSavedGame.setVisibility(View.INVISIBLE);
			btnStartGame.setText("Start Game");
			txtSavedGame.setText("");
			break;
		}
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(isSaved){
			btnStartGame.setText("Resume game");
			txtSavedGame.setText("Game in progress");
			btnResetSavedGame.setVisibility(View.VISIBLE);
		}else{
			btnStartGame.setText("Start game");
			txtSavedGame.setText("");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			myPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        	SharedPreferences.Editor editor=myPreferences.edit();
    		editor.putBoolean(IS_SAVED, false);
    		editor.commit();
			Intent i;
			i=new Intent(this,Gameboard.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
