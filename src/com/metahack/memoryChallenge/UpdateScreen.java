package com.metahack.memoryChallenge;

import android.app.Activity;
import android.widget.Button;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;

import com.metahack.memoryChallenge.R;

import android.widget.TextView;


public class UpdateScreen {
	
	public static void update(Activity a) {
		
		
		TextView turnsDisplay = (TextView) a.findViewById(R.id.turnCount);
		turnsDisplay.setText(MainActivity.turn.toString());
		
		for (Map.Entry<Integer, GameButton> entry : MainActivity.buttonObjectHash.entrySet() ) {
			Button b = (Button)a.findViewById(entry.getKey());
			
			
			if (entry.getValue().numberVisible) {
				
				if (entry.getValue().number < 10) {
					b.setText("0" + entry.getValue().number.toString());
				}
				else {
					b.setText(entry.getValue().number.toString());
				}
			}
			else {
				b.setText("");
			}
			b.setTextColor(entry.getValue().color);
			b.setVisibility(entry.getValue().visibility);
			b.setClickable(entry.getValue().clickable);
		}
		
	}

}
