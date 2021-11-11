package org.exposeproject.models;

public class User {

	private Long id = null;
	private String username;
	private String password;
	private byte[] image;

	public User() {

	}

	public User(Long id, String username, String password, byte[] image) {
		this(username,password,image);
		this.id = id;
	}

	public User(String username, String password, byte[] image) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
