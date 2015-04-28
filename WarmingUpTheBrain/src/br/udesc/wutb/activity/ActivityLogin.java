/**
 * 
 */
package br.udesc.wutb.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.udesc.wutb.R;
import br.udesc.wutb.listener.ListenerLogin;
import br.udesc.wutb.listener.ListenerMenu;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public class ActivityLogin extends AnimatedActivity {
	private ListenerLogin listener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_login);

		Params.setActivityLogin(this);
		Params.setActivity(this);
		
		listener = new ListenerLogin(this);
		listener.addListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return ListenerMenu.addOptionsMenu(this, item);
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onRestart() {
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}
	
	@Override
	public void onBackPressed() {
		ListenerMenu.openMenuClose(this);
	}

}
