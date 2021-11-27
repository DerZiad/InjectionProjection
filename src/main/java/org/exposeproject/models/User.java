package org.exposeproject.models;

import java.util.Date;

public class User {

	private Long id;
	private String username;
	private String password;
	private String image;
	private String bin;
	public String getNomBanque() {
		return nomBanque;
	}

	public void setNomBanque(String nomBanque) {
		this.nomBanque = nomBanque;
	}

	private Integer cvv;
	private String date;
	private String nomBanque ;
	
	public User() {

	}

	public User(Long id, String username, String password, String image, String bin, Integer cvv, String date) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.image = image;
		this.bin = bin;
		this.cvv = cvv;
		this.date = date;
	}

	public User(String username, String password, String image) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
	}

	public User(String username, String password, String image, String bin, Integer cvv, String date) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
		this.bin = bin;
		this.cvv = cvv;
		this.date = date;
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

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
