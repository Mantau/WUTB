/**
 * 
 */
package br.udesc.wutb.log;

import br.udesc.ccsmocw.values.CCSMessageType;
import br.udesc.wutb.values.Config;

/**
 * @author mantau
 * 
 */
public class Message {

	private TypeMessage type;
	private CCSMessageType tag;
	private String time;
	private String user;
	private String content;

	public Message(TypeMessage type, CCSMessageType tag, String time, String user,
			String content) {
		super();
		this.type = type;
		this.tag = tag;
		this.time = time;
		this.user = user;
		this.content = content;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CCSMessageType getTag() {
		return tag;
	}

	public void setTag(CCSMessageType tag) {
		this.tag = tag;
	}

	public String toTerminalString() {
		return ">" + user + "@" + content + "\n";
	}

	public String messageToStr() {
		String split = Config.split;
		return "" + type + split + tag + split + time + split + user
				+ split + content;
	}

	public static Message strToMessage(String str) {
		String s[] = str.split(Config.split);
		TypeMessage type = TypeMessage.valueOf(s[0]);
		CCSMessageType tag = CCSMessageType.valueOf(s[1]);
		
		return new Message(type, tag, s[2], s[3], s[4]);
	}

	@Override
	public String toString() {
		return toTerminalString();
	}

}
