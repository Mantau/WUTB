/**
 * 
 */
package br.udesc.wutb.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.listener.ListenerBoard;
import br.udesc.wutb.listener.ListenerChat;
import br.udesc.wutb.listener.ListenerConfig;
import br.udesc.wutb.listener.ListenerMenu;
import br.udesc.wutb.listener.ListenerQuestion;
import br.udesc.wutb.listener.ListenerTabs;
import br.udesc.wutb.listener.ListenerTerminal;
import br.udesc.wutb.listener.ListenerUserList;
import br.udesc.wutb.service.ServiceBoard;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public class ActivityGame extends AnimatedActivity {
	//Services
	private ServiceBoard serviceBoard;
	
	//Listeners
	private ListenerBoard    listenerBoard;
	private ListenerQuestion listenerQuestion;
	private ListenerUserList listenerUserList;
	private ListenerChat     listenerChat;
	private ListenerTerminal listenerTerminal;
	private ListenerConfig   listenerConfig;
	private ListenerTabs     listenerTabs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_game);

		TextView terminal = (TextView) this.findViewById(R.id.textViewTerminal);
		ScrollView scrollTerminal = (ScrollView) this.findViewById(R.id.scrollViewGameTerminal);

		Params.setActivityGame(this);
		Params.setTerminal(terminal);
		Params.setScrollTerminal(scrollTerminal);

		loadListeners(this);
	}

	private void loadListeners(ActivityGame activityGame) {
		//Load listeners
		listenerBoard    = new ListenerBoard(this);
		listenerQuestion = new ListenerQuestion(this);
		listenerUserList = new ListenerUserList(this);
		listenerChat     = new ListenerChat(this);
		listenerTerminal = new ListenerTerminal(this);
		listenerConfig   = new ListenerConfig(this);
		listenerTabs     = new ListenerTabs(this);
		
		//Add actions
		listenerBoard.addListeners();
		listenerQuestion.addListeners();
		listenerUserList.addListeners();
		listenerChat.addListeners();
		listenerTerminal.addListeners();
		listenerConfig.addListeners();
		listenerTabs.addListeners();
		
		
		//Add the listeners to Config
		Params.setListenerBoard(listenerBoard);
		Params.setListenerQuestion(listenerQuestion);
		Params.setListenerUserList(listenerUserList);
		Params.setListenerChat(listenerChat);
		Params.setListenerTerminal(listenerTerminal);
		Params.setListenerConfig(listenerConfig);
		Params.setListenerTabs(listenerTabs); 
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
		serviceBoard.cancel(true);
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

		serviceBoard = new ServiceBoard();
		serviceBoard.execute("");
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onBackPressed() {
		Log.e("", "asdf");
		ListenerMenu.openMenuClose(this);
	}

}
