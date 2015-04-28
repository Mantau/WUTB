/**
 * 
 */
package br.udesc.wutb.core;

import java.util.Date;

import android.content.Intent;
import android.widget.Toast;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.R;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.Question;
import br.udesc.wutb.values.StatusQuestion;

/**
 * @author mantau
 * 
 */
public abstract class GameManager {

	private static boolean isCorrectAnswer(Question currentQuestion, int id) {
		String altSelected = "";

		switch (id) {
		case R.id.radioButtonAlt1:
			altSelected = "a";
			break;

		case R.id.radioButtonAlt2:
			altSelected = "b";
			break;

		case R.id.radioButtonAlt3:
			altSelected = "c";
			break;

		case R.id.radioButtonAlt4:
			altSelected = "d";
			break;

		case R.id.radioButtonAlt5:
			altSelected = "e";
			break;
		}
		return altSelected.equalsIgnoreCase(currentQuestion.getAnswer());
	}

	public static void doActionSubmit(Question currentQuestion, int id) {
		int q = (Cache.getCurrentQuestion().getNumber() - 1);

		if (isCorrectAnswer(Cache.getCurrentQuestion(), id)) {
			@SuppressWarnings("deprecation")
			Message m = new Message(TypeMessage.CACHE_COHERENCE,CCSMessageType.ANSWER_CORRECT, new Date().toGMTString(),Cache.user.getName(), "" + q);

			Cache.logOut.addLast(m);
			Cache.questions.get(q).setStatus(StatusQuestion.CORRECT_ME);

			String txt = "Question " + (q + 1) + " correct!";
			LogApp.a(txt);
			Toast.makeText(Params.getActivity(), txt, Toast.LENGTH_SHORT).show();
		} else {
			@SuppressWarnings("deprecation")
			Message m = new Message(TypeMessage.CACHE_COHERENCE,CCSMessageType.ANSWER_INCORRECT, new Date().toGMTString(),Cache.user.getName(), "" + q);

			Cache.logOut.addLast(m);
			Cache.questions.get(q).setStatus(StatusQuestion.INCORRECT_ME);

			String txt = "Question " + (q + 1) + " incorrect!";
			LogApp.a(txt);
			Toast.makeText(Params.getActivity(), txt, Toast.LENGTH_SHORT).show();
		}
	}

	public static void cancelLockQuestion() {
		int q = (Cache.getCurrentQuestion().getNumber() - 1);

		@SuppressWarnings("deprecation")
		Message m = new Message(TypeMessage.CACHE_COHERENCE,CCSMessageType.CANCEL_LOCK, new Date().toGMTString(),Cache.user.getName(), "" + q);

		Cache.logOut.addLast(m);
		Cache.questions.get(q).unlock();
		Cache.questions.get(q).setPlayerName("");
	}

	public static void finish() {
		// Send all Messages to server
		while (!Cache.logOut.isEmpty()) {
			// Wait to app send all Messages to server...
		}

		// Close all Services/Background Tasks
		if (Params.getActivity() != null) {
			Intent intent = new Intent("CCS_MoCW");
			Params.getActivity().stopService(intent);
		}

		// Close all Activity
		if (Params.getActivity() != null) {
			Params.getActivity().finish();
		}

		if (Params.getActivityConnection() != null) {
			Params.getActivityConnection().finish();
		}

		if (Params.getActivityGame() != null) {
			Params.getActivityGame().finish();
		}

		if (Params.getActivityLogin() != null) {
			Params.getActivityLogin().finish();
		}
	}

}
