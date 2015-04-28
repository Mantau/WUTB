/**
 * 
 */
package br.udesc.wutb.activity;

import android.app.Activity;
import android.content.Intent;
import br.udesc.wutb.R;

/**
 * @author mantau
 * 
 */
public class AnimatedActivity extends Activity {
	private final String ENTER_ANIM_KEY = "ENTER_ANIM_KEY";
	private final String EXIT_ANIM_KEY = "EXIT_ANIM_KEY";

	public static final int SLIDE_FROM_LEFT             = 0;
	public static final int SLIDE_FROM_RIGHT            = 1;
	public static final int SLIDE_FROM_TOP              = 2;
	public static final int SLIDE_FROM_BOTTOM           = 4;
	public static final int SLIDE_FROM_BOTTOM_IOS_STYLE = 8;

	public void startAnimatedActivity(Intent intent) {
		startAnimatedActivityForResult(intent, -1, SLIDE_FROM_LEFT);
	}

	public void startAnimatedActivity(Intent intent, int anim) {
		startAnimatedActivityForResult(intent, -1, anim);
	}

	public void startAnimatedActivityForResult(Intent intent, int requestCode) {
		startAnimatedActivityForResult(intent, requestCode, SLIDE_FROM_LEFT);
	}

	public void startAnimatedActivityForResult(Intent intent, int requestCode, int anim) {
		setAnims(intent, anim);

		int enterAnim = intent.getIntExtra(ENTER_ANIM_KEY, 0);
		int exitAnim = intent.getIntExtra(EXIT_ANIM_KEY, 0);

		if (requestCode == -1) {
			startActivity(intent);
		} 
		else {
			startActivityForResult(intent, requestCode);
		}
		
		this.overridePendingTransition(enterAnim, exitAnim);
	}

	@Override
	public void finish() {
		Intent intent = this.getIntent();
		
		int enterAnim    = intent.getIntExtra(ENTER_ANIM_KEY, 0);
		int exitAnim     = intent.getIntExtra(EXIT_ANIM_KEY, 0);
		int newEnterAnim = getReverseAnim(exitAnim);
		int newExitAnim  = getReverseAnim(enterAnim);
		
		super.finish();
		this.overridePendingTransition(newEnterAnim, newExitAnim);
	}

	private void setAnims(Intent intent, int anim) {
		switch (anim) {
		case SLIDE_FROM_LEFT:
			intent.putExtra(ENTER_ANIM_KEY, R.anim.animated_activity_slide_left_in);
			intent.putExtra(EXIT_ANIM_KEY, R.anim.animated_activity_slide_right_out);
			break;
			
		case SLIDE_FROM_RIGHT:
			intent.putExtra(ENTER_ANIM_KEY, R.anim.animated_activity_slide_right_in);
			intent.putExtra(EXIT_ANIM_KEY, R.anim.animated_activity_slide_left_out);
			break;
			
		case SLIDE_FROM_TOP:
			intent.putExtra(ENTER_ANIM_KEY, R.anim.animated_activity_slide_top_in);
			intent.putExtra(EXIT_ANIM_KEY, R.anim.animated_activity_slide_bottom_out);
			break;
			
		case SLIDE_FROM_BOTTOM:
			intent.putExtra(ENTER_ANIM_KEY, R.anim.animated_activity_slide_bottom_in);
			intent.putExtra(EXIT_ANIM_KEY, R.anim.animated_activity_slide_top_out);
			break;
			
		case SLIDE_FROM_BOTTOM_IOS_STYLE:
			intent.putExtra(ENTER_ANIM_KEY, R.anim.animated_activity_slide_bottom_in);
			intent.putExtra(EXIT_ANIM_KEY, R.anim.animated_activity_slide_none);
			break;
		}

	}

	private int getReverseAnim(int animation) {
		int reverseAnimation = 0;

		if (animation == R.anim.animated_activity_slide_top_in) {
			reverseAnimation = R.anim.animated_activity_slide_top_out;
		} 
		else if (animation == R.anim.animated_activity_slide_bottom_in) {
			reverseAnimation = R.anim.animated_activity_slide_bottom_out;
		} 
		else if (animation == R.anim.animated_activity_slide_right_in) {
			reverseAnimation = R.anim.animated_activity_slide_right_out;
		} 
		else if (animation == R.anim.animated_activity_slide_left_in) {
			reverseAnimation = R.anim.animated_activity_slide_left_out;
		} 
		else if (animation == R.anim.animated_activity_slide_top_out) {
			reverseAnimation = R.anim.animated_activity_slide_top_in;
		} 
		else if (animation == R.anim.animated_activity_slide_right_out) {
			reverseAnimation = R.anim.animated_activity_slide_right_in;
		} 
		else if (animation == R.anim.animated_activity_slide_bottom_out) {
			reverseAnimation = R.anim.animated_activity_slide_bottom_in;
		} 
		else if (animation == R.anim.animated_activity_slide_left_out) {
			reverseAnimation = R.anim.animated_activity_slide_left_in;
		} 
		else if (animation == R.anim.animated_activity_slide_none) {
			reverseAnimation = R.anim.animated_activity_slide_none;
		}

		return reverseAnimation;
	}

}