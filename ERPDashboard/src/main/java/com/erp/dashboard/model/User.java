package com.erp.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 43454525254L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "user_seq")
	
	@Column(name="USER_ID")
    private int userId;  
	
	@Column(name="USER_NAME")
    private String userName;
	
	@Column(name="USER_PASSWORD")
    private String userPassword;
	
	public User() {};
	
	public User(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}