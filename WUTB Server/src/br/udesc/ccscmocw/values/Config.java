/**
 * 
 */
package br.udesc.ccscmocw.values;

import br.udesc.ccscmocw.log.TypeMessage;

/**
 * @author mantau
 * 
 */
public abstract class Config {

	// time in ms to update "server-to-cache"
	public static int timeUpdateCache = 10000;
	
	//IP Address server
	public static String address = "192.168.0.100";
	
	//PORT Address server
	public static int port = 8888;
	
	//Split caracter message
	public static String split = "▓";
	
	//Disconnection time  (miliseconds)
	public static int d = 10000;
	
	//Broadcast Interval
	public static int iB= 30000;

	// Type messages displayed on terminal
	public static String[] typeMessageDisplayed = {
		TypeMessage.ACTION.toString(),
		TypeMessage.NOTIFICATION.toString(), 
		TypeMessage.ERROR.toString()};
	
	// Type messages saved
	public static String[] typeMessageSaved = {
		TypeMessage.ACTION.toString(),
		TypeMessage.NOTIFICATION.toString(), 
		TypeMessage.ERROR.toString(), 
		TypeMessage.CACHE_COHERENCE.toString(), 
		TypeMessage.WARNING.toString()};
}
