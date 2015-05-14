/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Address;

/**
 *
 * @author shruti
 */
public class AddressDao {

	public boolean addAddress(String street, String city, String state,
			String pincode) {
		boolean flag = true;
		Statement statement = null;
		try {

			String sql = "INSERT INTO `clothing`.`address` (`street`,`state`,`city`,`zipcode`) VALUES ('"
					+ street
					+ "','"
					+ city
					+ "','"
					+ state
					+ "',"
					+ pincode
					+ ");";
			statement = SqlUtil.getConnection().createStatement();

			statement.executeUpdate(sql);
			ResultSet generatedKeys = statement.getGeneratedKeys();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			flag = false;
		} finally {
			if (null != statement) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public boolean updateAddress(Address address) {
		boolean flag = true;
		PreparedStatement statement = null;
		try {

			String sql = "UPDATE address SET address_line1 = ?,address_line2 = ?,street = ?,city =?,state = ?,country = ?,zipcode = ? WHERE address_id =?;";

			statement = SqlUtil.getConnection().prepareStatement(sql);
			statement.setString(1, address.getAddressLine1());
			statement.setString(2, address.getAddressLine2());
			statement.setString(3, address.getStreet());
			statement.setString(4, address.getCity());
			statement.setString(5, address.getState());
			statement.setString(6, address.getCountry());
			statement.setInt(7, address.getZipcode());
			statement.setInt(8, address.getAddressId());

			statement.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			flag = false;
		} finally {
			if (null != statement) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static Address getAddressForUser(String id) {
		Address address = null;
		Statement statement = null;
		try {

			String sql = "SELECT * FROM address as a, usermaster as u where u.address_id=a.address_id and u.userid="
					+ id + ";";
			statement = SqlUtil.getConnection().createStatement();

			ResultSet executeQuery = statement.executeQuery(sql);
			if (executeQuery != null && executeQuery.next()) {
				address = new Address(executeQuery.getInt(1),
						executeQuery.getString(2), executeQuery.getString(3),
						executeQuery.getString(4), executeQuery.getString(5),
						executeQuery.getString(6), executeQuery.getString(7),
						executeQuery.getInt(8));
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			if (null != statement) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return address;
	}

}
