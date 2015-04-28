/**
 * 
 */
package br.udesc.wutb.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public class LogBroadcastReceiver extends BroadcastReceiver {
	public LogBroadcastReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			TextView terminal = Params.getTerminal();
			while (!Cache.terminalLog.isEmpty()) {
				terminal.setText(terminal.getText().toString()
						+ Cache.terminalLog.removeFirst());

								
				//Refresh
				terminal.refreshDrawableState();
				

				// Scroll the end of terminal
				Params.getScrollTerminal().fullScroll(View.FOCUS_DOWN);
				
			}
			
			//Comented because make overhead of message log
			//LogApp.d("terminal:updated@ServiceBoard.onPostExecute(..)");
			
		} catch (Exception e) {
			LogApp.e("Terminal not yet started! >>" + e.getMessage());
		}
	}
}
