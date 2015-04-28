package br.udesc.ccscmocw.core.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.util.Log;
import br.udesc.ccsmocw.core.CacheCoherenceClientReceiver;
import br.udesc.wutb.values.Config;

public class ClientINThread extends Thread {

	/**
	 * @param args
	 */
	private Socket socket;
	private BufferedReader in;
	private String line;

	public ClientINThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			Log.d("WUTB", "IN Startting...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("WUTB", "IN Error: " + e.toString());
		}

		while (true) {
			try {
				while ((line = in.readLine()) != null) {
					Log.d("", "line: " + line);
					CacheCoherenceClientReceiver.receiveMessage(line);
				}

				// Wait the next update...
				sleep(Config.timeUpdateCache);

			} catch (Exception e) {
				Log.e("WUTB", "IN Error: " + e.getMessage());
			}
		}

	}
}
