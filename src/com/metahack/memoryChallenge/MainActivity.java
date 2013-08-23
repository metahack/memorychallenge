package com.metahack.memoryChallenge;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.Toast;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

import com.metahack.memoryChallenge.R;

import android.widget.GridLayout;


public class MainActivity extends Activity {
	
	
	// backPressedCount helps keep an errant back button
	// press from erasing a game. I might be able to move
	// backPressedCount into a new subclassed onBackpress
	// as a value shared among instances
	static Integer backPressedCount = 0;

	static int turnPhase = 1;
	static Integer firstPhaseButton = null;
	static Integer secondPhaseButton = null;
	
	// probably don't need this locking code; listeners seem to be blocking on the main thread.
	static boolean lockedByClick = false;
	static boolean lastTurnWasMatch = false;
	static boolean firstTimeStarted = true;
	static boolean numbersVisible = false;
	
	// first is button ID, second is random number
	
	static Integer[][] buttonMap = new Integer[16][2];
	static Integer[] randomNumbers = new Integer[16];
	
	static Integer turn = 44;
	static Integer matches = 0;
	// if last score = 0, display last score as "-"
	static Integer lastScore = 0;
	public static Integer[] buttonIDs = {
		R.id.b01, R.id.b02, R.id.b03, R.id.b04,
		R.id.b05, R.id.b06, R.id.b07, R.id.b08,
		R.id.b09, R.id.b10, R.id.b11, R.id.b12,
		R.id.b13, R.id.b14, R.id.b15, R.id.b16
	};
	
	public static List<Integer> randomNumbersList = new ArrayList<Integer>();
	
	public static HashMap<Integer, GameButton> buttonObjectHash = new HashMap<Integer, GameButton>();
	
	public static HashMap<Integer, Integer> buttonHash = new HashMap<Integer, Integer>();
	//InitializeGame(MainActivity.this);
	//InitializeGame newgame = new InitializeGame();
	
	//GridLayout gridViewClick_Portrait = null;
	//GridLayout gridViewClick_Landscape = (GridLayout)findViewById(R.id.landscape);
	
	// this is the listener for number buttons
	OnClickListener listenForButtonClick = new View.OnClickListener() {
		public void onClick(View v) {
			EvaluateClick.evaluate(MainActivity.this, v);
		}
	};
	
	public void checkForNewGame(View v) {
		if (matches == 8) {
			InitializeGame.initialize(MainActivity.this);
			UpdateScreen.update(MainActivity.this);
		}
		//Toast.makeText(getBaseContext(), "!", Toast.LENGTH_SHORT).show();
	};
	
/*
	OnTouchListener listenForNewGame = new OnTouchListener() {
		@Override
		public void onTouch(View v, MotionEvent m) {
			
			if (matches == 8) {
				InitializeGame.initialize(MainActivity.this);
				UpdateScreen.update(MainActivity.this);
			}

			Toast.makeText(getBaseContext(), "!", Toast.LENGTH_SHORT).show();
		};
	};
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (firstTimeStarted) {
			
		InitializeGame.initialize(MainActivity.this);
		
		}
		firstTimeStarted = false;
		
		UpdateScreen.update(MainActivity.this);
		
	//	gridViewClick_Portrait = (GridLayout) findViewById(R.id.portrait);
		
		//START DELETE same code exists in InitializeGame
		for (int i = 0; i < 16; i++) {
			Button b = (Button) findViewById(MainActivity.buttonIDs[i]);
			if (MainActivity.buttonMap[i][1] == null) {
				b.setText("x" + i);
			}
			else {
			//	b.setText(MainActivity.buttonHash.get(MainActivity.buttonIDs[i]).toString());
		//		b.setText(MainActivity.buttonHash.get(b.getId()).toString());
			//b.setBackground(a.getResources().getDrawable(android.R.drawable.btn_default));
			//	b.setText( MainActivity.buttonMap[i][1].toString() );
			}
		}
		//END DELETE  same code exists in InitializeGame
		
		// this loop adds a listener to number buttons
		for(int i = 0; i < buttonIDs.length; i++){
			 Button button = (Button) findViewById(buttonIDs[i]);
			  button.setOnClickListener(listenForButtonClick);
			  
		}
		
		// test global listener for endGame
		

		//crashes app at the moment
//		gridViewClick_Portrait.setOnTouchListener();
		

		
		
	}
	

	
	
	// warns user that they will lose their game if
	// they press the back button again
	@Override
	public void onBackPressed() {
		
			if (backPressedCount == 0) {
			 backPressedCount = 1;
			 BackPressedCountReset reset = new BackPressedCountReset();

			 Toast.makeText(getBaseContext(), "tap Back again to reset and quit", Toast.LENGTH_SHORT).show();
			 reset.execute();
			 return;
			}
			else {
				backPressedCount = 0;
				System.exit(0);
				super.onBackPressed();	
				
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
	    // Handle item selection
         
 		final Dialog dialog = new Dialog(this);
 		dialog.setContentView(R.layout.rules);
 		dialog.setTitle("     Rules");
	// seTextViewt the custom dialog components - text, image and button

 		// 		dialog/ text = (TextView) dialog.findViewById(R.id.text);
 	//	text.setText("Clear all the tiles by matching pairs of numbers- " + 
 	//	"but each turn, all the numbers go up by 1.");
 		
 		
 		
// 		ImageView image = (ImageView) dialog.findViewById(R.id.image);
 	//	image.setImageResource(R.drawable.ic_launcher);

 		Button dialogButton = (Button) dialog.findViewById(R.id.dismissRules);
 		// if button is clicked, close the custom dialog
 		dialogButton.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				dialog.dismiss();
 			}
 		});

 		dialog.show();

         return true;
    }
	
	
}


