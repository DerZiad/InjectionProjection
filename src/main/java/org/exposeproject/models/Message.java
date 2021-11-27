package org.exposeproject.models;

public class Message {

	private Long id;
	private String message;
	private Long idUser;
	private User user;
	private String SujetMessage;
	
	public String getSujetMessage() {
		return SujetMessage;
	}

	public void setSujetMessage(String sujetMessage) {
		SujetMessage = sujetMessage;
	}

	public Message(String message, Long idUser) {
		super();
		this.message = message;
		this.idUser = idUser;
	}

	public Message() {

	}

	public Message(String message) {
		super();
		this.message = message;
	}

	public Message(Long id, String message, Long idUser) {
		this(message, idUser);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", idUser=" + idUser + ", user=" + user
				+ ", SujetMessage=" + SujetMessage + "]";
	}
	
}
