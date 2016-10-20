package br.udesc.ccscmocw.core.thread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import br.udesc.ccscmocw.log.Message;
import br.udesc.ccscmocw.log.Terminal;
import br.udesc.ccscmocw.log.TypeMessage;
import br.udesc.ccscmocw.values.CCSMessageType;
import br.udesc.ccscmocw.values.Cache;
import br.udesc.ccscmocw.values.CacheConverter;
import br.udesc.ccscmocw.values.Question;

public class ServerOUTThread extends Thread {

	/**
	 * @param args
	 */
	private Socket socket;
	private PrintStream out;

	public ServerOUTThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		//The id of the last message
		int lastId = 0;
		
		try {
			out = new PrintStream(socket.getOutputStream(), true);

			Terminal.printMessage("OUT Server Startting...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Terminal.printMessage("OUT Server Error: " + e.toString());
		}
		
		while (true) {
			try {
				// Send all messages of outSream while
				int size = Cache.logOut.size();
				while (lastId < size) {
					Message m = Cache.logOut.get(lastId);
					lastId ++;
					
					out.println(m.messageToStr());
					Terminal.printMessage("OUT " + m.messageToStr());
				}
				/*
				boolean a = true;
				//???????????????????????????????????????????????????????????????????????????????????????????????????????
				// This is a cache example
				out.println("CACHE_COHERENCE▓IMAGE_CACHE▓10 Apr 2013 17:34:14 GMT▓WUTB▓1615●9●-8●19●6●Diogo;null;null○Lucas;null;null○Eduardo;null;null●Felipe;null;null○Johnny;null;null●0;Johnny;CORRECT○1;Felipe;CORRECT○2;Lucas;CORRECT○3;Eduardo;CORRECT○4;Lucas;CORRECT○5;Dany;CORRECT○6;Lucas;CORRECT○7;null;null○8;Dany;INCORRECT○9;Felipe;LOCKED○10;Lucas;CORRECT○11;null;null○12;Lucas;CORRECT○13;Johnny;INCORRECT○14;Dany;INCORRECT○15;Lucas;CORRECT○16;null;null○17;Lucas;LOCKED○18;Lucas;CORRECT○19;Dany;INCORRECT○20;Lucas;CORRECT○21;Dany;CORRECT○22;Felipe;CORRECT○23;Dany;INCORRECT○24;Lucas;CORRECT○25;Felipe;LOCKED○26;Eduardo;INCORRECT○27;Lucas;CORRECT○28;Lucas;LOCKED○29;Eduardo;CORRECT○30;Lucas;CORRECT○31;Dany;INCORRECT○32;Dany;INCORRECT○33;Diogo;CORRECT○34;Lucas;CORRECT○35;Dany;CORRECT○36;Diogo;INCORRECT○37;Dany;INCORRECT○38;Eduardo;CORRECT○39;Lucas;CORRECT○40;Eduardo;INCORRECT○41;Lucas;INCORRECT○42;Eduardo;CORRECT○43;Eduardo;INCORRECT○44;Dany;INCORRECT○45;Dany;INCORRECT○46;Lucas;INCORRECT○47;null;null○48;Eduardo;INCORRECT○49;Lucas;CORRECT");
				if(a){
					a = false;
				Message cc;
				cc = new Message(TypeMessage.CHAT, CCSMessageType.NULL,
						new Date().toGMTString(), "Felipe", "Alguem no chat ae?");
				Cache.logOut.addLast(cc);
				
				cc = new Message(TypeMessage.CHAT, CCSMessageType.NULL,
						new Date().toGMTString(), "Dany", "oi");
				Cache.logOut.addLast(cc);
				
				cc = new Message(TypeMessage.CHAT, CCSMessageType.NULL,
						new Date().toGMTString(), "Dany", "ja errei varias");
				Cache.logOut.addLast(cc);
				cc = new Message(TypeMessage.CHAT, CCSMessageType.NULL,
						new Date().toGMTString(), "Johnny", "sacanagem essa 14");
				Cache.logOut.addLast(cc);
				//???????????????????????????????????????????????????????????????????????????????????????????????????????
				}*/
				
				out.flush();

				// Reply the server cache to the all clients
				sendAllCache();

				// Verify the end of the game
				if (isEndOfGame()) {
					@SuppressWarnings("deprecation")
					Message m = new Message(TypeMessage.CACHE_COHERENCE,
							CCSMessageType.GAME_FINISHED,
							new Date().toGMTString(), "WUTB",
							CacheConverter.cacheToStr());
					Cache.logOut.addLast(m);
				}

				sleep(Cache.timeUpdateCache);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isEndOfGame() {
		// TODO Auto-generated method stub
		try {
			for (Question q : Cache.questions) {
				if (!q.getStatus().equalsIgnoreCase("CORRECT")) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void sendAllCache() {
		//Test if cache was modified on the last interval
		if(Cache.RTant >= Cache.RTserver)
			return;
		
		@SuppressWarnings("deprecation")
		Message m = new Message(TypeMessage.CACHE_COHERENCE,
				CCSMessageType.IMAGE_CACHE, new Date().toGMTString(), "WUTB",
				CacheConverter.cacheToStr());
		Cache.logOut.addLast(m);
		
		Cache.RTant = Cache.RTserver;
	}
}
