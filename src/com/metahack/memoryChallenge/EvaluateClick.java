package com.metahack.memoryChallenge;

import android.app.Activity;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.HapticFeedbackConstants;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class EvaluateClick {
	
	public static void evaluate(Activity a, View v) {
		
		//v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
		
		
		// exits if game has been won and user keeps tapping buttons
		if (MainActivity.matches == 8) {
			InitializeGame.initialize(a);
			UpdateScreen.update(a);
			return;
		}
		
		v.playSoundEffect(SoundEffectConstants.CLICK);
		
		Button b = (Button)v;
		GameButton bb = MainActivity.buttonObjectHash.get(b.getId());
		
		
		// This is taken care of by UpdateScreen so it was commented out
		//TextView turnsDisplay = (TextView) a.findViewById(R.id.turnCount);
		
		if (MainActivity.turnPhase == 1) {
			if (MainActivity.lockedByClick) { return; }
			MainActivity.lockedByClick = true;
			
			MainActivity.turn++;
			UpdateNumbers.update();
			UpdateScreen.update(a);

			// This is taken care of by UpdateScreen so it was commented out
			//turnsDisplay.setText(MainActivity.turn.toString());
			
			
			// assuming we're on turn two...
			if ((MainActivity.firstPhaseButton != null) && (MainActivity.secondPhaseButton != null)) {
				
				
				GameButton prevTurnPhaseOne = MainActivity.buttonObjectHash.get(MainActivity.firstPhaseButton);
				GameButton prevTurnPhaseTwo = MainActivity.buttonObjectHash.get(MainActivity.secondPhaseButton);
				
			
			//	previousTurnPhaseOne.setBackground(a.getResources().getDrawable(android.R.drawable.btn_default));
			//	previousTurnPhaseTwo.setBackground(a.getResources().getDrawable(android.R.drawable.btn_default));
				prevTurnPhaseOne.color = Color.BLACK;
				prevTurnPhaseTwo.color = Color.BLACK;
				
				prevTurnPhaseOne.numberVisible = false;
				prevTurnPhaseTwo.numberVisible = false;
				
				if (MainActivity.lastTurnWasMatch) {
					prevTurnPhaseOne.visibility = View.INVISIBLE;
					prevTurnPhaseTwo.visibility = View.INVISIBLE;
				}
				
				MainActivity.buttonObjectHash.put(MainActivity.firstPhaseButton, prevTurnPhaseOne);
				MainActivity.buttonObjectHash.put(MainActivity.secondPhaseButton, prevTurnPhaseTwo);
				
						//(android.R.drawable.btn_default_small));
				//previousTurnPhaseTwo.setBackgroundColor(Color.GREEN);
				//a.getResources(android.R.drawable.btn_default_small)
				//b.setb
			
			}

			 //*android.R.drawable.btn_default*/
		
			// we make bb visible here in case the user selected a button with a number
			// that was hidden above because it was selected on the previous turn
			bb.numberVisible = true;
		///	b.setTextColor(Color.YELLOW)
			bb.color = Color.YELLOW;
			
			//Can we change this to b.getId OR add an button ID property to the GameButton class?
			MainActivity.buttonObjectHash.put(v.getId(), bb);
			
			
			MainActivity.firstPhaseButton = b.getId();
			MainActivity.turnPhase = 2;
			MainActivity.lockedByClick = false;
			
			/* to do's
			 * 
			 * set buttons yellow as they are clicked, this will be a proxy for displaying numbers
			 * 
			 * END OF TURN PHASE
			 * clear yellow
			 * 
			 */
			
			UpdateScreen.update(a);
			
			return;
		}
		
		// TURN PHASE 2
		if (MainActivity.turnPhase == 2) {
			
			// I really don't know if this button locking code makes a difference;
			// probably not, since I think these listeners all run on the same thread.
			if (MainActivity.lockedByClick) { return; }
			MainActivity.lockedByClick = true;
			
			// make sure the user isn't tapping the same button twice
			if (MainActivity.firstPhaseButton == b.getId()) {
				MainActivity.lockedByClick = false;
				return;
			}
			
			MainActivity.turnPhase = 1;
			// should create a handle for the first button

		//	Button p = (Button) a.findViewById(MainActivity.firstPhaseButton);
			
			GameButton pp = MainActivity.buttonObjectHash.get(MainActivity.firstPhaseButton);
			
			bb.numberVisible = true;
			
			MainActivity.secondPhaseButton = b.getId();
			
			if ( bb.number == pp.number
					/*MainActivity.buttonHash.get(MainActivity.firstPhaseButton) == 
					MainActivity.buttonHash.get(MainActivity.secondPhaseButton)*/) {
				MainActivity.matches++;
				//p.setBackgroundColor(Color.YELLOW);
				
				bb.color = Color.GREEN;
				pp.color = Color.GREEN;
			///	b.setTextColor(Color.GREEN);
			///	p.setTextColor(Color.GREEN);

				MainActivity.lastTurnWasMatch = true;
				
				// we don't want users to start their next turn by selecting buttons they've already matched
				
				bb.clickable = false;
				pp.clickable = false;
			///	b.setClickable(false);
			///	p.setClickable(false);
				
				
			}
			else {
				
				bb.color = Color.RED;
				pp.color = Color.RED;
		///		b.setTextColor(Color.RED);
		///		p.setTextColor(Color.RED);
				MainActivity.lastTurnWasMatch = false;
				//b.setBackgroundColor(Color.BLUE);
			}

			//SystemClock.sleep(2000);
			
			/*
			p.setBackgroundColor(Color.GRAY);
			b.setBackgroundColor(Color.GRAY);
			*/
			
			MainActivity.buttonObjectHash.put(MainActivity.firstPhaseButton, pp);
			MainActivity.buttonObjectHash.put(MainActivity.secondPhaseButton, bb);
			
			UpdateScreen.update(a);
			
			if (MainActivity.matches == 8) {
				Toast.makeText(a.getBaseContext(), "You won! Tap for a new game.", Toast.LENGTH_SHORT).show();
			}
			
			MainActivity.lockedByClick = false;
		}
		// demonstration code, used for debugging purposes
		//Button b = (Button)v.findViewById(v.getId());
		
		//b.setBackgroundColor(Color.RED);
		//b.setText(MainActivity.buttonMap[1][1].toString());
		/* don't really need this toas anymore; it should eventually be part of a reference app
		 Toast .makeText(v.getContext(), b.getText(), Toast.LENGTH_SHORT).show();
		 b.setText(b.getText() + "!"); */
	}

}
