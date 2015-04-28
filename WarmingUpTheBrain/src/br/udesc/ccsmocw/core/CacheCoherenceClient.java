/**
 * 
 */
package br.udesc.ccsmocw.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import br.udesc.ccscmocw.core.thread.ClientINThread;
import br.udesc.ccscmocw.core.thread.ClientOUTThread;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.values.Config;

/**
 * @author mantau
 * 
 */
public class CacheCoherenceClient extends Service implements Runnable {
	private Socket client = null;
	private ClientINThread inThread;
	private ClientOUTThread outThread;

	// private Thread thread = null;
	// private BufferedReader in = null;
	// private PrintStream out = null;
	// private String line = null;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	} 

	@Override
	public void onCreate() {
		super.onCreate();

		new Thread(this).start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDestroy() {
		try {
			inThread.stop();
			outThread.stop();
			client.close();
		} catch (IOException e) {
			LogApp.e("IN Error: " + e.toString());
		}
		super.onDestroy();
	}

	@Override
	public void run() {
		
		/*
		 * while (true) { try { isRunning = true;
		 * 
		 * while(isRunning){
		 */
		
		inThread = new ClientINThread(getClientConnection());
		outThread = new ClientOUTThread(getClientConnection());

		inThread.start();
		outThread.start();

		// }

		/*
		 * } catch (IOException e) { System.out.println("Error: " +
		 * e.toString()); isRunning = false; } try { //Send all messages of
		 * outSream while(!Cache.fullLog.isEmpty()){ Message m =
		 * Cache.fullLog.removeFirst(); out.println(m.messageToStr());
		 * Log.e("","" + m.messageToStr()); } out.flush();
		 * 
		 * //Read all mesages of inReader //Test if line is not null, "", or
		 * "NULL" while((line = in.readLine()) != null){ LogApp.a("line: " +
		 * line); CacheCoherenceClientReceiver.receiveMessage(line); }
		 * 
		 * //Add a message ACK at the top of the pool
		 * 
		 * @SuppressWarnings("deprecation") Message ack = new
		 * Message(TypeMessage.CACHE_COHERENCE, CCSMessageType.ACK, new
		 * Date().toGMTString(), Cache.user.getName(), null);
		 * Cache.fullLog.addFirst(ack);
		 * 
		 * //Wait the next update... Thread.sleep(Config.timeUpdateCache); }
		 * catch (Exception e) { // TODO: handle exception } }
		 */
	}

	public Socket getClientConnection() {
		try {
			if (client == null) {
				InetAddress addr = InetAddress.getByName(Config.address);
				client = new Socket(addr, Config.port);
			}
		} catch (UnknownHostException e) {
			LogApp.e("IN Error: " + e.toString());
		} catch (IOException e) {
			LogApp.e("IN Error: " + e.toString());
		}

		return client;
	}

}
