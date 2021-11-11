package org.exposeproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id = null;
	private String username;
	private String password;
	private byte[] image;

	public User(String username, String password, byte[] image) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
	}

}
