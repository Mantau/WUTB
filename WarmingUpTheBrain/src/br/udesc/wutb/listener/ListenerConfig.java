/**
 * 
 */
package br.udesc.wutb.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Config;

/**
 * @author mantau
 * 
 */
public class ListenerConfig {
	private ActivityGame activity;
	
	// UI Elements
	private Button btnDefault;
	private Button btnApply;
	private Button btnConnect;
	private EditText edtIp;
	private EditText edtPort;
	private CheckBox boxxxView;
	private CheckBox boxxxCorrect;
	private CheckBox boxxxIncorrect;
	private CheckBox boxxxDots;
	private CheckBox boxOtherGroupDisplayCorrect;
	private CheckBox boxTerminalActions;
	private CheckBox boxTerminalNotifications;
	private CheckBox boxTerminalCacheCoherence;
	private CheckBox boxTerminalChat;
	private CheckBox boxTerminalWarning;
	private CheckBox boxTerminalError;
	private RadioButton radioColors;
	private RadioButton radioColorsIcons;
	private RadioButton radioColorsIconsLabels;

	public ListenerConfig(ActivityGame activity) {
		this.activity = activity;
		// Load UI components
		btnDefault = (Button) activity.findViewById(R.id.buttonGameConfigRestoreChanges);
		btnApply = (Button) activity.findViewById(R.id.buttonGameConfigAppyChanges);

		// Connection options
		btnConnect = (Button) activity.findViewById(R.id.buttonConfigTryConnection);
		edtIp = (EditText) activity.findViewById(R.id.editTextConfigAddressServer);
		edtPort = (EditText) activity.findViewById(R.id.editTextConfigPortServer);

		// User list display options
		boxxxView = (CheckBox) activity.findViewById(R.id.checkBoxConfigUserLIstDisplayUserView);
		boxxxCorrect = (CheckBox) activity.findViewById(R.id.checkBoxConfigUserLIstDisplayUserCorrect);
		boxxxIncorrect = (CheckBox) activity.findViewById(R.id.checkBoxConfigUserLIstDisplayUserInorrect);
		boxxxDots = (CheckBox) activity.findViewById(R.id.checkBoxConfigUserLIstDisplayUserDots);

		// Game board options
		radioColors = (RadioButton) activity.findViewById(R.id.radioFilteringGameBoardColors);
		radioColorsIcons = (RadioButton) activity.findViewById(R.id.radioFilteringGameBoardColorsAndIcons);
		radioColorsIconsLabels = (RadioButton) activity.findViewById(R.id.radioFilteringGameBoardColorsIconsLabels);
		boxOtherGroupDisplayCorrect = (CheckBox) activity.findViewById(R.id.checkBoxFilteringGameBoardDisplayQuestionCorrectOtherGroup);

		// Terminal options
		boxTerminalActions = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayActions);
		boxTerminalNotifications = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayNotifications);
		boxTerminalCacheCoherence = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayCacheCoherence);
		boxTerminalChat = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayChat);
		boxTerminalWarning = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayWarning);
		boxTerminalError = (CheckBox) activity.findViewById(R.id.checkBoxConfigTerminalDisplayErrors);

	}

	public void addListeners() {
		btnConnect.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {
				String ip = edtIp.getText().toString();
				String port = edtPort.getText().toString();
				
				//Try connect
				//TODO 
			}
		});

		btnDefault.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doDefaultOptions();
				Toast.makeText(activity, "Filtering options restored!", Toast.LENGTH_LONG).show();
			}
		});

		btnApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				applyChanges();
				Toast.makeText(activity, "Filtering options applied!", Toast.LENGTH_LONG).show();
			}
		});
	}

	protected void applyChanges() {
		// Change user's filtering options
		Config.displayUserView = boxxxView.isChecked();
		Config.displayUserCorrect = boxxxCorrect.isChecked();
		Config.displayUserIncorrect = boxxxIncorrect.isChecked();
		Config.displayUserDots = boxxxDots.isChecked();

		// Change game board filtering options
		Config.displayQuestionCorrectOtherGroup = boxOtherGroupDisplayCorrect
				.isChecked();
		if (radioColors.isChecked()) {
			Config.displayQuestionColors = true;
			Config.displayQuestionIcons = false;
			Config.displayQuestionLabels = false;
		} else if (radioColorsIcons.isChecked()) {
			Config.displayQuestionColors = true;
			Config.displayQuestionIcons = true;
			Config.displayQuestionLabels = false;
		} else if (radioColorsIconsLabels.isChecked()) {
			Config.displayQuestionColors = true;
			Config.displayQuestionIcons = true;
			Config.displayQuestionLabels = true;
		}

		// Change terminal filtering options
		Config.typeMessageDisplayed.clear();
		if (boxTerminalActions.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.ACTION);
		}
		if (boxTerminalNotifications.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.NOTIFICATION);
		}

		if (boxTerminalCacheCoherence.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.CACHE_COHERENCE);
		}

		if (boxTerminalChat.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.CHAT);
		}

		if (boxTerminalWarning.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.WARNING);
		}

		if (boxTerminalError.isChecked()) {
			Config.typeMessageDisplayed.add(TypeMessage.ERROR);
		}
	}

	protected void doDefaultOptions() {
		//Restore UI status
		boxxxView.setChecked(true);
		boxxxCorrect.setChecked(true);
		boxxxIncorrect.setChecked(true);
		boxxxDots.setChecked(true);
		boxOtherGroupDisplayCorrect.setChecked(true);
		boxTerminalActions.setChecked(true);
		boxTerminalError.setChecked(true);
		boxTerminalNotifications.setChecked(false);
		boxTerminalCacheCoherence.setChecked(false);
		boxTerminalChat.setChecked(false);
		boxTerminalWarning.setChecked(false);
		radioColors.setChecked(false);
		radioColorsIcons.setChecked(false);
		radioColorsIconsLabels.setChecked(true);
		
		//Restore Config options
		applyChanges();
	}

}
