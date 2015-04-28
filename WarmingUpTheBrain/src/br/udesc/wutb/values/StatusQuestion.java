/**
 * 
 */
package br.udesc.wutb.values;

import br.udesc.wutb.R;

/**
 * @author mantau
 *
 */
public enum StatusQuestion {
	NO_SELECTED_1(R.drawable.question_noselected_1),
	NO_SELECTED_2(R.drawable.question_noselected_2),
	CORRECT_OTHER_GROUP(R.drawable.question_othergroup),
	CORRECT_MY_GROUP(R.drawable.question_correct),
	CORRECT_ME(R.drawable.question_correct),
	INCORRECT_OTHER_GROUP(R.drawable.question_doublepoints),
	INCORRECT_MY_GROUP(R.drawable.question_incorrect),
	INCORRECT_ME(R.drawable.question_incorrect),
	LOCKED_MY_GROUP(R.drawable.question_locked),
	LOCKED_ME(R.drawable.question_locked),
	LOCKED_OTHER_GROUP(R.drawable.question_locked);
	
	private int ID;
	
	private StatusQuestion(int ID){
		this.ID = ID;
	}
	
	public int getID(){
		return ID;
	}
	
}
