/**
 * 
 */
package br.udesc.wutb.values;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.udesc.wutb.log.TypeMessage;

/**
 * @author mantau
 * @see These values ​​can be changed in the settings screen of the app (ListenerConfig.class)
 */
public abstract class Config {
	// time in ms to update "cache-to-UI" (default)
	public static int timeUpdateInterface = 5000;

	// time in ms to update "server-to-cache" (default)
	public static int timeUpdateCache = 10000;
	
	//IP Address server (default)
	public static String address = "192.168.0.100";
	
	//PORT Address server (default)
	public static int port = 8888;
	
	//Split caracter message (default)
	public static String split = "▓";
	
	//Disconnection time  (miliseconds) (default)
	public static int d = 1000;
	
	//Broadcast Interval (default)
	public static int iB= 3000;
	
	//Filtering information at the user's list (mYGroup & Other Group) (default)
	public static boolean displayUserView      = true;
	public static boolean displayUserCorrect   = true;
	public static boolean displayUserIncorrect = true;
	public static boolean displayUserDots      = true;
	
	//Filtering information at the question's board (default)
	public static boolean displayQuestionColors = true;
	public static boolean displayQuestionIcons  = true;
	public static boolean displayQuestionLabels = true;
	public static boolean displayQuestionCorrectOtherGroup = true;

	// Type messages displayed on terminal (default)
	public static Set<TypeMessage> typeMessageDisplayed = new HashSet<TypeMessage>(Arrays.asList(
		TypeMessage.ACTION,
		TypeMessage.NOTIFICATION, 
		TypeMessage.ERROR));
	
	// Type messages saved (default)
	public static Set<TypeMessage> typeMessageSaved = new HashSet<TypeMessage>(Arrays.asList(
		TypeMessage.ACTION,
		TypeMessage.NOTIFICATION, 
		TypeMessage.ERROR, 
		TypeMessage.CACHE_COHERENCE, 
		TypeMessage.WARNING));
}
