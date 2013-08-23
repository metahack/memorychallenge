package com.metahack.memoryChallenge;

import java.util.Random;
import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import java.lang.Boolean;
import java.util.Map;
import java.util.Random;
import java.util.Collections;


public class UpdateNumbers {

	
	public static void update() {
		for (Map.Entry<Integer, GameButton> entry : MainActivity.buttonObjectHash.entrySet() ) {
			
			Integer tempNumber = entry.getValue().number;
			tempNumber++;
			
			if (tempNumber == 100) {
				tempNumber = 0;
			}
				
			GameButton tempButton = entry.getValue();
			tempButton.number = tempNumber;
			
			MainActivity.buttonObjectHash.put(entry.getKey(), tempButton);
		}
	}
}
