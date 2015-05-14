/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author shruti
 */
public class Seller extends User {

	private String company;
	private String accno;
	private String routingno;
	private boolean isLocked;
	private boolean isClosed;
	
	public Seller() {
	}

	/**
	 * @param userID
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param loggedin
	 */
	public Seller(int userID, String userName, String firstName,
			String lastName, boolean loggedin) {
		super(userID, userName, firstName, lastName, loggedin);
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param loggedin
	 * @param address
	 * @param email
	 * @param phoneNo
	 * @param role
	 */
	public Seller(int userId, String userName, String password,
			String firstName, String lastName, boolean loggedin,
			Address address, String email, String phoneNo, String role,
			String company, String accountno, String rountingno,boolean isLocked,boolean isClosed) {
		super(userId, userName, password, firstName, lastName, loggedin,
				address, email, phoneNo, role);
		this.company = company;
		this.accno = accountno;
		this.routingno = rountingno;
		this.isLocked=isLocked;
		this.isClosed = isClosed;
	}

	/**
	 * @param userID
	 * @param userName
	 * @param password
	 */
	public Seller(int userID, String userName, String password) {
		super(userID, userName, password);
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the accno
	 */
	public String getAccno() {
		return accno;
	}

	/**
	 * @param accno
	 *            the accno to set
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * @return the routingno
	 */
	public String getRoutingno() {
		return routingno;
	}

	/**
	 * @param routingno
	 *            the routingno to set
	 */
	public void setRoutingno(String routingno) {
		this.routingno = routingno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accno == null) ? 0 : accno.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((routingno == null) ? 0 : routingno.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Seller)) {
			return false;
		}
		Seller other = (Seller) obj;
		if (accno == null) {
			if (other.accno != null) {
				return false;
			}
		} else if (!accno.equals(other.accno)) {
			return false;
		}
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (routingno == null) {
			if (other.routingno != null) {
				return false;
			}
		} else if (!routingno.equals(other.routingno)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seller [company=");
		builder.append(company);
		builder.append(", accno=");
		builder.append(accno);
		builder.append(", routingno=");
		builder.append(routingno);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", loggedin=");
		builder.append(loggedin);
		builder.append(", address=");
		builder.append(address);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * @return the isClosed
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * @param isClosed the isClosed to set
	 */
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

}
