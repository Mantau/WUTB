/**
 * 
 */
package br.udesc.wutb.listener;

import java.util.Arrays;
import java.util.Date;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.StatusQuestion;

/**
 * @author mantau
 * 
 */
public class ListenerBoard {
	private ActivityGame activity = null;

	public ListenerBoard(ActivityGame activity) {
		this.activity = activity;
	}

	public void addListeners() {
		final int questions[] = Params.getQuestionBoardRef();
		for (final int x : questions) {
			try {
				RelativeLayout question = (RelativeLayout) activity
						.findViewById(x);
				question.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						int id = Arrays.binarySearch(questions, 0,
								questions.length, x);
						LogApp.a("Question selected: " + (id + 1)
								+ "cheking your state...");
						checkQuestionSelected(id);
					}
				});
				
			} catch (Exception e) {
				//TODO
			}
		}
	}

	protected void checkQuestionSelected(int id) {
		StatusQuestion st = Cache.questions.get(id).getStatus();
		if (st == StatusQuestion.NO_SELECTED_1
				|| st == StatusQuestion.NO_SELECTED_2
				|| st == StatusQuestion.INCORRECT_OTHER_GROUP
				|| st == StatusQuestion.INCORRECT_MY_GROUP
				|| st == StatusQuestion.LOCKED_ME) {
			LogApp.a("Locking question " + (id + 1) + "...  [OK]");
			
			//Unlock the current question (if exist) and (if is locked for me)
			if(Cache.getCurrentQuestion() != null){
				if(Cache.getCurrentQuestion().getStatus() == StatusQuestion.LOCKED_ME){
					Cache.getCurrentQuestion().unlock();
					
					//Add event in the log
					@SuppressWarnings("deprecation")
					Message m = new Message(TypeMessage.CACHE_COHERENCE, CCSMessageType.UNLOCK_ITEM, new Date()
					.toGMTString(), Cache.user.getName(), "" + id);
					Cache.logOut.addLast(m);
				}
			}
			//Lock the new question
			Cache.setCurrentQuestion(id);
			Cache.questions.get(id).setStatus(StatusQuestion.LOCKED_ME);
			
			//Add event in the log
			@SuppressWarnings("deprecation")
			Message m = new Message(TypeMessage.CACHE_COHERENCE, CCSMessageType.LOCK_ITEM, new Date()
			.toGMTString(), Cache.user.getName(), "" + id);
			Cache.logOut.addLast(m);
			
			//Open the question
			openQuestion();
		} else {
			LogApp.a("Question " + (id + 1) + "already in use.");
		}

	}

	protected void openQuestion() {
		Params.getListenerQuestion().loadCurrentQuestion();
		Params.getListenerTabs().changeContentToQuestion();
	}
}
