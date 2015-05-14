/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Item;
import com.model.Message;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class MessageDao {

	public MessageDao() {
	}

	public Item findbyID(String id) {
		// ItemDetail item=null;
		// try {
		// Connection connection = daoFactory.SqlUtil.getConnection();
		// PreparedStatement statement = prepareStatement(connection,
		// FIND_BY_ID, false, id);
		// ResultSet resultSet = statement.executeQuery();
		//
		// if (resultSet.next()) {
		// item = map(resultSet);
		// }
		// } catch (SQLException e) {
		// throw new DAOException(e);
		// }
		// return item;
		return null;
	}

	public boolean addMessage(String to, String from, String message) {
		boolean flag = true;
		Statement statement = null;
		try {
			String sql = "INSERT INTO `clothing`.`message` ( `msg`,`from`,`to`,`ondate`) VALUES ('"
					+ message + "'," + from + "," + to + ",now());";
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

	// public List<Message> getMessagesForUser(String userId) {
	// List<Message> message = new LinkedList<>();
	// ResultSet result;
	//
	// try {
	// String sql =
	// "SELECT `notification`.`id`,`notification`.`subject`,`notification`.`for`,`notification`.`ondate`,`notification`.`isSeen`,`notification`.`associated_user` FROM `clothing`.`notification` where `notification`.`isSeen`=0 and `for`="+userId+";";
	// Statement statement = SqlUtil.getConnection().createStatement();
	// result = statement.executeQuery(sql);
	// if (result != null) {
	// while (result.next()) {
	// message.add(new Message(result.getInt(1), result.getString(2),
	// result.getDate(3),null,new
	// AuthDAO().getUserById(result.getInt(5)),false));
	// }
	// }
	// } catch (SQLException ex) {
	// ex.printStackTrace(System.out);
	// }
	//
	// return message;
	// }
	public void markAsReadMessage(String messageId) {
		Statement statement = null;
		try {
			String sql = "UPDATE `clothing`.`message` SET `isSeen` = 1 WHERE `id`="
					+ messageId + ";";
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

	public void markAsRead(String userId) {
		Statement statement = null;
		try {
			String sql = "UPDATE `clothing`.`message` SET `isSeen` = 1 WHERE `to`="
					+ userId + ";";
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

	public int getMessageCountForUser(String userId) {
		ResultSet result;
		int messageCount = 0;
		Statement statement = null;
		try {
			String sql = "SELECT count(id) FROM clothing.message where `to`="
					+ userId + " and `isSeen`=0 group by `to`;";
			statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			if (result != null && result.next()) {
				messageCount = result.getInt(1);
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

		return messageCount;
	}

	public List<Message> getMessagesForUser(String userId) {
		ResultSet result;
		List<Message> messages = new LinkedList<>();
		Statement statement = null;
		try {
			String sql = "SELECT * FROM clothing.message where `to`=" + userId
					+ " ;";
			statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			if (result != null) {
				while (result.next()) {
					messages.add(new Message(result.getInt("id"), result
							.getInt("to"), result.getInt("from"), result
							.getString("msg"),
							((result.getInt("isSeen") == 0) ? false : true),
							result.getDate("ondate")));
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

		return messages;
	}

	public Message getMessageById(String msgId) {
		ResultSet result;
		Message message = null;
		Statement statement = null;
		try {
			String sql = "SELECT * FROM clothing.message where `id`=" + msgId
					+ ";";
			statement = SqlUtil.getConnection().createStatement();
			result = statement.executeQuery(sql);
			if (result != null) {
				while (result.next()) {
					message = new Message(result.getInt("id"),
							result.getInt("to"), result.getInt("from"),
							result.getString("msg"),
							((result.getInt("isSeen") == 0) ? false : true),
							result.getDate("ondate"));
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

		return message;
	}
}
