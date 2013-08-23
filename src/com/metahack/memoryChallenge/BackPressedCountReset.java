package com.metahack.memoryChallenge;
import android.os.AsyncTask;
import android.os.SystemClock;

public class BackPressedCountReset extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected Void doInBackground(Void... params) {
			SystemClock.sleep(5000);
			MainActivity.backPressedCount = 0;
			return null;
		}
	}