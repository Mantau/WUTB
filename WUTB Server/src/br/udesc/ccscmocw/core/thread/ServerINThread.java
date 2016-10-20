package br.udesc.ccscmocw.core.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import br.udesc.ccscmocw.core.CacheCoherenceServerReceiver;
import br.udesc.ccscmocw.log.Terminal;
import br.udesc.ccscmocw.values.Cache;

public class ServerINThread extends Thread {

	/**
	 * @param args
	 */
	private Socket socket;
	private BufferedReader in;
	private String line;

	public ServerINThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			Terminal.printMessage("IN Startting...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Terminal.printMessage("IN Error: " + e.toString());
		}

		while (true) {
			try {

				// Read all mesages of inReader
				// Test if line is not null, "", or "NULL"
				while ((line = in.readLine()) != null) {
					Terminal.printMessage("IN " + line);
					CacheCoherenceServerReceiver.receiveMessage(line);
				}

				// Wait the next update...
				Thread.sleep(Cache.timeUpdateCache);

			} catch (Exception e) {
				Terminal.printMessage("IN Error: " + e.getMessage());
			}
		}

	}
}
