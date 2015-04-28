/**
 * 
 */
package br.udesc.wutb.listener;

import java.util.ArrayList;

import android.widget.ListView;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.activity.adapter.ListUsersAdapter;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Params;
import br.udesc.wutb.values.User;

/**
 * @author mantau
 * 
 */
public class ListenerUserList {
	
	// UI Elements
	private static ListView userListMyGroup;
	private static ListView userListOtherGroup;

	public ListenerUserList(ActivityGame activity) {
		// Load layout
		userListMyGroup          = (ListView) activity.findViewById(R.id.listViewMyGroup);
		userListOtherGroup       = (ListView) activity.findViewById(R.id.listViewOtherGroup);

		// Set the adapters
		userListMyGroup.setAdapter(new ListUsersAdapter(activity, new ArrayList<User>(Cache.myGroup)));
		userListOtherGroup.setAdapter(new ListUsersAdapter(activity, new ArrayList<User>(Cache.otherGroup)));
	}

	public void addListeners() {
		// TODO
		/*
		 * sendMessage.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { submitMessage();
		 * 
		 * } });
		 */
	}

	public void refresh() {
		// Force to reload all content
		userListMyGroup.setAdapter(new ListUsersAdapter(Params.getActivityGame(), new ArrayList<User>(Cache.myGroup)));
		userListOtherGroup.setAdapter(new ListUsersAdapter(Params.getActivityGame(), new ArrayList<User>(Cache.otherGroup)));
	}
}
