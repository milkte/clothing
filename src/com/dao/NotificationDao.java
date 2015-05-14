/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Notification;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class NotificationDao {

	String url = "jdbc:mysql://localhost:3306/clothing";

	public NotificationDao() {
	}

	public boolean addNotification(String subject, String foruser ,
			String associatedUser, String type) {
		boolean flag = true;
		Statement  statement = null;
		try {
			
			//String sql = "INSERT INTO notification(subject,for,ondate,associated_user,type) VALUES (\'"+subject+"\',"+foruser+",now(),"+associatedUser+",\'"+type+"\');";
			
			String sql = "INSERT INTO `clothing`.`notification`(`subject`,`for`,`ondate`,`isSeen`,`associated_user`,`type`) VALUES('"+subject+"',"+Integer.parseInt(foruser)+",now(),"+0+","+Integer.parseInt(associatedUser)+",'"+type+"')";
			
			statement = SqlUtil.getConnection().createStatement();
			statement.executeUpdate(sql);
			// }
		} catch (SQLException ex) {
			ex.printStackTrace();
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

	public List<Notification> getNotificationForUser(String userId) {
		List<Notification> notifications = new LinkedList<>();
		ResultSet result;
		Statement statement = null;
		try {
			String sql = "SELECT `notification`.`id`,`notification`.`subject`,`notification`.`for`,`notification`.`ondate`,`notification`.`isSeen`,`notification`.`associated_user` FROM `clothing`.`notification` where `notification`.`isSeen`=0 and `for`="
					+ userId + ";";
			statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			if (result != null) {
				while (result.next()) {
					notifications
							.add(new Notification(
									result.getInt(1),
									result.getString(2),
									result.getDate(4),
									null,
									new AuthDAO().getUserById(result.getInt(5)),
									false));
				}
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

		return notifications;
	}

	public int getNotificationCountForUser(String userId) {
		ResultSet result;
		int notificationCount = 0;
		Statement statement = null;
		try {
			String sql = "SELECT count(id) FROM clothing.notification where `notification`.`isSeen`=0 and `for`="
					+ userId + " group by `for`;";
			statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			if (result != null && result.next()) {
				notificationCount = result.getInt(1);
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

		return notificationCount;
	}

	public void markAsReadNotification(String user) {
		Statement statement = null;
		try {
			String sql = "UPDATE `clothing`.`notification` SET `isSeen` = 1 WHERE `for`="
					+ user + ";";
			statement = SqlUtil.getConnection().createStatement();
			statement.executeUpdate(sql);
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
	}
}
