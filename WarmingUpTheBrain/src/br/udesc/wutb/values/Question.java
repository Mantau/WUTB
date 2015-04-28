/**
 * 
 */
package br.udesc.wutb.values;

/**
 * @author mantau
 * 
 */
public class Question implements Cloneable{
	//The first question is "1". The arrays use the first index as "0".
	//No use this value to access the content of Cache.array
	private int number;
	
	private String category;
	private String content;
	private String altA;
	private String altB;
	private String altC;
	private String altD;
	private String altE;
	private String answer;

	// Need to update the UI components
	private StatusQuestion status = StatusQuestion.NO_SELECTED_1;
	private String playerName;

	public Question(int number, String[] strArray) {
		this.setNumber(number + 1);
		this.category = strArray[0];
		this.content = strArray[1];
		this.altA = strArray[2];
		this.altB = strArray[3];
		this.altC = strArray[4];
		this.altD = strArray[5];
		this.altE = strArray[6];
		this.answer = strArray[7];
	}

	public String getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public String getAltA() {
		return altA;
	}

	public String getAltB() {
		return altB;
	}

	public String getAltC() {
		return altC;
	}

	public String getAltD() {
		return altD;
	}

	public String getAltE() {
		return altE;
	}

	public String getAnswer() {
		return answer;
	}

	public StatusQuestion getStatus() {
		return status;
	}

	public void setStatus(StatusQuestion status) {
		this.status = status;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void unlock() {
		if((number-1)%2==0){
			status = StatusQuestion.NO_SELECTED_1;
		}
		else{
			status = StatusQuestion.NO_SELECTED_2;
		}
		
		playerName = "";
	}

	@Override
	public Question clone() throws CloneNotSupportedException {
		return  (Question) super.clone();
	}
	
	

}
