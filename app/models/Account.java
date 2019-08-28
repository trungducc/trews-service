package models;

import controllers.account.SignUpParameters;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;

@Entity
public class Account extends AbstractIdentifiable {

	private String username;

	private String hashedPassword;

	public Account() {
	}

	public Account(String username, String hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
	}

	public Account(SignUpParameters parameters) {
		this.username = parameters.getUsername();

		String password = parameters.getPassword();
		this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
}
