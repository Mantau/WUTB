/**
 * 
 */
package br.udesc.wutb.values;

import android.widget.ScrollView;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.AnimatedActivity;
import br.udesc.wutb.listener.ListenerBoard;
import br.udesc.wutb.listener.ListenerChat;
import br.udesc.wutb.listener.ListenerConfig;
import br.udesc.wutb.listener.ListenerQuestion;
import br.udesc.wutb.listener.ListenerTabs;
import br.udesc.wutb.listener.ListenerTerminal;
import br.udesc.wutb.listener.ListenerUserList;

/**
 * @author mantau
 * 
 */
public abstract class Params {

	// Terminal elements
	private static TextView terminal = null;
	private static ScrollView scrollTerminal = null;

	// Activity
	private static AnimatedActivity activity = null;
	private static AnimatedActivity activityLogin = null;
	private static AnimatedActivity activityConnection = null;
	private static AnimatedActivity activityGame = null;
	
	//Listeners
	private static ListenerBoard    listenerBoard;
	private static ListenerQuestion listenerQuestion;
	private static ListenerUserList listenerUserList;
	private static ListenerChat     listenerChat;
	private static ListenerTerminal listenerTerminal;
	private static ListenerConfig   listenerConfig;
	private static ListenerTabs     listenerTabs;

	
	// This contains the reference of question's UI painel
	private static final int questionBoardRef[] = { R.id.relativeLayoutGameQ01,
			R.id.relativeLayoutGameQ02, R.id.relativeLayoutGameQ03,
			R.id.relativeLayoutGameQ04, R.id.relativeLayoutGameQ05,
			R.id.relativeLayoutGameQ06, R.id.relativeLayoutGameQ07,
			R.id.relativeLayoutGameQ08, R.id.relativeLayoutGameQ09,
			R.id.relativeLayoutGameQ10, R.id.relativeLayoutGameQ11,
			R.id.relativeLayoutGameQ12, R.id.relativeLayoutGameQ13,
			R.id.relativeLayoutGameQ14, R.id.relativeLayoutGameQ15,
			R.id.relativeLayoutGameQ16, R.id.relativeLayoutGameQ17,
			R.id.relativeLayoutGameQ18, R.id.relativeLayoutGameQ19,
			R.id.relativeLayoutGameQ20, R.id.relativeLayoutGameQ21,
			R.id.relativeLayoutGameQ22, R.id.relativeLayoutGameQ23,
			R.id.relativeLayoutGameQ24, R.id.relativeLayoutGameQ25,
			R.id.relativeLayoutGameQ26, R.id.relativeLayoutGameQ27,
			R.id.relativeLayoutGameQ28, R.id.relativeLayoutGameQ29,
			R.id.relativeLayoutGameQ30, R.id.relativeLayoutGameQ31,
			R.id.relativeLayoutGameQ32, R.id.relativeLayoutGameQ33,
			R.id.relativeLayoutGameQ34, R.id.relativeLayoutGameQ35,
			R.id.relativeLayoutGameQ36, R.id.relativeLayoutGameQ37,
			R.id.relativeLayoutGameQ38, R.id.relativeLayoutGameQ39,
			R.id.relativeLayoutGameQ40};

	// This contains the reference of "user-questions" UI painel
	private static final int questionLabelUserRef[] = { R.id.textViewUserQ01,
			R.id.textViewUserQ02, R.id.textViewUserQ03, R.id.textViewUserQ04,
			R.id.textViewUserQ05, R.id.textViewUserQ06, R.id.textViewUserQ07,
			R.id.textViewUserQ08, R.id.textViewUserQ09, R.id.textViewUserQ10,
			R.id.textViewUserQ11, R.id.textViewUserQ12, R.id.textViewUserQ13,
			R.id.textViewUserQ14, R.id.textViewUserQ15, R.id.textViewUserQ16,
			R.id.textViewUserQ17, R.id.textViewUserQ18, R.id.textViewUserQ19,
			R.id.textViewUserQ20, R.id.textViewUserQ21, R.id.textViewUserQ22,
			R.id.textViewUserQ23, R.id.textViewUserQ24, R.id.textViewUserQ25,
			R.id.textViewUserQ26, R.id.textViewUserQ27, R.id.textViewUserQ28,
			R.id.textViewUserQ29, R.id.textViewUserQ30, R.id.textViewUserQ31,
			R.id.textViewUserQ32, R.id.textViewUserQ33, R.id.textViewUserQ34,
			R.id.textViewUserQ35, R.id.textViewUserQ36, R.id.textViewUserQ37,
			R.id.textViewUserQ38, R.id.textViewUserQ39, R.id.textViewUserQ40};

