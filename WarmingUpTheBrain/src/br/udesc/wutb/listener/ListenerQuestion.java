/**
 * 
 */
package br.udesc.wutb.listener;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.core.GameManager;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.Question;

/**
 * @author mantau
 * 
 */
public class ListenerQuestion {
	private ActivityGame activity = null;

	public ListenerQuestion(ActivityGame activity) {
		this.activity = activity;
	}

	public void addListeners() {
		Button btnCancel = (Button) activity.findViewById(R.id.buttonQuestionCancel);
		Button btnSubmit = (Button) activity.findViewById(R.id.buttonQuestionSubmit);

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GameManager.cancelLockQuestion();
				Params.getListenerTabs().changeContentToBoard();
				
				//Clear selected idQuestion in the Cache
				Cache.setCurrentQuestion(-1);
			}
		});

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RadioGroup radioGroup = (RadioGroup) activity.findViewById(R.id.radioGroupQuestionAlt);
				int id = radioGroup.getCheckedRadioButtonId();
				if (id > 0) {
					GameManager.doActionSubmit(Cache.getCurrentQuestion(), id);
					
					clearhighlightField(radioGroup);
					Params.getListenerTabs().changeContentToBoard();
					
					//Clear selected idQuestion in the Cache
					Cache.setCurrentQuestion(-1);
				} else {
					highlightField(radioGroup);
				}
			}
		});

	}

	public void loadCurrentQuestion() {
		Question question = Cache.getCurrentQuestion();

		Log.e("", "" + Cache.getCurrentQuestion().getContent());

		TextView number = (TextView) activity
				.findViewById(R.id.textViewQuestionNumber);
		TextView category = (TextView) activity
				.findViewById(R.id.textViewCategoryQuestion);
		TextView content = (TextView) activity
				.findViewById(R.id.textViewQuestionContent);

		RadioButton radioQ1 = (RadioButton) activity
				.findViewById(R.id.radioButtonAlt1);
		RadioButton radioQ2 = (RadioButton) activity
				.findViewById(R.id.radioButtonAlt2);
		RadioButton radioQ3 = (RadioButton) activity
				.findViewById(R.id.radioButtonAlt3);
		RadioButton radioQ4 = (RadioButton) activity
				.findViewById(R.id.radioButtonAlt4);
		RadioButton radioQ5 = (RadioButton) activity
				.findViewById(R.id.radioButtonAlt5);

		RadioGroup radioGroup = (RadioGroup) activity
				.findViewById(R.id.radioGroupQuestionAlt);
		ScrollView scrollQuestion = (ScrollView) activity
				.findViewById(R.id.scrollViewQuestionContent);

		number.setText("" + question.getNumber());
		category.setText(question.getCategory());
		content.setText(question.getContent());

		radioQ1.setText(question.getAltA());
		radioQ2.setText(question.getAltB());
		radioQ3.setText(question.getAltC());
		radioQ4.setText(question.getAltD());
		radioQ5.setText(question.getAltE());

		radioGroup.clearCheck();

		scrollQuestion.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
		scrollQuestion.fullScroll(View.FOCUS_UP);
	}
	
	private void highlightField(View view) {
		view.setBackgroundResource(R.drawable.field_state_invalid);

		Animation shake = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
		view.startAnimation(shake);
		
		view.refreshDrawableState();
	}

	@SuppressLint("NewApi")
	private void clearhighlightField(View view) {
		view.setBackgroundResource(R.drawable.btn_default_disabled_holo_light);
		view.refreshDrawableState();
	}
}
