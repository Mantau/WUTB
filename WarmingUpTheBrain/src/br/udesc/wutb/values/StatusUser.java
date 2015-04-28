/**
 * 
 */
package br.udesc.wutb.values;


/**
 * @author mantau
 *
 */
public enum StatusUser {
	ONLINE("online"),
	OFFLINE("offline"),
	ABSENT("absent"),
	CHAT("chat"),
	WORK("work");
	
	
	private String state;
	
	private StatusUser(String state){
		this.state = state;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return state;
	}
	
}
