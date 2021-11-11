package org.exposeproject.models;

public class Message {
	
	private static Long cmp = 0l;
	private Long id;
	private String message;
	private Long idUser;

	public Message(String message, Long idUser) {
		super();
		this.message = message;
		this.idUser = idUser;
		id = cmp++;
	}

	public Message() {

	}

	public Message(String message) {
		super();
		this.message = message;
	}

	public Message(Long id, String message, Long idUser) {
		this(message,idUser);
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

}
