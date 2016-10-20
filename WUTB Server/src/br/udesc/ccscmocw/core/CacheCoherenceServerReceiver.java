/**
 * 
 */
package br.udesc.ccscmocw.core;

import java.util.Iterator;

import br.udesc.ccscmocw.log.Message;
import br.udesc.ccscmocw.values.CCSMessageType;
import br.udesc.ccscmocw.values.Cache;
import br.udesc.ccscmocw.values.StatusUser;
import br.udesc.ccscmocw.values.User;

/**
 * @author mantau
 * 
 */
public abstract class CacheCoherenceServerReceiver {

	public static synchronized void receiveMessage(String line) {
		Message m = Message.strToMessage(line);

		switch (m.getType()) {
		case ACTION:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case NOTIFICATION:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case ERROR:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case CACHE_COHERENCE:
			updateCache(m);
			break;

		case WARNING:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case CHAT:
			replyMessage(m);
			break;

		default:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;
		}
	}

	private static void updateCache(Message m) {
		switch (m.getTag()) {
		case ACK:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case ANSWER_CORRECT:
			answerCorrectQuestion(m);
			break;

		case ANSWER_INCORRECT:
			answerIncorrectQuestion(m);
			break;

		case CANCEL_LOCK:
			cancelLock(m);
			break;

		case COMMIT_CHANGES:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case DELETE:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case EXIT_GROUPWARE:
			exitGroupware(m);
			break;

		case INSERT:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case JOIN_TO_GROUPWARE:
			joinToGroupware(m);
			break;

		case LOCK_ITEM:
			lockItem(m);
			break;

		case NOTIFICATION_CHANGE:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case NOTIFY_IR_RECEIVED:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case REQUEST_LOST_IR:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case SYNC_REQUEST:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;

		case UNLOCK_ITEM:
			cancelLock(m);
			break;

		case IMAGE_CACHE:
			Cache.fullLog.addLast(m);
			break;

		case GAME_FINISHED:
			// TODO do finish
			Cache.fullLog.addLast(m);
			break;

		default:
			// TODO do nothing
			Cache.fullLog.addLast(m);
			break;
		}
	}

	private static void replyMessage(Message m) {
		Cache.fullLog.addLast(m);
		Cache.logOut.addLast(m);
	}

	private static void joinToGroupware(Message m) {
		User u = new User(m.getUser(), null, StatusUser.ONLINE);

		if (m.getContent().equalsIgnoreCase("Team A")) {
			Cache.groupA.add(u);
		}

		if (m.getContent().equalsIgnoreCase("Team B")) {
			Cache.groupB.add(u);
		}

		replyMessage(m);
		Cache.RTserver++;

	}

	private static void answerCorrectQuestion(Message m) {
		int itemId = Integer.parseInt(m.getContent());

		// Test if the question is locked (user)
		if (Cache.questions.get(itemId).getPlayerName().equalsIgnoreCase(m.getUser()) && Cache.questions.get(itemId).getStatus().equalsIgnoreCase("LOCKED")) {
			Cache.questions.get(itemId).setStatus("CORRECT");
			Cache.questions.get(itemId).setPlayerName(m.getUser());

			replyMessage(m);

			updateStatusQuestionAlt(m);
		}

		Cache.RTserver++;

	}

	private static void answerIncorrectQuestion(Message m) {
		int itemId = Integer.parseInt(m.getContent());

		// Test if the question is locked (user)
		if (Cache.questions.get(itemId).getPlayerName().equalsIgnoreCase(m.getUser()) && Cache.questions.get(itemId).getStatus().equalsIgnoreCase("LOCKED")) {
			Cache.questions.get(itemId).setStatus("INCORRECT");
			Cache.questions.get(itemId).setPlayerName(m.getUser());

			replyMessage(m);

			updateStatusQuestionAlt(m);
		}

		Cache.RTserver++;

	}

	private static void lockItem(Message m) {
		int itemId = Integer.parseInt(m.getContent());

		if (!Cache.questions.get(itemId).getStatus().equalsIgnoreCase("LOCKED") || !Cache.questions.get(itemId).getStatus().equalsIgnoreCase("CORRECT")) {
			Cache.questions.get(itemId).setStatus("LOCKED");
			Cache.questions.get(itemId).setPlayerName(m.getUser());

			replyMessage(m);

			updateStatusQuestionAlt(m);
		}

		Cache.RTserver++;
	}

