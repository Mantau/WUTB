/**
 * 
 */
package br.udesc.wutb.activity.adapter;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.values.Cache;
import br.udesc.wutb.values.Config;

/**
 * @author mantau
 * 
 */
public class ListChatAdapter extends BaseAdapter {
	private List<String> chat = new LinkedList<String>();
	private LayoutInflater inflater;
	private ViewHolder holder;

	protected static class ViewHolder {
		private TextView userName;
		private TextView message;
	}

	public ListChatAdapter(Context context, List<String> chat) {
		this.chat = chat;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return chat.size();
	}

	@Override
	public Object getItem(int position) {
		return chat.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String msg[] = chat.get(position).split(Config.split);

		if (Cache.user.getName().equalsIgnoreCase(msg[0])) {
			convertView = inflater.inflate(R.layout.chat_item_me, null);
		} else {
			convertView = inflater.inflate(R.layout.chat_item_other, null);
		}

		holder = new ViewHolder();
		holder.userName = (TextView) convertView.findViewById(R.id.textViewChatItemUserName);
		holder.message = (TextView) convertView.findViewById(R.id.textViewChatItemContent);
		holder.userName.setText(msg[0]);
		holder.message.setText(msg[1]);
		
		convertView.setTag(holder);

		return convertView;
	}

}
