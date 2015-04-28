/**
 * 
 */
package br.udesc.wutb.listener;

import android.app.Activity;
import android.view.MenuItem;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.dialog.DialogConfirmExit;

/**
 * @author mantau
 * 
 */
public abstract class ListenerMenu {

	public static boolean addOptionsMenu(Activity activity, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_help:
			return openMenuHelp(activity);

		case R.id.menu_settings:
			return openMenuSettings(activity);

		case R.id.menu_close:
			openMenuClose(activity);

		default:
			return doDefaultAction(activity);
		}
	}

	public static boolean doDefaultAction(Activity activity) {
		return false;
	}

	public static void openMenuClose(Activity activity) {
		new DialogConfirmExit().showDefaultDialog(activity);

	}

	public static boolean openMenuSettings(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean openMenuHelp(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}
}