	// This contains the reference of "image-questions" UI painel
	private static final int questionImageViewRef[] = { R.id.imageViewQ01,
			R.id.imageViewQ02, R.id.imageViewQ03, R.id.imageViewQ04,
			R.id.imageViewQ05, R.id.imageViewQ06, R.id.imageViewQ07,
			R.id.imageViewQ08, R.id.imageViewQ09, R.id.imageViewQ10,
			R.id.imageViewQ11, R.id.imageViewQ12, R.id.imageViewQ13,
			R.id.imageViewQ14, R.id.imageViewQ15, R.id.imageViewQ16,
			R.id.imageViewQ17, R.id.imageViewQ18, R.id.imageViewQ19,
			R.id.imageViewQ20, R.id.imageViewQ21, R.id.imageViewQ22,
			R.id.imageViewQ23, R.id.imageViewQ24, R.id.imageViewQ25,
			R.id.imageViewQ26, R.id.imageViewQ27, R.id.imageViewQ28,
			R.id.imageViewQ29, R.id.imageViewQ30, R.id.imageViewQ31,
			R.id.imageViewQ32, R.id.imageViewQ33, R.id.imageViewQ34,
			R.id.imageViewQ35, R.id.imageViewQ36, R.id.imageViewQ37,
			R.id.imageViewQ38, R.id.imageViewQ39, R.id.imageViewQ40};

	// This contains the reference of "content-question" UI painel
	private static final int questionValuesArrayRef[] = { R.array.q01,
			R.array.q02, R.array.q03, R.array.q04, R.array.q05, R.array.q06,
			R.array.q07, R.array.q08, R.array.q09, R.array.q10, R.array.q11,
			R.array.q12, R.array.q13, R.array.q14, R.array.q15, R.array.q16,
			R.array.q17, R.array.q18, R.array.q19, R.array.q20, R.array.q21,
			R.array.q22, R.array.q23, R.array.q24, R.array.q25, R.array.q26,
			R.array.q27, R.array.q28, R.array.q29, R.array.q30, R.array.q31,
			R.array.q32, R.array.q33, R.array.q34, R.array.q35, R.array.q36,
			R.array.q37, R.array.q38, R.array.q39, R.array.q40};

	public static AnimatedActivity getActivity() {
		return activity;
	}

	public static void setActivity(AnimatedActivity activity) {
			Params.activity = activity;
	}

	public static AnimatedActivity getActivityLogin() {
		return activityLogin;
	}

	public static void setActivityLogin(AnimatedActivity activityLogin) {
			Params.activityLogin = activityLogin;
	}

	public static AnimatedActivity getActivityConnection() {
		return activityConnection;
	}

	public static void setActivityConnection(AnimatedActivity activityConnection) {
			Params.activityConnection = activityConnection;
	}

	public static AnimatedActivity getActivityGame() {
		return activityGame;
	}

	public static void setActivityGame(AnimatedActivity activityGame) {
			Params.activityGame = activityGame;
	}

	public static TextView getTerminal() {
		return terminal;
	}

	public static void setTerminal(TextView terminal) {
			Params.terminal = terminal;
	}

	public static ScrollView getScrollTerminal() {
		return scrollTerminal;
	}

	public static void setScrollTerminal(ScrollView scrollTerminal) {
			Params.scrollTerminal = scrollTerminal;
	}

	public static int[] getQuestionBoardRef() {
		return questionBoardRef;
	}

	public static int[] getQuestionLabelUserRef() {
		return questionLabelUserRef;
	}

	public static int[] getQuestionValuesArrayRef() {
		return questionValuesArrayRef;
	}

	public static int[] getQuestionImageViewRef() {
		return questionImageViewRef;
	}

	public static ListenerBoard getListenerBoard() {
		return listenerBoard;
	}

	public static void setListenerBoard(ListenerBoard listenerBoard) {
		Params.listenerBoard = listenerBoard;
	}

	public static ListenerQuestion getListenerQuestion() {
		return listenerQuestion;
	}

	public static void setListenerQuestion(ListenerQuestion listenerQuestion) {
		Params.listenerQuestion = listenerQuestion;
	}

	public static ListenerUserList getListenerUserList() {
		return listenerUserList;
	}

	public static void setListenerUserList(ListenerUserList listenerUserList) {
		Params.listenerUserList = listenerUserList;
	}

	public static ListenerChat getListenerChat() {
		return listenerChat;
	}

	public static void setListenerChat(ListenerChat listenerChat) {
		Params.listenerChat = listenerChat;
	}

	public static ListenerTerminal getListenerTerminal() {
		return listenerTerminal;
	}

	public static void setListenerTerminal(ListenerTerminal listenerTerminal) {
		Params.listenerTerminal = listenerTerminal;
	}

	public static ListenerConfig getListenerConfig() {
		return listenerConfig;
	}

	public static void setListenerConfig(ListenerConfig listenerConfig) {
		Params.listenerConfig = listenerConfig;
	}

	public static ListenerTabs getListenerTabs() {
		return listenerTabs;
	}

	public static void setListenerTabs(ListenerTabs listenerTabs) {
		Params.listenerTabs = listenerTabs;
	}
}
