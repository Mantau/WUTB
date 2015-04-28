/**
 * 
 */
package br.udesc.wutb.values;

/**
 * @author mantau
 * 
 */
public class User {
	private String name;
	private String email;
	private StatusUser status;
	
	private int xxView = 0;
	private int xxCorrect = 0;
	private int xxIncorrect = 0;
	private int xxDots = 0;

	public User(String name, String email, StatusUser status) {
		super();
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusUser getStatus() {
		return status;
	}

	public void setStatus(StatusUser status) {
		this.status = status;
	}

	
	
	public int getxxView() {
		return xxView;
	}

	public void setxxView(int xxView) {
		this.xxView = xxView;
	}

	public int getxxCorrect() {
		return xxCorrect;
	}

	public void setxxCorrect(int xxCorrect) {
		this.xxCorrect = xxCorrect;
	}

	public int getxxIncorrect() {
		return xxIncorrect;
	}

	public void setxxIncorrect(int xxIncorrect) {
		this.xxIncorrect = xxIncorrect;
	}

	public int getxxDots() {
		return xxDots;
	}

	public void setxxDots(int xxDots) {
		this.xxDots = xxDots;
	}
	
	public void incxxView() {
		this.xxView++;
	}
	
	public void incxxCorrect() {
		this.xxCorrect++;
	}
	
	public void incxxIncorrect() {
		this.xxIncorrect++;
	}
	
	public void incxxDots(int points) {
		this.xxDots += points;
	}
	
	public void decxxDots() {
		this.xxDots --;		
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", status="
				+ status + ", wiew=" + xxView + ", correct=" 
				+ xxCorrect + ", incorrect=" + xxIncorrect + ", xxDots=" 
				+ xxDots +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}

}
