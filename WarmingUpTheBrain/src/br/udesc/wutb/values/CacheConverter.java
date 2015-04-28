/**
 * 
 */
package br.udesc.wutb.values;

import java.util.Date;
import java.util.Set;

import android.util.Log;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;

/**
 * @author mantau
 * 
 */
public abstract class CacheConverter {

	// The split used to divide attributes
	private static String splitParams = "●";

	// The split used to divide multiple items
	// of some attribute (i.e. values of arrays)
	private static String splitValues = "○";

	// Used to split values of users and questions
	private static String splitX = ";";

	// Cache message
	// RTum●xxViewGA●xxCorrectGA●xxIncorrectGA●xxDotsGA●xxViewGB●xxCorrectGB●xxIncorrectGB●xxDotsGB●usersGA●usersGB●questions

	// users are divided: user1○user2○user3○...○userX
	// questions are divided: question1○question2○question3○...○question50

	// a user is composed of: name;email;state;xxView;xxCorrect;xxIncorrect;xxDots;
	// a question is composed of: number;user;state

	public static void strToCache(String content) {
		String a[] = content.split(CacheConverter.splitParams);

		long RTcurrent = Long.parseLong(a[0]);

		if (RTcurrent > Cache.RTum) {
			// Update scores of the groups
			updateScore(a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]);

			// Update user list
			updateUsers(a[9], a[10]);

			// update board
			updateBoard(a[11]);

			// update RT
			Cache.RTum = RTcurrent;
		}
	}

	public static String cacheToStr() {
	  /*RTum●
		xxViewGA●
		xxCorrectGA●
		xxIncorrectGA●
		xxDotsGA●
		xxViewGB●
		xxCorrectGB●
		xxIncorrectGB●
		xxDotsGB●
		usersGA●
		usersGB
		●questions*/
		return "" + Cache.RTum                      + CacheConverter.splitParams 
				  + Cache.questionsViewGA           + CacheConverter.splitParams 
				  + Cache.questionsCorrectGA        + CacheConverter.splitParams 
				  + Cache.questionsIncorrectGA       + CacheConverter.splitParams 
				  + Cache.questionsDotsGA           + CacheConverter.splitParams 
				  + Cache.questionsViewGB           + CacheConverter.splitParams 
				  + Cache.questionsCorrectGB        + CacheConverter.splitParams 
				  + Cache.questionsIncorrectGB       + CacheConverter.splitParams 
				  + Cache.questionsDotsGB           + CacheConverter.splitParams 
				  + convertUsersToStr(Cache.groupA)	+ CacheConverter.splitParams 
				  + convertUsersToStr(Cache.groupB)	+ CacheConverter.splitParams 
				  + convertQuestionsToStr();
	}

	private static String convertQuestionsToStr() {
		String txt = "";
		for (Question q : Cache.questions) {
			if (!txt.isEmpty()) {
				txt += CacheConverter.splitValues;
			}
			txt += q.getNumber() + CacheConverter.splitX + q.getPlayerName()
					+ CacheConverter.splitX + q.getStatus();
		}
		return txt;
	}

	private static String convertUsersToStr(Set<User> users) {
		//name;email;state;xxView;xxCorrect;xxIncorrect;xxDots
		String txt = "";
		for (User u : users) {
			if (!txt.isEmpty()) {
				txt += CacheConverter.splitValues;
			}
			txt += u.getName()         + CacheConverter.splitX 
			     + u.getEmail()        + CacheConverter.splitX 
			     + u.getStatus()       + CacheConverter.splitX
			     + u.getxxView()       + CacheConverter.splitX
			     + u.getxxCorrect()    + CacheConverter.splitX
			     + u.getxxIncorrect()  + CacheConverter.splitX
			     + u.getxxDots();
		}
		return txt;
	}

	public static void sendAllCache() {
		@SuppressWarnings("deprecation")
		Message m = new Message(TypeMessage.CACHE_COHERENCE,
				CCSMessageType.IMAGE_CACHE, new Date().toGMTString(), "WUTB",
				CacheConverter.cacheToStr());
		Cache.logOut.addFirst(m);
	}

	private static void updateBoard(String content) {
		String questions[];

		questions = content.split(CacheConverter.splitValues);

		for (int i = 0; i < 50; i++) {
			String q = questions[i];
			String values[] = q.split(CacheConverter.splitX);

			// update question status
			updateStatusQuestion(i, values[1], values[2]);
		}

	}

	private static void updateStatusQuestion(int index, String username,
			String status) {

		Cache.questions.get(index).setPlayerName(username);

		User user = new User(username, null, null);

		if (status.equalsIgnoreCase("CORRECT")) {
			if (username.equalsIgnoreCase(Cache.user.getName())) {
				Cache.questions.get(index).setStatus(StatusQuestion.CORRECT_ME);
			} else if (Cache.myGroup.contains(user)) {
				Cache.questions.get(index).setStatus(
						StatusQuestion.CORRECT_MY_GROUP);
			} else {
				Cache.questions.get(index).setStatus(
						StatusQuestion.CORRECT_OTHER_GROUP);
			}
		} else if (status.equalsIgnoreCase("INCORRECT")) {
			if (username.equalsIgnoreCase(Cache.user.getName())) {
				Cache.questions.get(index).setStatus(
						StatusQuestion.INCORRECT_ME);
			} else if (Cache.myGroup.contains(user)) {
				Cache.questions.get(index).setStatus(
						StatusQuestion.INCORRECT_MY_GROUP);
			} else {
				Cache.questions.get(index).setStatus(
						StatusQuestion.INCORRECT_OTHER_GROUP);
			}
		} else if (status.equalsIgnoreCase("LOCKED")) {
			if (username.equalsIgnoreCase(Cache.user.getName())) {
				Cache.questions.get(index).setStatus(StatusQuestion.LOCKED_ME);
			} else if (Cache.myGroup.contains(user)) {
				Cache.questions.get(index).setStatus(
						StatusQuestion.LOCKED_MY_GROUP);
			} else {
				Cache.questions.get(index).setStatus(
						StatusQuestion.LOCKED_OTHER_GROUP);
			}
		} else {
			Cache.questions.get(index).unlock();
		}

	}

	//xxViewGA●xxCorrectGA●xxIncorrectGA●xxDotsGA●xxViewGB●xxCorrectGB●xxIncorrectGB●xxDotsGB
	private static void updateScore(String xxViewGA, String xxCorrectGA, String xxIncorrectGA, String xxDotsGA, 
			                        String xxViewGB, String xxCorrectGB, String xxIncorrectGB, String xxDotsGB) {
		
		Cache.questionsViewGA = Integer.parseInt(xxViewGA);
		Cache.questionsCorrectGA = Integer.parseInt(xxCorrectGA);
		Cache.questionsIncorrectGA = Integer.parseInt(xxIncorrectGA);
		Cache.questionsDotsGA = Integer.parseInt(xxDotsGA);
		
		Cache.questionsViewGB = Integer.parseInt(xxViewGB);
		Cache.questionsCorrectGB = Integer.parseInt(xxCorrectGB);
		Cache.questionsIncorrectGB = Integer.parseInt(xxIncorrectGB);
		Cache.questionsDotsGB = Integer.parseInt(xxDotsGB);
	}

	private static void updateUsers(String ga, String gb) {
		String users[];
		
		//Clear userlist
		Cache.groupA.clear();
		Cache.groupB.clear();
		
		// Test if ga is empty
		if ((gb != null) && !(ga.isEmpty()) && !(ga.equalsIgnoreCase("null"))) {
			// Reload GA
			users = ga.split(CacheConverter.splitValues);

			for (String x : users) {
				String values[] = x.split(CacheConverter.splitX);

				// TODO melhorar
				//name;email;state;xxView;xxCorrect;xxIncorrect;xxDots
				User u = new User(values[0], values[1], StatusUser.ONLINE);
				try {
					u.setxxView(Integer.parseInt("" + values[3]));
					u.setxxCorrect(Integer.parseInt("" + values[4]));
					u.setxxIncorrect(Integer.parseInt("" + values[5]));
					u.setxxDots(Integer.parseInt("" + values[6]));
					u.setStatus(StatusUser.valueOf("" + values[2]));
					
					Log.e("", "USUARIO: " + u.toString());
				} catch (Exception e) {
					//TODO
				}

				if (!u.getName().isEmpty()) {
					Cache.groupA.add(u);
				}
			}
		}

		// Test if gb is empty
		if ((gb != null) && !(gb.isEmpty()) && !(gb.equalsIgnoreCase("null"))) {
			// Reload GB

			users = gb.split(CacheConverter.splitValues);

			for (String x : users) {
				String values[] = x.split(CacheConverter.splitX);

				// TODO melhorar
				//name;email;state;xxView;xxCorrect;xxIncorrect;xxDots
				User u = new User(values[0], values[1], StatusUser.ONLINE);
				try {
					u.setxxView(Integer.parseInt("" + values[3]));
					u.setxxCorrect(Integer.parseInt("" + values[4]));
					u.setxxIncorrect(Integer.parseInt("" + values[5]));
					u.setxxDots(Integer.parseInt("" + values[6]));
					u.setStatus(StatusUser.valueOf("" + values[2]));
				} catch (Exception e) {
					//TODO
				}

				if (!u.getName().isEmpty()) {
					Cache.groupB.add(u);
				}
			}
		}

	}
}
