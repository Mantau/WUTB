/**
 * 
 */
package br.udesc.wutb.log;

import java.util.Date;

import android.content.Intent;
import android.util.Log;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public abstract class LogApp {

	private static final CCSMessageType ccsTag = CCSMessageType.INSERT;
	private static final String TAG = "WUTB";
	private static Date date = new Date();

	// Message action
	public static void a(String content) {
		@SuppressWarnings("deprecation")
		Message message = new Message(TypeMessage.ACTION, ccsTag,
				date.toLocaleString(), Cache.user.getName(), content);
		addMessageToCache(message);

		Log.i(TAG, message.toString());
	}

	// Message notification
	public static void n(String content) {
		@SuppressWarnings("deprecation")
		Message message = new Message(TypeMessage.NOTIFICATION, ccsTag,
				date.toLocaleString(), Cache.user.getName(), content);
		addMessageToCache(message);

		Log.i(TAG, message.toString());
	}

	// Message cache coherence
	public static void c(String content) {
		@SuppressWarnings("deprecation")
		Message message = new Message(TypeMessage.CACHE_COHERENCE, ccsTag,
				date.toLocaleString(), Cache.user.getName(), content);
		addMessageToCache(message);

		Log.d(TAG, message.toString());
	}

	// message error
	public static void e(String content) {
		@SuppressWarnings("deprecation")
		Message message = new Message(TypeMessage.ERROR, ccsTag,
				date.toLocaleString(), Cache.user.getName(), content);
		addMessageToCache(message);

		Log.e(TAG, message.toString());
	}

	// message warning
	public static void w(String content) {
		@SuppressWarnings("deprecation")
		Message message = new Message(TypeMessage.WARNING, ccsTag,
				date.toLocaleString(), Cache.user.getName(), content);
		addMessageToCache(message);

		Log.e(TAG, message.toString());
	}

	private static void addMessageToCache(Message message) {

		if (Config.typeMessageDisplayed.contains(message.getType())) {
			Cache.terminalLog.addLast(message.toTerminalString());

			// Send a broadcast to refresh log Terminal UI
			Params.getActivity().sendBroadcast(new Intent("WUTB_LOG_UPDATE"));
		}

		if (Config.typeMessageSaved.contains(message.getType())) {
			Cache.logOut.addLast(message);
		}
	}

}
