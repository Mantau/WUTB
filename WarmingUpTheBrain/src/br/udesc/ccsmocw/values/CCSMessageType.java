/**
 * 
 */
package br.udesc.ccsmocw.values;

/**
 * @author mantau
 * 
 */
public enum CCSMessageType {

	JOIN_TO_GROUPWARE,     
	LOCK_ITEM,      
	CANCEL_LOCK,   
	IMAGE_CACHE,   
	COMMIT_CHANGES,    
	SYNC_REQUEST,      
	NOTIFICATION_CHANGE,        
	UNLOCK_ITEM,        
	ANSWER_CORRECT, 
	ANSWER_INCORRECT,
	NOTIFY_IR_RECEIVED,
	REQUEST_LOST_IR, 
	EXIT_GROUPWARE, 
	GAME_FINISHED,
	
	INSERT,
	DELETE,
	ACK,
	
	NULL; 

}
