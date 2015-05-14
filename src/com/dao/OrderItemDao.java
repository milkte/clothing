/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.ItemDetail;
import com.model.OrderItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class OrderItemDao {

    String url = "jdbc:mysql://localhost:3306/clothing";

    public static boolean addOrderItem(String itemId, String color, String size, int seller, int orderId, int qty, double price) {
        boolean flag = true;
        Statement statement=null;
        try {
        	
            String sql = "INSERT INTO `clothing`.`order_item`"
                    + "(`item_id`,"
                    + "`color`,"
                    + "`size`,"
                    + "`order_id`,"
                    + "`quantity`,"
                    + "`price_per_item`,"
                    + "`seller`)"
                    + "VALUES"
                    + "(" + itemId
                    + "," + color
                    + "," + size
                    + "," + orderId
                    + "," + qty + "," + price + " ," + seller
                    + ");";
            statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }finally{
        	if(null!=statement){
        		try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
        return flag;
    }

    public static boolean editOrderItem(String id, String seller, String itemId, String color, String size, int orderId, int qty, double price) {
        boolean flag = true;
        Statement statement = null;
        try {
            String sql = "UPDATE `clothing`.`order_item` "
                    + "SET "
                    + "`item_id` = " + itemId + ", "
                    + "`color` = " + color + ", "
                    + "`size` = " + size + ", "
                    + "`order_id` = " + orderId + ", "
                    + "`quantity` = " + qty + ", "
                    + "`price_per_item` = " + price
                    + " WHERE `id` = " + id + " and seller= " + seller + ";";
             statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }finally{
        	if(null!=statement){
        		try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
        return flag;
    }

    public static List<OrderItem> GetOrderItem(String id, String seller) {
        List<OrderItem> orderItems = new LinkedList<>();
        Statement statement =null;
        try {
            ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
            String sql = "SELECT `order_item`.`id`,`order_item`.`item_id`,`order_item`.`color`,`order_item`.`size`,`order_item`.`order_id`,`order_item`.`quantity`,`order_item`.`price_per_item`,`order_item`.`seller` FROM `clothing`.`order_item` where item_id=" + id + " and seller=" + seller + ";";
            statement = SqlUtil.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {

                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(resultSet.getInt(1));
                    orderItem.setQty(resultSet.getInt(6));
                    ItemDetail item = itemDAOImpl.getItem(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
                    orderItem.setItemDetail(item);
                    orderItems.add(orderItem);
                }

            }
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        	if(null!=statement){
        		try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
        return orderItems;
    }

    public static boolean deleteOrderItem(String id, String seller) {
        boolean flag = true;
        Statement statement=null;
        try {
            String sql = "DELETE FROM `clothing`.`order_item` "
                    + " WHERE `id` = " + id + "  and seller= " + seller + ";";
            statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }finally{
        	if(null!=statement){
        		try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
        return flag;
    }
}
