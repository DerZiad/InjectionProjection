package org.exposeproject.models;

public class MessageUser {
	private String usrname ;
	private String nomBanque ;
	private double bin ;
	private int cvv ;
	private String dataExp;
	private String message;
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getNomBanque() {
		return nomBanque;
	}
	public void setNomBanque(String nomBanque) {
		this.nomBanque = nomBanque;
	}
	
	public double getBin() {
		return bin;
	}
	public void setBin(double bin) {
		this.bin = bin;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getDataExp() {
		return dataExp;
	}
	public void setDataExp(String dataExp) {
		this.dataExp = dataExp;
	}
	
	public MessageUser(String usrname, String nomBanque, double bin, int cvv, String dataExp) {
		super();
		this.usrname = usrname;
		this.nomBanque = nomBanque;
		this.bin = bin;
		this.cvv = cvv;
		this.dataExp = dataExp;
	}
	@Override
	public String toString() {
		return "MessageUser [usrname=" + usrname + ", nomBanque=" + nomBanque + ", bin=" + bin + ", cvv=" + cvv
				+ ", dataExp=" + dataExp + "]";
	}
	public MessageUser() {
		super();
	}
	public MessageUser(String usrname, String nomBanque, double bin, int cvv, String dataExp, String message) {
		super();
		this.usrname = usrname;
		this.nomBanque = nomBanque;
		this.bin = bin;
		this.cvv = cvv;
		this.dataExp = dataExp;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
