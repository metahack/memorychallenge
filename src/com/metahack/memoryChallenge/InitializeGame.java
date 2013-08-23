package com.metahack.memoryChallenge;

import java.util.Random;
import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import java.lang.Boolean;
import java.util.Random;
import java.util.Collections;

public class InitializeGame {
	
	static void initialize(Activity a) {
		
		MainActivity.turn = 0;
		MainActivity.matches = 0;
		MainActivity.lastScore = MainActivity.turn;
		MainActivity.firstPhaseButton = null;
		MainActivity.secondPhaseButton = null;
		
		// not necessary since the keys are always the same, but just in case that changes...
		MainActivity.buttonObjectHash.clear();
		
		// you may be able to safely delete this when HashMap maps are deployed
		// This loop clears all random numbers from the button map
		 for (int i = 0; i < 16; i++) {
			MainActivity.buttonMap[i][1] = 111;
			MainActivity.buttonObjectHash.put(MainActivity.buttonIDs[i], new GameButton());
		}
		
 // scratch code to display feedback
		
		/*Integer value_test = null;
		String holder = "0!=N";
		/*if (value_test == 0) {
			holder = "0=n";
		}*/
		
		// MainActivity.turn = 0
	
	
	// this block generates random numbers
		
		    for (int i = 0; i < 8;) {
			//Boolean exists = false;
			//exists = false;
		    	Random random = new Random();
		    	Integer randomNumberHolder = random.nextInt(100);
		    	if (MainActivity.randomNumbersList.contains(randomNumberHolder)) {
		    		continue;
		    	}
		    	else {
		    		// adds 8 pairs of numbers, 16 total
		    		MainActivity.randomNumbersList.add(randomNumberHolder);
		    		MainActivity.randomNumbersList.add(randomNumberHolder);
		    		i++;
		    	}
		    }
			
			
			/*
			for (int j = 0; j < 16; j++) {
				if (MainActivity.randomNumbers[j] == randomNumberHolder) {
					exists = true;
					break;
				}	
			}
			if (!exists) {
				MainActivity.randomNumbers[i] = randomNumberHolder;
				MainActivity.randomNumbers[i + 1] = randomNumberHolder;
				i = i + 2;
			}
			
		}
		*/
		 
		Collections.shuffle(MainActivity.randomNumbersList);
		
		
		// random numbers assignned to buttons loop
	for(int i = 0; i < 16; i++) {
		
		Integer tempNumber = MainActivity.randomNumbersList.remove(0);
		MainActivity.buttonHash.put(MainActivity.buttonIDs[i], tempNumber);
		
		GameButton g = MainActivity.buttonObjectHash.get(MainActivity.buttonIDs[i]);
		g.number = tempNumber;
		g.clickable = true;
		g.color = Color.BLACK;
		g.visibility = View.VISIBLE;
		g.numberVisible = false;
		MainActivity.buttonObjectHash.put(MainActivity.buttonIDs[i], g);
		}
		//OLD BUTTON-NUMBER MAP START
		// this block assigns random numbers to buttonMap
/*		
		int breaker = 0;
		for (int i = 0; i < 16; ) {
			Boolean exists = false;
			Random random = new Random();
			Integer randomNumberHolder = random.nextInt(16); 
			

				if (MainActivity.buttonMap[randomNumberHolder][1] != 111) {
					exists = true;
					breaker++;
					if (breaker > 10000) {break;}
					continue;
		//		}	
			}
			if (!exists) {
				MainActivity.buttonMap[randomNumberHolder][1] = MainActivity.randomNumbers[i];
				i = i + 1;
			}
		}
		*/
		// OLD BUTTON-NUMBER MAP END
		
		// START this block will be replaced by a call to UpdateScreen
		for (int i = 0; i < 16; i++) {
			Button b = (Button) a.findViewById(MainActivity.buttonIDs[i]);
			if (MainActivity.buttonMap[i][1] == null) {
				b.setText("x" + i);
			}
			else {
			//	b.setText(MainActivity.buttonHash.get(MainActivity.buttonIDs[i]).toString());
			
			// we are ready to stop showing text -- we'll uncomment for debugging
			//b.setText(MainActivity.buttonHash.get(b.getId()).toString());
			
			
			//b.setBackground(a.getResources().getDrawable(android.R.drawable.btn_default));
			//	b.setText( MainActivity.buttonMap[i][1].toString() );
			}
		}
		// END this block will be replaced by a call to UpdateScreen

		// temp code to display feedback START
		Button b = (Button) a.findViewById(MainActivity.buttonIDs[3]);	
		Integer tempInteger = MainActivity.randomNumbers[5]; 
		Integer temp2 = null;
		//String tempString = String.
		//Button b = (Button) a.findViewById(R.id.b16);		
		//b.setText(tempInteger.toString() /*String.valueOf(MainActivity.turn)*/); 
		// temp code to display feedback END
	}
}
