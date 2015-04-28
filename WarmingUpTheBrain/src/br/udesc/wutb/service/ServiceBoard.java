/**
 * 
 */
package br.udesc.wutb.service;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.dialog.DialogEndGame;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.Question;
import br.udesc.wutb.values.StatusQuestion;

/**
 * @author mantau
 * 
 */
public class ServiceBoard extends AsyncTask<String, Integer, String> {

	@Override
	protected String doInBackground(String... params) {
		try {
			// load cache array questions
			Resources resources = Params.getActivity().getResources();
			int[] arrayValues = Params.getQuestionValuesArrayRef();

			// Display team labels in the panel header game
			TextView myGroup = (TextView) Params.getActivityGame().findViewById(R.id.textViewMyGroup);
			myGroup.setText("My Group: " + Cache.team);

			TextView otherGroup = (TextView) Params.getActivityGame().findViewById(R.id.textViewOtherGroup);
			if (Cache.team.equalsIgnoreCase(resources.getString(R.string.str_team_a)))
				otherGroup.setText("Other Group: Team B");
			else
				otherGroup.setText("Other Group: Team A");

			// load Questions borad
			for (int i = 0; i < 40; i++) {
				String[] content = resources.getStringArray(arrayValues[i]);
				Question q = new Question(i, content);
				q.unlock();

				Cache.questions.add(q);

				publishProgress(i);
				Thread.sleep(100);
			}
			LogApp.n("Loading questions... [OK]");

			// Update board
			while (true) {
				// update all quations
				for (int i = 0; i < 40; i++) {
					publishProgress(i);
				}
				Thread.sleep(Config.timeUpdateInterface);
			}

		} catch (Exception e) {
			// TODO
			LogApp.e("Resources not found!>>" + e.getMessage());
		}
		return null;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onCancelled(String result) {
		super.onCancelled(result);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// Commented because make overhead of message log
		//Log.e("", "start@ServiceBoard.onProgressUpdate(..)");

		// Update the header
		if (values[0] == 0) {
			updateGameHeader();
			updateUserListHeaders();
			try{
				Params.getListenerUserList().refresh();
				Params.getListenerChat().refresh();
			}catch(Exception e){
				//TODO do nothing yet
			}
		}

		Question q = Cache.questions.get(values[0]);

		RelativeLayout question = (RelativeLayout) Params.getActivityGame().findViewById(Params.getQuestionBoardRef()[values[0]]);
		TextView user           = (TextView) Params.getActivityGame().findViewById(Params.getQuestionLabelUserRef()[values[0]]);
		ImageView image         = (ImageView) Params.getActivityGame().findViewById(Params.getQuestionImageViewRef()[values[0]]);

		question.setBackgroundResource(q.getStatus().getID());
		user.setText(Cache.questions.get(values[0]).getPlayerName());
		changeImageQuestionByState(image, q.getStatus());
		
		//Apply filtering options
		applyFilteringÒptions(question, user, image, q.getStatus());
		
		// verify is the end of the game
		if(Cache.isendOfTheGame){
			new DialogEndGame().showDefaultDialog(Params.getActivityGame());
		}

	}

	private void applyFilteringÒptions(RelativeLayout question, TextView user, ImageView image, StatusQuestion state) {
		question.setVisibility(View.VISIBLE);
		user.setVisibility(View.INVISIBLE);
		image.setVisibility(View.INVISIBLE);
		
		//Display colors questions
		if(Config.displayQuestionColors){
			//TODO
		}
		
		//Display icons questions
		if(Config.displayQuestionIcons){
			image.setVisibility(View.VISIBLE);
		}
		
		//Display labels questions
		if(Config.displayQuestionLabels){
			user.setVisibility(View.VISIBLE);
		}
		
		//Don't display questions correct by other group
		if((state == StatusQuestion.CORRECT_OTHER_GROUP)&&(!Config.displayQuestionCorrectOtherGroup)){
			question.setVisibility(View.INVISIBLE);
		}
		
	}

	private void updateGameHeader() {
		/*// Update message unread count
		Button btnGroup = (Button) Params.getActivityGame().findViewById(R.id.buttonGroup);
		btnGroup.setText("" + Cache.numChatMessages);*/

		// Update my team panel
		TextView myGroupParticipants = (TextView) Params.getActivityGame().findViewById(R.id.textViewMyGroupParticipants);
		TextView myGroupQuestions    = (TextView) Params.getActivityGame().findViewById(R.id.textViewMyGroupCorrectAnswers);
		TextView myGroupPoints       = (TextView) Params.getActivityGame().findViewById(R.id.textViewMyGroupPoints);
		
		myGroupParticipants.setText("" + Cache.myGroup.size() + " participants");

		// Update other team panel
		TextView otherGroupParticipants = (TextView) Params.getActivityGame().findViewById(R.id.textViewOtherGroupParticipants);
		TextView otherGroupQuestions    = (TextView) Params.getActivityGame().findViewById(R.id.textViewOtherGroupCorrectAnswers);
		TextView otherGroupPoints       = (TextView) Params.getActivityGame().findViewById(R.id.textViewOtherGroupPoints);
		
		otherGroupParticipants.setText("" + Cache.otherGroup.size()	+ " participants");

		if (Cache.team.equalsIgnoreCase(Params.getActivity().getString(R.string.str_team_a))) {
			myGroupQuestions.setText("" + Cache.questionsCorrectGA);
			myGroupPoints.setText("" + Cache.questionsDotsGA);
			otherGroupQuestions.setText("" + Cache.questionsCorrectGB);
			otherGroupPoints.setText("" + Cache.questionsDotsGB);
		} else {
			myGroupQuestions.setText("" + Cache.questionsCorrectGB);
			myGroupPoints.setText("" + Cache.questionsDotsGB);
			otherGroupQuestions.setText("" + Cache.questionsCorrectGA);
			otherGroupPoints.setText("" + Cache.questionsDotsGA);
		}
	}
	
	public void updateUserListHeaders() {
		//Load UI Components
		TextView titleMyGroup          = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupUserName);
		TextView resumeMyGroup         = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupUserStatus);
		TextView xxViewMyGroup         = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupView);
		TextView xxCorrectMyGroup      = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupCorrect);
		TextView xxDotsMyGroup         = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupDots);
		TextView xxIncorrectMyGroup    = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderMyGroupIncorrect);
		TextView titleOtherGroup       = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupUserName);
		TextView resumeOtherGroup      = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupUserStatus);
		TextView xxViewOtherGroup      = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupView);
		TextView xxCorrectOtherGroup   = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupCorrect);
		TextView xxIncorrectOtherGroup = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupIncorrect);
		TextView xxDotsOtherGroup      = (TextView) Params.getActivityGame().findViewById(R.id.textViewUserLIstHeaderOtherGroupDots);
		
		// refresh Headers
		resumeMyGroup.setText("" + Cache.myGroup.size() + " participants");
		resumeOtherGroup.setText("" + Cache.otherGroup.size() + " participants");
		
		if (Cache.team.equalsIgnoreCase(Params.getActivity().getString(R.string.str_team_a))) {
			titleMyGroup.setText("My goup: Team A");
			xxViewMyGroup.setText("" + Cache.questionsViewGA);
			xxCorrectMyGroup.setText("" + Cache.questionsCorrectGA);
			xxIncorrectMyGroup.setText("" + Cache.questionsIncorrectGA);
			xxDotsMyGroup.setText("" + Cache.questionsDotsGA);
			titleOtherGroup.setText("Other goup: Team B");
			xxViewOtherGroup.setText("" + Cache.questionsViewGB);
			xxCorrectOtherGroup.setText("" + Cache.questionsCorrectGB);
			xxIncorrectOtherGroup.setText("" + Cache.questionsIncorrectGB);
			xxDotsOtherGroup.setText("" + Cache.questionsDotsGB);
		} else {
			titleMyGroup.setText("My goup: Team B");
			xxViewMyGroup.setText("" + Cache.questionsViewGB);
			xxCorrectMyGroup.setText("" + Cache.questionsCorrectGB);
			xxIncorrectMyGroup.setText("" + Cache.questionsIncorrectGB);
			xxDotsMyGroup.setText("" + Cache.questionsDotsGB);
			titleOtherGroup.setText("Other goup: Team A");
			xxViewOtherGroup.setText("" + Cache.questionsViewGA);
			xxCorrectOtherGroup.setText("" + Cache.questionsCorrectGA);
			xxIncorrectOtherGroup.setText("" + Cache.questionsIncorrectGA);
			xxDotsOtherGroup.setText("" + Cache.questionsDotsGA);
		}
	}
	

	private void changeImageQuestionByState(ImageView image,
			StatusQuestion state) {
		int resid = R.drawable.none_image;

		switch (state) {
		case CORRECT_ME:
			resid = R.drawable.question_user_correct;
			break;

		case CORRECT_MY_GROUP:
			resid = R.drawable.question_group_correct;
			break;

		case CORRECT_OTHER_GROUP:
			// TODO none yet
			break;

		case INCORRECT_ME:
			resid = R.drawable.question_user_incorrect;
			break;

		case INCORRECT_MY_GROUP:
			resid = R.drawable.question_group_incorrect;
			break;

		case INCORRECT_OTHER_GROUP:
			resid = R.drawable.question_double_score;
			break;

		case LOCKED_ME:
			resid = R.drawable.user;
			break;

		case LOCKED_MY_GROUP:
			resid = R.drawable.group;
			break;

		case LOCKED_OTHER_GROUP:
			// TODO none yet
			break;

		case NO_SELECTED_1:
			// TODO none yet
			break;

		case NO_SELECTED_2:
			// TODO none yet
			break;

		default:
			// TODO none yet
			break;

		}

		image.setBackgroundResource(resid);
		image.refreshDrawableState();

	}

}