	private static void exitGroupware(Message m) {
		User u = new User(m.getUser(), null, StatusUser.OFFLINE);

		if (m.getContent().equalsIgnoreCase("EXIT GA")) {
			Cache.groupA.remove(u);
		}

		if (m.getContent().equalsIgnoreCase("EXIT GB")) {
			Cache.groupB.remove(u);
		}

		replyMessage(m);
		Cache.RTserver++;
	}

	private static void cancelLock(Message m) {
		int itemId = Integer.parseInt(m.getContent());

		Cache.questions.get(itemId).setStatus(" ");
		Cache.questions.get(itemId).setPlayerName(" ");

		replyMessage(m);
		Cache.RTserver++;

		updateStatusQuestionAlt(m);
	}
	
	@SuppressWarnings("rawtypes")
	private static void updateStatusQuestionAlt(Message m) {
		User u = new User(m.getUser(), null, null);
		int itemId = Integer.parseInt(m.getContent());
		int points = 1;
		boolean containsA = false;
		
		Iterator itA = Cache.groupA.iterator();
		Iterator itB = Cache.groupB.iterator();
		
		while(itA.hasNext()){
			User x = (User) itA.next();
			
			if(x.getName().equalsIgnoreCase(m.getUser())){
				u = x;
				containsA = true;
			}
		}
		
		while(itB.hasNext()){
			User x = (User) itB.next();
			if(x.getName().equalsIgnoreCase(m.getUser())){
				u = x;
				containsA = false;
			}
		}
		
		
		
		//verifies that the question has been answered correctly
		if(Cache.statusQuestionsAux[itemId].equalsIgnoreCase("CGA") || 
		   Cache.statusQuestionsAux[itemId].equalsIgnoreCase("CGB")){
			return ;
		}

		//question correct
		if (m.getTag() == CCSMessageType.ANSWER_CORRECT) {
			
			//correct GA
			if (containsA) {

				if (Cache.statusQuestionsAux[itemId].equalsIgnoreCase("IGB")) {
					points++;
				}
				
				Cache.statusQuestionsAux[itemId] = "CGA";
				Cache.questionsCorrectGA++;
				Cache.questionsDotsGA += points;
			}
			
			//correct GB
			else {
				if (Cache.statusQuestionsAux[itemId].equalsIgnoreCase("IGA")) {
					points++;
				}
				
				Cache.statusQuestionsAux[itemId] = "CGB";
				Cache.questionsCorrectGB++;
				Cache.questionsDotsGB += points;
			}
			
			//++ individual
			u.incxxCorrect();
			u.incxxDots(points);
		}

		//question incorrect
		if (m.getTag() == CCSMessageType.ANSWER_INCORRECT) {
		
			//incorrect GA
			if (containsA) {
				//Verify if the question already "IGA"
				if(Cache.statusQuestionsAux[itemId].equalsIgnoreCase("IGA")){
					return;
				}
				Cache.questionsDotsGA--;
				Cache.questionsIncorrectGA++;
				Cache.statusQuestionsAux[itemId] = "IGA";
			} 
			
			//incorrect GB
			else {
				//Verify if the question already "IGB"
				if(Cache.statusQuestionsAux[itemId].equalsIgnoreCase("IGB")){
					return;
				}
				Cache.questionsDotsGB--;
				Cache.questionsIncorrectGB++;
				Cache.statusQuestionsAux[itemId] = "IGB";
			}
						
			//++ individual
			u.incxxIncorrect();
			u.decxxDots();
		}

		//question locked
		if (m.getTag() == CCSMessageType.LOCK_ITEM) {
			
			//locked GA
			if (containsA) {
				Cache.statusQuestionsAux[itemId] = "LGA";
				Cache.questionsViewGA++;
			} 
			
			//locked GB
			else {
				Cache.statusQuestionsAux[itemId] = "LGB";
				Cache.questionsViewGB++;
			}
			
			
			//++ individual
			u.incxxView();
		}

		//question unlocked
		if (m.getTag() == CCSMessageType.UNLOCK_ITEM) {
			Cache.statusQuestionsAux[itemId] = " ";
		}
	}
}
