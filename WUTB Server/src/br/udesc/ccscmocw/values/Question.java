/**
 * 
 */
package br.udesc.ccscmocw.values;



/**
 * @author mantau
 * 
 */
public class Question {
	// The first question is "1". The arrays use the first index as "0".
	// No use this value to access the content of Cache.array
	private int number;

	// Need to update the UI components
	private String status = null;
	private String playerName = null;

	public Question(int number, String playerName, String status) {
		super();
		this.number = number;
		this.status = status;
		this.playerName = playerName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public Question clone() throws CloneNotSupportedException {
		return  (Question) super.clone();
	}

}
