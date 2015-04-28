package br.udesc.wutb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.udesc.widget.TxtProgressBar;
import br.udesc.wutb.R;
import br.udesc.wutb.values.Params;

public class ActivitySplash extends AnimatedActivity implements Runnable {
	TxtProgressBar bar;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		// set handler
		handler = new Handler();
		handler.postDelayed(ActivitySplash.this, 2000);

	}

	public void run() {
		try {
			// set progress bar
			bar = (TxtProgressBar) this.findViewById(R.id.progressBarSplash);
			bar.setProgress(1);
			bar.setText("Starting the app WUTB.");
			bar.setProgress(10);
			bar.refreshDrawableState();
			Thread.sleep(400);
			bar.setText("Try Connect to server..");
			bar.setProgress(25);
			bar.refreshDrawableState();
			Thread.sleep(500);

			// try connect
			Intent intent = new Intent("CCS_MoCW");
			Params.getActivity().startService(intent);

			// Sucess... Load the game Login
			Thread.sleep(500);
			bar.setProgress(90);
			bar.setText("Sucess! Loading app.");
			Thread.sleep(300);
			bar.setProgress(100);
			
			openActivityLogin();

		} catch (Exception e) {
			bar.setText("Connection failed!");
			openActivityConnection();
		}

	}

	private void openActivityLogin() {
		Intent it = new Intent(ActivitySplash.this, ActivityLogin.class);
		startActivity(it);

		this.finish();
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}
	
	private void openActivityConnection() {
		Intent it = new Intent(ActivitySplash.this, ActivityConnection.class);
		startActivity(it);

		this.finish();
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		handler.removeCallbacks(this);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();

		// run
		new Thread(this).start();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}