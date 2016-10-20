package br.udesc.ccscmocw.core;

/**
 * 
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.udesc.ccscmocw.core.thread.ServerINThread;
import br.udesc.ccscmocw.core.thread.ServerOUTThread;

/**
 * @author mantau
 * 
 */
public class CacheCoherenceServer extends Thread {
	private boolean isRunning = false;

	private ServerSocket serverSocket;
	private ArrayList<ServerINThread> listInThread;
	private ArrayList<ServerOUTThread> ListOutThread;

	public CacheCoherenceServer() {
		isRunning = true;
		listInThread = new ArrayList<ServerINThread>();
		ListOutThread = new ArrayList<ServerOUTThread>();
	}

	// Create a multithread server
	@Override
	public void run() {
		System.out.println("Start...");
		try {
			serverSocket = new ServerSocket(8888);

			while (isRunning) {
				System.out.println("Waiting clients...");
				Socket socket = serverSocket.accept();

				System.out.println("New client connected!");

				ServerINThread inThread = new ServerINThread(socket);
				ServerOUTThread outThread = new ServerOUTThread(socket);

				listInThread.add(inThread);
				ListOutThread.add(outThread);

				inThread.start();
				outThread.start();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
			isRunning = false;
		}
	}

	@SuppressWarnings("deprecation")
	public void stopServer() {
		for (ServerINThread inThread : listInThread) {
			inThread.stop();
		}
		for (ServerOUTThread outThread : ListOutThread) {
			outThread.stop();
		}
		this.stop();
	}
}
