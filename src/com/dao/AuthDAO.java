/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.model.Address;
import com.model.Seller;
import com.model.User;
import com.mysql.jdbc.MySQLConnection;

public class AuthDAO {

	static Connection con;
	static String url;

	public static final String USER_ROLE = "USER";
	public static final String SELLER_ROLE = "SELLER";
	public static final String ADMIN = "ADMIN";

	/**
	 * Create DB connection
	 *
	 * @return
	 */

	public static int checkUserPass(String userName, String password) {
		PreparedStatement ps = null;
		try {
			ResultSet result = null;
			try {
				String sql = "SELECT userid FROM usermaster WHERE username LIKE ? and password LIKE ?";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setString(1, userName);
				ps.setString(2, password);
				result = ps.executeQuery();
			} catch (SQLException ex) {

			}
			if (result.next()) {
				return result.getInt(1);

			}
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);

		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public static User getUserByUserNamePassword(String userName,
			String password) {
		User user = null;
		PreparedStatement ps = null;
		try {
			ResultSet result = null;
			user = getUserByUserName(userName);
			String sql = "SELECT u.userid,u.firstname,u.lastname,u.email,u.phoneno,u.role ,u.companyName,u.accountNumber,u.routingNumber,u.isLocked,u.isClosed,a.address_id,a.address_line1,a.address_line2,a.street,a.city,a.state,a.country,a.zipcode  FROM usermaster as u, address as a  where u.username= ? and u.password=? and u.address_id=a.address_id";

			if (null != user)
				if (ADMIN.equals(user.getRole())) {
					sql = "SELECT u.userid,u.firstname,u.lastname,u.email,u.phoneno,u.role ,u.companyName,u.accountNumber,u.routingNumber,u.isLocked,u.isClosed,a.address_id,a.address_line1,a.address_line2,a.street,a.city,a.state,a.country,a.zipcode  FROM usermaster as u, address as a  where u.username= ? and u.password=?";

				}
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			result = ps.executeQuery();
			if (result != null && result.next()) {
				if (SELLER_ROLE.equals(result.getString(6))) {

					Address address = new Address(result.getInt(12),
							result.getString(13), result.getString(14),
							result.getString(15), result.getString(16),
							result.getString(17), result.getString(18),
							result.getInt(19));
					user = new Seller(result.getInt(1), userName, password,
							result.getString(2), result.getString(3), true,
							address, result.getString(4), result.getString(5),
							SELLER_ROLE, result.getString(7),
							String.valueOf(result.getInt(8)),
							String.valueOf(result.getInt(9)),
							result.getBoolean(10), result.getBoolean(11));
				} else {
					Address address = null;
					if (!ADMIN.equals(result.getString(6))) {
						address = new Address(result.getInt(12),
								result.getString(13), result.getString(14),
								result.getString(15), result.getString(16),
								result.getString(17), result.getString(18),
								result.getInt(19));
					}
					user = new User(result.getInt(1), userName, password,
							result.getString(2), result.getString(3), true,
							address, result.getString(4), result.getString(5),
							result.getString(6));
				}

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}

	public static User getUserByUserName(String userName) {
		User user = null;
		try {
			ResultSet result = null;

			PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
					(MySQLConnection) SqlUtil.getConnection(), url);
			try {
				String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role` FROM `clothing`.`usermaster` where `usermaster`.`username`= ? ;";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setString(1, userName);

				result = ps.executeQuery();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			try {
				if (result != null && result.next()) {
					user = new User(result.getInt(1), result.getString(2),
							null, result.getString(4), result.getString(5),
							true, null, result.getString(6),
							result.getString(7), result.getString(8));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}

		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);

		}
		return user;
	}

	public static User getUserById(int userId) {
		try {
			ResultSet result = null;

			PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
					(MySQLConnection) SqlUtil.getConnection(), url);
			try {
				String sql = "SELECT userid,username,password,firstname,lastname FROM usermaster WHERE userid LIKE ?";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setInt(1, userId);
				result = ps.executeQuery();
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}

			try {
				if (result.next()) {
					try {
						User user = new User(result.getInt(1),
								result.getString(2), result.getString(3));
						user.setFirstName(result.getString(4));
						user.setLastName(result.getString(5));
						return user;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);

		}
		return null;
	}

	public static Seller getSellerById(int userId) {
		try {
			ResultSet result = null;

			PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
					(MySQLConnection) SqlUtil.getConnection(), url);
			try {
				String sql = "SELECT userid,username,password,firstname,companyName FROM usermaster WHERE userid LIKE ?";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setInt(1, userId);
				result = ps.executeQuery();
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}

			try {
				if (result.next()) {
					try {
						Seller seller = new Seller();
						seller.setUserId(result.getInt(1));
						seller.setUserName(result.getString(2));
						seller.setPassword(result.getString(3));
						seller.setCompany(result.getString(4));
						return seller;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);

		}
		return null;
	}

	public static int enterNewUser(String userName, String password) {
		int flag = 1;
		try {
			PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
					(MySQLConnection) SqlUtil.getConnection(), url);
			ResultSet result = null;

			try {
				String sql = "insert into `clothing`.`usermaster`(`usermaster`.`username`,`usermaster`.`password`) values (?,?)";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.executeUpdate();
				System.out.println("result is " + result);
			} catch (SQLException ex) {
				ex.printStackTrace();
				flag = 0;
			}

			return flag;
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);
			flag = 0;
		}
		return flag;
	}

	public static int updateUser(User user) {
		int flag = 1;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			String sql = "UPDATE usermaster SET password = ?,firstname = ?,lastname = ?,email = ?,phoneno = ?,companyName = ?,routingNumber = ?,accountNumber = ?,isLocked = ?,isClosed = ? WHERE userid = ?;";

			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhoneNo());
			if (SELLER_ROLE.equals(user.getRole())) {
				Seller seller = (Seller) user;
				ps.setString(6, seller.getCompany());
				ps.setLong(7, Long.parseLong(seller.getAccno()));
				ps.setInt(8, Integer.parseInt(seller.getRoutingno()));
				ps.setBoolean(9, seller.isLocked());
				ps.setBoolean(10, seller.isClosed());
			} else {
				ps.setString(6, "");
				ps.setLong(7, 0);
				ps.setInt(8, 0);
				ps.setBoolean(9, false);
				ps.setBoolean(10, false);
			}
			ps.setInt(11, user.getUserId());
			ps.executeUpdate();
			System.out.println("result is " + result);
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	public static int updateSellerStatus(User user) {
		int flag = 1;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			String sql = "UPDATE  usermaster SET isLocked = ? WHERE userid = ?;";

			ps = SqlUtil.getConnection().prepareStatement(sql);
		
			Seller seller = (Seller) user;
			ps.setBoolean(1, seller.isLocked());
			ps.setInt(2, user.getUserId());
			ps.executeUpdate();
			System.out.println("result is " + result);
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = 0;
		}
		return flag;
	}

	public static int updateRole(String userid, String role) {
		int flag = 1;
		ResultSet result = null;

		try {
			String sql = "UPDATE `clothing`.`usermaster` SET `role` = '" + role
					+ "' WHERE `userid` = " + userid + ";";
			Statement statement = SqlUtil.getConnection().createStatement();
			statement.executeUpdate(sql);
			System.out.println("result is " + result);
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = 0;
		}

		return flag;
	}

	public static List<User> retrieveUserByRole(String role) {
		List<User> users = new LinkedList<>();
		ResultSet result = null;

		try {
			String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role`, `usermaster`.`isLocked` FROM `clothing`.`usermaster` where `usermaster`.`role`= '"
					+ role + "' ;";
			Statement statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			System.out.println("result is " + result);
			if (result != null) {
				while (result.next()) {
					System.out.println("user " + result.getInt(1));
					if (SELLER_ROLE.equals(result.getString(8))) {

						Seller user = new Seller();
						user.setUserId(result.getInt(1));
						user.setUserName(result.getString(2));
						user.setFirstName(result.getString(4));
						user.setLastName(result.getString(5));
						user.setEmail(result.getString(6));
						user.setPhoneNo(result.getString(7));
						user.setRole(result.getString(8));
						user.setLocked(result.getBoolean(9));
						users.add(user);
					} else {
						User e = new User(result.getInt(1),
								result.getString(2), null, result.getString(4),
								result.getString(5), true, null,
								result.getString(6), result.getString(7),
								result.getString(8));
						users.add(e);
					}

				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}

		return users;
	}

	public static List<User> retrieveUserLike(String keyword) {
		List<User> users = new LinkedList<>();
		ResultSet result = null;

		try {
			String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role` FROM `clothing`.`usermaster` where  `usermaster`.`role` ='USER' and (`usermaster`.`firstname` like '%"
					+ keyword
					+ "%' or `usermaster`.`lastname` like '%"
					+ keyword + "%') ;";
			Statement statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			System.out.println("result is " + result);
			if (result != null) {
				while (result.next()) {
					users.add(new User(result.getInt(1), result.getString(2),
							null, result.getString(4), result.getString(5),
							true, null, result.getString(6), result
									.getString(7), result.getString(8)));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}

		return users;
	}

	public static boolean enterUserName(String username, String password,
			String firstName, String lastName, String email, String phno,
			String addrs1, String addrs2, String street, String city,
			String state, String country, long pincode, String company,
			int status, String role) {
		PreparedStatement ps = null;
		try {
			boolean flag = true;
			int addressId = 0;
			try {

				String sql = "insert into address"
						+ "(address_line1,address_line2,street,city,state,country,zipcode)"
						+ "values(?,?,?,?,?,?,?)";
				ps = SqlUtil.getConnection().prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, addrs1);
				ps.setString(2, addrs2);
				ps.setString(3, street);
				ps.setString(4, city);
				ps.setString(5, state);
				ps.setString(6, country);
				ps.setLong(7, pincode);
				int executeUpdate = ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys != null && generatedKeys.next()) {
					addressId = generatedKeys.getInt(1);
				}
				ps.close();
				sql = "INSERT INTO `clothing`.`usermaster` (`username`,`password`,`firstname`,`lastname`,`address_id`,`email`,`phoneno`,`role`,`companyName`,`isLocked`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setString(3, firstName);
				ps.setString(4, lastName);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setInt(5, addressId);
				ps.setString(6, email);
				ps.setString(7, phno);
				ps.setString(8, role);
				ps.setString(9, company);
	//			ps.setLong(10, Long.parseLong(accno));
	//			ps.setInt(11, Integer.parseInt(routingno));
				ps.setInt(12, status);
				ps.executeUpdate();

			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			} finally {
				if (null != ps) {
					ps.close();
				}
			}

			return flag;
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return false;
	}

	public static boolean isUserNameAvailable(String userName) {
		boolean flag = false;
		try {
			PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
					(MySQLConnection) SqlUtil.getConnection(), url);
			ResultSet result = null;
			flag = true;
			try {
				String sql = "select * from usermaster where username = ?";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setString(1, userName);
				result = ps.executeQuery();
			} catch (SQLException ex) {
				flag = false;
			}

			try {

				if (result.next()) {
					flag = false;
				} else {
					flag = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return flag;
		} catch (SQLException ex) {
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return flag;
	}

	public static void DB_Close() throws Throwable {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
