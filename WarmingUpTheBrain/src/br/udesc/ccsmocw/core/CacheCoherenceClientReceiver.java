/**
 * 
 */
package br.udesc.ccsmocw.core;

import java.util.Date;

import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.R;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.CacheConverter;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.StatusQuestion;
import br.udesc.wutb.values.User;

/**
 * @author mantau
 * 
 */
public abstract class CacheCoherenceClientReceiver {

	public static void receiveMessage(String line) {
		Message m = Message.strToMessage(line);

		switch (m.getType()) {
		case ACTION:
			// TODO do nothing
			break;

		case NOTIFICATION:
			// TODO do nothing
			break;

		case ERROR:
			// TODO do nothing
			break;

		case CACHE_COHERENCE:
			updateCache(m);
			break;

		case WARNING:
			// TODO do nothing
			break;

		case CHAT:
			updateChat(m);
			break;

		default:
			// TODO do nothing
			break;
		}
	}

	private static void updateCache(Message m) {
		switch (m.getTag()) {
		case ACK:
			// TODO do nothing
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
			break;

		case DELETE:
			// TODO do nothing
			break;

		case EXIT_GROUPWARE:
			exitGroupware(m);
			break;

		case INSERT:
			// TODO do nothing
			break;

		case JOIN_TO_GROUPWARE:
			joinToGroupware(m);
			break;

		case LOCK_ITEM:
			lockItem(m);
			break;

		case NOTIFICATION_CHANGE:
			// TODO do nothing
			break;

		case NOTIFY_IR_RECEIVED:
			// TODO do nothing
			break;

		case REQUEST_LOST_IR:
			// TODO do nothing
			break;

		case SYNC_REQUEST:
			// TODO do nothing
			break;

		case UNLOCK_ITEM:
			cancelLock(m);

		case IMAGE_CACHE:
			refreshCacheValues(m);
			break;

		case GAME_FINISHED:
			finalizeTheGame();
			break;

		default:
			// TODO do nothing
			break;
		}
	}

	private static void refreshCacheValues(Message m) {
		CacheConverter.strToCache(m.getContent());
	}

	private static void updateChat(Message m) {
		User u = new User(m.getUser(), null, null);
	
		if ((!u.getName().equalsIgnoreCase(Cache.user.getName()))
				&& (Cache.myGroup.contains(u))) {
			Cache.chat.addLast(u.getName() + Config.split + m.getContent());
			
			Cache.numChatMessages++;
		}
	}

	private static void joinToGroupware(Message m) {
		User u = new User(m.getUser(), null, null);
		if (m.getContent().equalsIgnoreCase("Team A"))
			if ((u.getName().length() > 0) && !(Cache.groupA.contains(u))) {
				Cache.groupA.add(u);
			}

		if (m.getContent().equalsIgnoreCase("Team B"))
			if ((u.getName().length() > 0) && !(Cache.groupB.contains(u))) {
				Cache.groupB.add(u);
			}

	}

	private static void answerCorrectQuestion(Message m) {
		int itemId = Integer.parseInt(m.getContent());
		User u = new User(m.getUser(), null, null);

		int points = 1;

		if (u.getName().equalsIgnoreCase(Cache.user.getName())) {
			if (Cache.questions.get(itemId).getStatus() == StatusQuestion.INCORRECT_OTHER_GROUP) {
				points++;
			}
			Cache.questions.get(itemId).setStatus(StatusQuestion.CORRECT_ME);
			if (Cache.team.equalsIgnoreCase(Params.getActivity().getString(
					R.string.str_team_a))) {
				Cache.questionsCorrectGA++;
				Cache.questionsDotsGA += points;
			} else {
				Cache.questionsCorrectGB++;
				Cache.questionsDotsGB += points;
			}
		} else if (Cache.myGroup.contains(u)) {
			if (Cache.questions.get(itemId).getStatus() == StatusQuestion.INCORRECT_OTHER_GROUP) {
				points++;
			}
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.CORRECT_MY_GROUP);
			if (Cache.team.equalsIgnoreCase(Params.getActivity().getString(
					R.string.str_team_a))) {
				Cache.questionsCorrectGA++;
				Cache.questionsDotsGA += points;
			} else {
				Cache.questionsCorrectGB++;
				Cache.questionsDotsGB += points;
			}
		} else {
			if (Cache.questions.get(itemId).getStatus() == StatusQuestion.INCORRECT_MY_GROUP) {
				points++;
			}
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.CORRECT_OTHER_GROUP);
			if (Cache.team.equalsIgnoreCase(Params.getActivity().getString(
					R.string.str_team_a))) {
				Cache.questionsCorrectGB++;
				Cache.questionsDotsGB += points;
			} else {
				Cache.questionsCorrectGA++;
				Cache.questionsDotsGA += points;
			}

		}
		Cache.questions.get(itemId).setPlayerName(u.getName());

	}

	private static void answerIncorrectQuestion(Message m) {
		int itemId = Integer.parseInt(m.getContent());
		User u = new User(m.getUser(), null, null);

		if (u.getName().equalsIgnoreCase(Cache.user.getName())) {
			Cache.questions.get(itemId).setStatus(StatusQuestion.INCORRECT_ME);
		} else if (Cache.myGroup.contains(u)) {
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.INCORRECT_MY_GROUP);
		} else {
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.INCORRECT_OTHER_GROUP);
		}
		Cache.questions.get(itemId).setPlayerName("");

	}

	private static void lockItem(Message m) {
		int itemId = Integer.parseInt(m.getContent());
		User u = new User(m.getUser(), null, null);

		if (u.getName().equalsIgnoreCase(Cache.user.getName())) {
			Cache.questions.get(itemId).setStatus(StatusQuestion.LOCKED_ME);
		} else if (Cache.myGroup.contains(u)) {
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.LOCKED_MY_GROUP);
		} else {
			Cache.questions.get(itemId).setStatus(
					StatusQuestion.LOCKED_OTHER_GROUP);
		}
		Cache.questions.get(itemId).setPlayerName(u.getName());
	}

	private static void exitGroupware(Message m) {
		User u = new User(m.getUser(), null, null);
		if (m.getContent().equalsIgnoreCase("EXIT GA"))
			Cache.groupA.remove(u);

		if (m.getContent().equalsIgnoreCase("EXIT GB"))
			Cache.groupB.remove(u);
	}

	private static void cancelLock(Message m) {
		int itemId = Integer.parseInt(m.getContent());
		Cache.questions.get(itemId).unlock();
		Cache.questions.get(itemId).setPlayerName("");
	}

	private static void finalizeTheGame() {
		@SuppressWarnings("deprecation")
		Message m = new Message(TypeMessage.CACHE_COHERENCE,
				CCSMessageType.GAME_FINISHED, new Date().toGMTString(), "WUTB",
				CacheConverter.cacheToStr());
		Cache.logOut.addLast(m);

		//Set the end of the game
		Cache.isendOfTheGame = true;
	}

}
