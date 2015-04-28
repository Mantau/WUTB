/**
 * 
 */
package br.udesc.wutb.activity.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import br.udesc.wutb.core.GameManager;

/**
 * @author mantau
 * 
 */
public class DialogConfirmExit {

	public boolean showDefaultDialog(final Activity activity) {
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setMessage("Are you sure you want to exit?");
		alert.setCancelable(false);

		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				GameManager.finish();
			}
		});
		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				//TODO action...
			}

		});

		AlertDialog dialog = alert.create();
		dialog.show();
		return true;
	}

}
