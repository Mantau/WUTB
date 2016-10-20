/**
 * 
 */
package br.udesc.ccscmocw.values;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import br.udesc.ccscmocw.log.Message;

/**
 * @author mantau
 * 
 */
public abstract class Cache {
	
	//about cache 
	public static int RTant = 0;
	public static int RTserver = 0;
	public static int timeUpdateCache = 15000;

	//Group Users
	public static Set<User> groupA = new HashSet<User>();
	public static Set<User> groupB = new HashSet<User>();
	
	// question
	public static LinkedList<Question> questions = new LinkedList<Question>();
	
	//Status question ant
	public static String statusQuestionsAux[] = new String[50];
	
	//pool of Messages. This messages are sent to server
	public static LinkedList<Message> fullLog = new LinkedList<Message>();
	public static LinkedList<Message> logOut = new LinkedList<Message>();

	//chatMessages format: "Username▓Message....." see Config.split
	public static LinkedList<String> chatGA = new LinkedList<String>();
	public static LinkedList<String> chatGB = new LinkedList<String>();
	
	//Sore of the game
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
	
}
