package com.gabalise.classes;

/**
 * @author Charles Garrocho
 * @author Thiago Garrocho
 *
 */
public class Usuario {
	private int UserId;
	private String UserName, Reason;
	
	public Usuario() {
		super();
	}

	public int getUserId() {
		return UserId;
	}
	
	public void setUserId(int userId) {
		UserId = userId;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getReason() {
		return Reason;
	}
	
	public void setReason(String reason) {
		Reason = reason;
	}

	@Override
	public String toString() {
		return "Usuario [UserId=" + UserId + ", UserName=" + UserName
				+ ", Reason=" + Reason + "]";
	}
}
