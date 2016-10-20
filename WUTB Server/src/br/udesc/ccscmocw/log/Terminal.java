/**
 * 
 */
package br.udesc.ccscmocw.log;

import java.util.ArrayList;

/**
 * @author mantau
 *
 */
public abstract class Terminal {

	
	public static ArrayList<String> prints = new ArrayList<>();
	
	
	public static void printMessage(String str){
		if(prints.contains(str))
			return;
		
		prints.add(str);
		
		System.out.println(str);
	}
}
