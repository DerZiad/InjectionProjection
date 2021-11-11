package org.exposeproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

	private Long id;
	private String message;
	private Long idUser;

	public Message(String message, Long idUser) {
		super();
		this.message = message;
		this.idUser = idUser;
		User user = new User();
	}

}
