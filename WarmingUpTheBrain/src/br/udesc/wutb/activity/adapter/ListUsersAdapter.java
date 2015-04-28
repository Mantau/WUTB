/**
 * 
 */
package br.udesc.wutb.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.udesc.wutb.R;
import br.udesc.wutb.values.Config;
import br.udesc.wutb.values.StatusUser;
import br.udesc.wutb.values.User;

/**
 * @author mantau
 * 
 */
public class ListUsersAdapter extends BaseAdapter {
	private List<User> list;
	private LayoutInflater inflater;
	private ViewHolder holder;

	protected static class ViewHolder {
		//Images or Avatars
		private ImageView userImage;
		private ImageView imageView;
		private ImageView imageCorrect;
		private ImageView imageIncorrect;
		private ImageView imageDots;
		
		
		//Labels
		private TextView userName;
		private TextView userStatus;
		private TextView xxView;
		private TextView xxCorrect;
		private TextView xxIncorrect;
		private TextView xxDots;
	}

	public ListUsersAdapter(Context context, List<User> list) {
		this.list = list;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.user_list_item, null);

			loadHolder(convertView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		User u = list.get(position);
		if (u.getName().length() > 0) {
			holder.userName.setText(u.getName());
			//Update the content
			
			//TODO refactor
			holder.userStatus.setText("(" + u.getStatus() != null?"online":u.getStatus().toString() + ")");
			
			holder.xxView.setText("" + u.getxxView());
			holder.xxCorrect.setText("" + u.getxxCorrect());
			holder.xxIncorrect.setText("" + u.getxxIncorrect());
			holder.xxDots.setText("" + u.getxxDots());
			
			//Change icons or avatars
			holder.userImage.setBackgroundResource(getUserImage(u.getStatus()));
			holder.userStatus.setTextColor(getUserStatusColor(u.getStatus()));
			
			//disable all info
			holder.imageView.setVisibility(View.GONE);
			holder.imageCorrect.setVisibility(View.GONE);
			holder.imageIncorrect.setVisibility(View.GONE);
			holder.imageDots.setVisibility(View.GONE);
			holder.xxView.setVisibility(View.GONE);
			holder.xxCorrect.setVisibility(View.GONE);
			holder.xxIncorrect.setVisibility(View.GONE);
			holder.xxDots.setVisibility(View.GONE);
			
			//display only options selected on "config"
			if(Config.displayUserView){
				holder.imageView.setVisibility(View.VISIBLE);
				holder.xxView.setVisibility(View.VISIBLE);
			}
			if(Config.displayUserCorrect){
				holder.imageCorrect.setVisibility(View.VISIBLE);
				holder.xxCorrect.setVisibility(View.VISIBLE);
			}
			if(Config.displayUserIncorrect){
				holder.imageIncorrect.setVisibility(View.VISIBLE);
				holder.xxIncorrect.setVisibility(View.VISIBLE);
			}
			if(Config.displayUserDots){
				holder.imageDots.setVisibility(View.VISIBLE);
				holder.xxDots.setVisibility(View.VISIBLE);
			}
		}

		convertView.refreshDrawableState();
		
		return convertView;
	}

	private void loadHolder(View convertView) {
		holder = new ViewHolder();
		
		//load images or avatars
		holder.userImage      = (ImageView) convertView.findViewById(R.id.imageViewUserListRowImage);
		holder.imageView      = (ImageView) convertView.findViewById(R.id.imageViewUserListRowView);
		holder.imageCorrect   = (ImageView) convertView.findViewById(R.id.imageViewUserListRowCorrect);
		holder.imageIncorrect = (ImageView) convertView.findViewById(R.id.imageViewUserListRowIncorrect);
		holder.imageDots      = (ImageView) convertView.findViewById(R.id.imageViewUserListRowDots);
		
		//load labels
		holder.userName       = (TextView) convertView.findViewById(R.id.textViewUserListRowUsername);
		holder.userStatus     = (TextView) convertView.findViewById(R.id.textViewUserListRowUserStatus);
		holder.xxView         = (TextView) convertView.findViewById(R.id.textViewUserListRowView);
		holder.xxCorrect      = (TextView) convertView.findViewById(R.id.textViewUserListRowCorrect);
		holder.xxIncorrect    = (TextView) convertView.findViewById(R.id.textViewUserListRowIncorrect);
		holder.xxDots         = (TextView) convertView.findViewById(R.id.textViewUserListRowDots);
		
	}

	private int getUserImage(StatusUser state) {
		if(state==null){
			return R.drawable.user;
		}
		
		switch (state) {
		case ONLINE:
			return R.drawable.status_online;

		case OFFLINE:
			return R.drawable.status_disconnected;

		case ABSENT:
			return R.drawable.status_away;

		case WORK:
			return R.drawable.status_question;

		case CHAT:
			return R.drawable.status_chat;

		default:
			return R.drawable.user;
		}
	}
	
	private int getUserStatusColor(StatusUser state) {
		if(state==null){
			return R.color.Black;
		}
		
		switch (state) {
		case ONLINE:
			return R.color.Green;

		case OFFLINE:
			return R.color.Black;

		case ABSENT:
			return R.color.Orange;

		case WORK:
			return R.color.Red;

		case CHAT:
			return R.color.Blue;

		default:
			return R.color.Green;
		}
	}

}
