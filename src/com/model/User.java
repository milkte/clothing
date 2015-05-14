package com.model;

import java.util.Objects;

/**
 * Created by SRawla on 4/11/2015.
 */
public class User {

	protected int userId;
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected boolean loggedin;
	protected Address address;
	protected String email;
	protected String phoneNo;
	protected String role;
	

	public User() {
	}

	public User(int userID, String userName, String password) {
		this.userId = userID;
		this.userName = userName;
		this.password = password;
	}

	public User(int userID, String userName, String firstName, String lastName,
			boolean loggedin) {
		this.userId = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loggedin = loggedin;
	}

	public User(int userId, String userName, String password, String firstName,
			String lastName, boolean loggedin, Address address, String email,
			String phoneNo, String role) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loggedin = loggedin;
		this.address = address;
		this.email = email;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	/**
	 *
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 *
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the loggedin
	 */
	public boolean isLoggedin() {
		return loggedin;
	}

	/**
	 * @param loggedin
	 *            the loggedin to set
	 */
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 29 * hash + this.getUserId();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.getUserId() != other.getUserId()) {
			return false;
		}
		return Objects.equals(this.getUserName(), other.getUserName());
	}

	@Override
	public String toString() {
		return "User{" + "userID=" + getUserId() + ", userName="
				+ getUserName() + ", firstName=" + getFirstName()
				+ ", lastName=" + getLastName() + ", loggedin=" + isLoggedin()
				+ ", address=" + getAddress() + '}';
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
