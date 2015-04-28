/**
 * 
 */
package br.udesc.wutb.values;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.udesc.wutb.log.Message;

/**
 * @author mantau
 * 
 */
public abstract class Cache {
	
	//about cache 
	public static long RTum = 0;

	// about user
	public static User user = new User("WUTB", "WUTB@wutb.com", StatusUser.OFFLINE);
	public static String team = null;
	
	//Group list(used to refer groupA or groupB list.
	public static Set<User> myGroup;
	public static Set<User> otherGroup;

	//Group Users
	public static Set<User> groupA = new HashSet<User>();
	public static Set<User> groupB = new HashSet<User>();
	
	// question
	public static List<Question> questions = new LinkedList<Question>();
	private static Question currentQuestion;
	public static Question questionImageAnt;

	// log displayed on the app
	public static LinkedList<String> terminalLog = new LinkedList<String>();
	
	//pool of Messages. This messages are sent to server
	public static LinkedList<Message> logOut = new LinkedList<Message>();

	//chatMessages format: "Username▓Message....." see Config.split
	public static LinkedList<String> chat = new LinkedList<String>();
	
	//Sore of the game
	public static int questionsViewGA = 0;
	public static int questionsCorrectGA = 0;
	public static int questionsIncorrectGA = 0;
	public static int questionsDotsGA = 0;
	public static int questionsViewGB = 0;
	public static int questionsCorrectGB = 0;
	public static int questionsIncorrectGB = 0;
	public static int questionsDotsGB = 0;
	public static boolean isendOfTheGame = false;
	
	//Number of messages not read
	public static int numChatMessages = 0;


	public static void setCurrentQuestion(int id) {
		try{
			currentQuestion = questions.get(id);
		}catch(IndexOutOfBoundsException e){
			currentQuestion = null;
		}
	}
	
	public static Question getCurrentQuestion(){
		return currentQuestion;
	}
	
}
