package org.exposeproject.models;

import java.io.Serializable;
import java.util.Objects;

public class Authentication implements Serializable {

	private Long id;
	private String ip;
	private int number;

	public Authentication(Long id, String ip, int number) {
		super();
		this.id = id;
		this.ip = ip;
		this.number = number;
	}

	public Authentication(String ip, int number) {
		super();
		this.ip = ip;
		this.number = number;
	}

	public Authentication() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ip, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authentication other = (Authentication) obj;
		return Objects.equals(id, other.id) && Objects.equals(ip, other.ip) && number == other.number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
