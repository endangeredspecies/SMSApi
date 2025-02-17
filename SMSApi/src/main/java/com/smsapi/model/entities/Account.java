package com.smsapi.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name="auth_id")
	private String authId;
	private String username;
	
	public Account() {}
	
	
	public Account(int id, String authId, String username) {
		super();
		this.id = id;
		this.authId = authId;
		this.username = username;
	}


	public Account(Account account) {
		this.id=account.getId();
		this.authId=account.getAuthId();
		this.username=account.getUsername();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
