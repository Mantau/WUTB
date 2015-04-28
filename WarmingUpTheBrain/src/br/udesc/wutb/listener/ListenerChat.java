/**
 * 
 */
package br.udesc.wutb.listener;

import java.util.Date;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.activity.adapter.ListChatAdapter;
import br.udesc.wutb.log.Message;
import br.udesc.wutb.log.TypeMessage;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.Params;

/**
 * @author mantau
 * 
 */
public class ListenerChat {
	// UI Elements
	private Button sendMessage;
	private EditText chatInput;
	private static ListView listChat;

	public ListenerChat(ActivityGame activity) {
		sendMessage = (Button) activity.findViewById(R.id.buttonSendMessage);
		chatInput   = (EditText) activity.findViewById(R.id.editTextChatImput);
		listChat    = (ListView) activity.findViewById(R.id.listChat);

		listChat.setAdapter(new ListChatAdapter(activity, Cache.chat));
		
		refresh();
	}
	
	public void addListeners() {
		sendMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				submitMessage();

			}
		});
	}

	public void refresh() {
		// Force to reload all content
		listChat.setAdapter(new ListChatAdapter(Params.getActivityGame(), Cache.chat));
	}

	protected void submitMessage() {
		String str = chatInput.getText().toString();

		if (str.length() > 0) {
			String message = Cache.user.getName() + Config.split + str;
			Cache.chat.addLast(message);
			listChat.invalidateViews();

			// Scoll chat to end
			listChat.smoothScrollToPosition(Cache.chat.size() - 1);

			@SuppressWarnings("deprecation")
			Message m = new Message(TypeMessage.CHAT, CCSMessageType.NULL,	new Date().toGMTString(), Cache.user.getName(), str);
			Cache.logOut.addLast(m);
		} else {
			Animation shake = AnimationUtils.loadAnimation(chatInput.getContext(), R.anim.shake);
			chatInput.startAnimation(shake);
		}
		chatInput.setText("");
	}

}
