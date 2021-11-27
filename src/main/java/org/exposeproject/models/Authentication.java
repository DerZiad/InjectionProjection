package org.exposeproject.models;

import java.io.Serializable;
import java.util.Objects;

public class Authentication implements Serializable {

	private String ip;
	private int number;
	private static final long DELAI = 60 * 1000;
	private long start = System.currentTimeMillis();

	public Authentication(String ip, int number, long start) {
		super();
		this.ip = ip;
		this.number = number;
		this.start = start;
	}

	public Authentication(int number, long start) {
		super();
		this.number = number;
		this.start = start;
	}

	public Authentication() {

	}

	public boolean isDead() {
		long dateActuelEnMillis = System.currentTimeMillis();
		return dateActuelEnMillis > (start + DELAI);
	}
	
	public void reinitialize() {
		this.start = System.currentTimeMillis();
	}

	@Override
	public int hashCode() {
		return Objects.hash(ip, number);
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
		return Objects.equals(ip, other.ip) && number == other.number;
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

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

}
