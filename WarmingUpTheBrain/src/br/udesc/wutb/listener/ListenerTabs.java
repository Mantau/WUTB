package br.udesc.wutb.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import br.udesc.wutb.R;
import br.udesc.wutb.activity.ActivityGame;
import br.udesc.wutb.values.Cache;

public class ListenerTabs {
	private RelativeLayout tabBoard;
	private RelativeLayout tabQuestion;
	private RelativeLayout tabUserList;
	private RelativeLayout tabChat;
	private RelativeLayout tabTerminal;
	private RelativeLayout tabConfig;

	private LinearLayout   board;
	private RelativeLayout question;
	private LinearLayout   users;
	private RelativeLayout chatContent;
	private RelativeLayout terminal;
	private RelativeLayout config;
	
	private ListView       chat;
	private ListView       userListMyGroup;
	private ListView       userListOtherGroup;
	
	
	public ListenerTabs(ActivityGame activity){
		//Load the Tabs Layout
		tabBoard    = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameBoard);
		tabQuestion = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameQuestion);
		tabUserList = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameUserlist);
		tabChat     = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameChat);
		tabTerminal = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameTerminal);
		tabConfig   = (RelativeLayout) activity.findViewById(R.id.relativeLayoutHeaderGameConfig);
		
		//Load the Content Layout
		board       = (LinearLayout) activity.findViewById(R.id.linearLayoutGame);
		question    = (RelativeLayout) activity.findViewById(R.id.relativeLayoutQuestion);
		users       = (LinearLayout) activity.findViewById(R.id.linearLayoutGameUserList);
		chatContent = (RelativeLayout) activity.findViewById(R.id.relativeLayoutChat);
		terminal    = (RelativeLayout) activity.findViewById(R.id.relativeLayoutGameTerminal);
		config      = (RelativeLayout) activity.findViewById(R.id.relativeLayoutGameConfig);
		
		//Lists
		chat                  = (ListView) activity.findViewById(R.id.listChat);
		userListMyGroup       = (ListView) activity.findViewById(R.id.listViewMyGroup);
		userListOtherGroup    = (ListView) activity.findViewById(R.id.listViewOtherGroup);
	}
	
	public void addListeners() {
		tabBoard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeContentToBoard();

			}
		});
		
		tabQuestion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Cache.getCurrentQuestion() != null)
				changeContentToQuestion();
			}
		});
		
		tabUserList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeContentToUserlist();

			}
		});
		
		tabChat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeContentToChat();

			}
		});
		
		tabTerminal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeContentToTerminal();

			}
		});
		
		tabConfig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeContentToConfig();

			}
		});
	}

	private void disableContent(){
		try{
			//Change the state of the Tab
			tabBoard.setBackgroundResource(R.drawable.tab_noselected);
			tabQuestion.setBackgroundResource(R.drawable.tab_noselected);
			tabUserList.setBackgroundResource(R.drawable.tab_noselected);
			tabChat.setBackgroundResource(R.drawable.tab_noselected);
			tabTerminal.setBackgroundResource(R.drawable.tab_noselected);
			tabConfig.setBackgroundResource(R.drawable.tab_noselected);
		
			//Set the invisible content
			board.setVisibility(View.GONE);
			question.setVisibility(View.GONE);
			users.setVisibility(View.GONE);
			chatContent.setVisibility(View.GONE);
			terminal.setVisibility(View.GONE);
			config.setVisibility(View.GONE);
		}catch(Exception e){
			//TODO Do nothing
		}
	}
	
	public void changeContentToBoard() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabBoard.setBackgroundResource(R.drawable.tab_selected);		
		board.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void changeContentToQuestion() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabQuestion.setBackgroundResource(R.drawable.tab_selected);		
		question.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void changeContentToUserlist() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabUserList.setBackgroundResource(R.drawable.tab_selected);		
		users.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void changeContentToChat() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabChat.setBackgroundResource(R.drawable.tab_selected);		
		chatContent.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void changeContentToTerminal() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabTerminal.setBackgroundResource(R.drawable.tab_selected);		
		terminal.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void changeContentToConfig() {
		//disable the current content
		disableContent();
		
		//Display option selected
		tabConfig.setBackgroundResource(R.drawable.tab_selected);		
		config.setVisibility(View.VISIBLE);
		refresh();
	}
	
	public void refresh() {
		try {
			//layouts
			board.refreshDrawableState();
			question.refreshDrawableState();
			users.refreshDrawableState();
			terminal.refreshDrawableState();
			config.refreshDrawableState();
			
			//Views
			chat.invalidateViews();
			userListMyGroup.invalidateViews();
			userListOtherGroup.invalidateViews();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
