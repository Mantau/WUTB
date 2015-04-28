package br.udesc.ccscmocw.core.thread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import android.util.Log;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Config;

public class ClientOUTThread extends Thread {

	/**
	 * @param args
	 */
	private Socket socket;
	private PrintStream out;

	public ClientOUTThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			out = new PrintStream(socket.getOutputStream(), true);

			Log.d("WUTB", "OUT Startting...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("WUTB", "OUT Error: " + e.toString());
		}

		while (true) {
			try {
				// Send all messages of outSream while
				while (!Cache.logOut.isEmpty()) {
					Message m = Cache.logOut.removeFirst();
					out.println(m.messageToStr());
				}
				out.flush();

				sleep(Config.timeUpdateCache);
			} catch (Exception e) {
				// TODO
			}
		}

	}
}
