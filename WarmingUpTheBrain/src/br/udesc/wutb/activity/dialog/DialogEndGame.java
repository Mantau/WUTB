/**
 * 
 */
package br.udesc.wutb.activity.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import br.udesc.wutb.core.GameManager;
import br.udesc.wutb.values.Cache;

/**
 * @author mantau
 * 
 */
public class DialogEndGame {

	public boolean showDefaultDialog(final Activity activity) {
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setMessage("End of game!\n" +
						 "Score Team A: " + Cache.questionsDotsGA + "\n" +
						 "Score Team B: " + Cache.questionsDotsGB + "\n" +
						 "Click [OK] to exit.");
		alert.setCancelable(false);

		alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				GameManager.finish();
			}
		});

		AlertDialog dialog = alert.create();
		dialog.show();
		return true;
	}

}
