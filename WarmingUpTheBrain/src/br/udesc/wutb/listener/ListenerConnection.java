/**
 * 
 */
package br.udesc.wutb.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityConnection;
import br.udesc.wutb.activity.ActivitySplash;
import br.udesc.wutb.activity.AnimatedActivity;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public class ListenerConnection {
	private ActivityConnection activity = null;

	public ListenerConnection(ActivityConnection activity) {
		this.activity = activity;
	}

	public void addListeners() {
		Button connection = (Button) activity.findViewById(R.id.buttonTryConnection);
		connection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String ip = null;

				EditText editIp = (EditText) activity.findViewById(R.id.editTextAddressServer);

				ip = (String) editIp.getText().toString();

				if (verifyInputs(ip)) {
					Config.address = ip;
					openActivitySplash();
				} else {
					highlightField(editIp);
				}
			}
		});

	}

	private void openActivitySplash() {
		Intent intent = new Intent(activity.getBaseContext(),ActivitySplash.class);
		Params.getActivityConnection().startAnimatedActivity(intent,AnimatedActivity.SLIDE_FROM_LEFT);
		Params.getActivityConnection().finish();
	}

	/**
	 * @exception: Verifies that the data was properly informed by user
	 * @return: <true> if values are corrects. <false> if failure
	 */
	private boolean verifyInputs(String ip) {
		if (ip == null || ip.isEmpty())
			return false;
		ip = ip.trim();
		if ((ip.length() < 6) & (ip.length() > 15))
			return false;

		try {
			Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
			Matcher matcher = pattern.matcher(ip);
			return matcher.matches();
		} catch (PatternSyntaxException ex) {
			return false;
		}
	}

	private void highlightField(View view) {
		view.setBackgroundResource(R.drawable.field_state_invalid);
		Animation shake = AnimationUtils.loadAnimation(view.getContext(),R.anim.shake);
		
		view.startAnimation(shake);
		view.refreshDrawableState();
	}
}
