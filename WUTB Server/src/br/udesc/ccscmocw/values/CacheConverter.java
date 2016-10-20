/**
 * 
 */
package br.udesc.ccscmocw.values;

import java.util.Date;
import java.util.Set;

import br.udesc.ccscmocw.log.Message;
import br.udesc.ccscmocw.log.TypeMessage;

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
	// RTum●scoreGA●scoreGB●questionsGA●questionsGB●usersGA●usersGB●questions

	// users are divided: user1○user2○user3○...○userX
	// questions are divided: question1○question2○question3○...○question50

	// a user is composed of: name;email;state
	// a question is composed of: number;user;state

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
			return "" + Cache.RTserver                  + CacheConverter.splitParams 
					  + Cache.questionsViewGA           + CacheConverter.splitParams 
					  + Cache.questionsCorrectGA        + CacheConverter.splitParams 
					  + Cache.questionsIncorrectGA      + CacheConverter.splitParams 
					  + Cache.questionsDotsGA           + CacheConverter.splitParams 
					  + Cache.questionsViewGB           + CacheConverter.splitParams 
					  + Cache.questionsCorrectGB        + CacheConverter.splitParams 
					  + Cache.questionsIncorrectGB      + CacheConverter.splitParams 
					  + Cache.questionsDotsGB           + CacheConverter.splitParams 
					  + convertUsersToStr(Cache.groupA)	+ CacheConverter.splitParams 
					  + convertUsersToStr(Cache.groupB)	+ CacheConverter.splitParams 
					  + convertQuestionsToStr();
		}
	
	public static void sendAllCache() {
		@SuppressWarnings("deprecation")
		Message m = new Message(TypeMessage.CACHE_COHERENCE, CCSMessageType.IMAGE_CACHE, new Date().toGMTString(), "WUTB", CacheConverter.cacheToStr());
		Cache.logOut.addFirst(m);
	}

	private static String convertQuestionsToStr() {
		String txt = "";
		for(Question q:Cache.questions){
			if(!txt.isEmpty()){
				txt+= CacheConverter.splitValues;
			}
			txt+= q.getNumber() + CacheConverter.splitX +
				  q.getPlayerName() + CacheConverter.splitX +
				  q.getStatus();
		}
		return txt;
	}

	private static String convertUsersToStr(Set<User> users) {
		//Test it list contains users
		if(users.isEmpty()){
			return null;
		}
		
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
}
