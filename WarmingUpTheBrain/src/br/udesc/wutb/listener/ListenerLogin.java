/**
 * 
 */
package br.udesc.wutb.listener;

import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.activity.ActivityLogin;
import br.udesc.wutb.activity.AnimatedActivity;
import br.udesc.wutb.log.LogApp;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.StatusUser;
import br.udesc.wutb.values.User;

/**
 * @author mantau
 * 
 */
public class ListenerLogin {
	private ActivityLogin activity = null;

	public ListenerLogin(ActivityLogin activity) {
		this.activity = activity;
	}

	public void addListeners() {
		Button buttonLogin = (Button) activity.findViewById(R.id.buttonStartGame);
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username, email;

				EditText editTextName = (EditText) activity.findViewById(R.id.editTextUsername);
				EditText editTextEmail = (EditText) activity.findViewById(R.id.editTextEmail);
				RadioGroup radioGroupTeam = (RadioGroup) activity.findViewById(R.id.radioGroupSelectTeam);

				int idTeamSelected = radioGroupTeam.getCheckedRadioButtonId();
				username = (String) editTextName.getText().toString();
				email = (String) editTextEmail.getText().toString();

				if (verifyInputs(username, email, idTeamSelected)) {
					// set cache values
					Cache.user = new User(username, email, StatusUser.ONLINE);
					Cache.team = (String) ((RadioButton) radioGroupTeam.findViewById(idTeamSelected)).getText().toString();

					// set teams
					if (Cache.team.equalsIgnoreCase(activity
							.getString(R.string.str_team_a))) {
						Cache.myGroup = Cache.groupA;
						Cache.otherGroup = Cache.groupB;
					} else {
						Cache.myGroup = Cache.groupB;
						Cache.otherGroup = Cache.groupA;
					}

					// add user to team
					Cache.myGroup.add(Cache.user);
					
					// Create a join message notification for all users
					@SuppressWarnings("deprecation")
					Message m = new Message(TypeMessage.CACHE_COHERENCE,CCSMessageType.JOIN_TO_GROUPWARE, new Date().toGMTString(), Cache.user.getName(), Cache.team);
					Cache.logOut.addLast(m);

					// start the game board
					openActivityGame();

				}

			}
		});

	}

	private void openActivityGame() {
		Intent intent = new Intent(activity.getBaseContext(),
				ActivityGame.class);
		Params.getActivityLogin().startAnimatedActivity(intent,
				AnimatedActivity.SLIDE_FROM_LEFT);
		Params.getActivityLogin().finish();
	}

	/**
	 * @exception: Verifies that the data was properly informed by user
	 * @return: <true> if values are corrects. <false> if failure
	 */
	private boolean verifyInputs(String username, String email,
			int idTeamSelected) {
		boolean status = true;
		View viewUsername, viewEmail, viewTeam;

		viewUsername = activity.findViewById(R.id.linearLayoutLoginEditUsername);
		viewEmail = activity.findViewById(R.id.linearLayoutLoginEditEmail);
		viewTeam = activity.findViewById(R.id.linearLayoutLoginSelectTeam);

		// Clear state UI elements
		clearhighlightField(viewUsername);
		clearhighlightField(viewEmail);
		clearhighlightField(viewTeam);

		// Check the inputs
		if (username.length() <= 1) {
			status = false;
			LogApp.e(Params.getActivity().getString(R.string.error_name_not_informed));

			// Display a red border i the UI element
			highlightField(viewUsername);
		}
		if (email.length() <= 1) {
			status = false;
			LogApp.e(Params.getActivity().getString(R.string.error_email_not_informed));

			// Display a red border i the UI element
			highlightField(viewEmail);
		}
		if (idTeamSelected < 0) {
			status = false;
			LogApp.e(Params.getActivity().getString(R.string.error_team_not_selected));

			// Display a red border i the UI element
			highlightField(viewTeam);
		}
		return status;
	}

	private void highlightField(View view) {
		view.setBackgroundResource(R.drawable.field_state_invalid);

		Animation shake = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
		view.startAnimation(shake);

		view.refreshDrawableState();
	}

	@SuppressLint("NewApi")
	private void clearhighlightField(View view) {
		view.setBackgroundResource(R.drawable.field_state_valid);
		view.refreshDrawableState();
	}
}
