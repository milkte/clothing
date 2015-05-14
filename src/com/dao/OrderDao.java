/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Item;
import com.model.Order;
import com.model.Status;
import com.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class OrderDao {

    String url = "jdbc:mysql://localhost:3306/clothing";
    private static final String FIND_BY_ID = " Query 1";
    private static final String FIND_BY_NAME = " Query 2";
    private static final String FIND_BY_CATEGORY = " Query 3";
    private static final String FIND_ROOT_CATEGORY = " Query 3";

    ItemDAOImpl itemDAO = new ItemDAOImpl();

    public OrderDao() {
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

    public int addInitialOrder(int orderId, int sellerid, String address, int user, String payment,  String total) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, 3);
            if (orderId == 0) {
                String sql = "INSERT INTO `clothing`.`order` ( "
                        + "`order_time`, `shipped_time`,"
                        + "`ship_to`, "
                        + "`payment`, "
                        + "`user`, "
                        + "`total`, "
                        + "`seller`) "
                        + "VALUES ("
                        + "now(), now() + interval 3 day,'"
                        + address + "', '"
                        + payment + "', "
                        + user + ", "
                        + total + ", "
                        +sellerid+ ");";
                Statement statement = SqlUtil.getConnection().createStatement();
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys != null && generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            } else {
                String sql = "INSERT INTO `clothing`.`order` (`id`,"
                            + "`order_time`, `shipped_time`,"
                        + "`ship_to`, "
                        + "`payment`, "
                        + "`user`, "
                        + "`total`, "
                        + "`seller`) "
                        + "VALUES ("
                        +orderId+ ",now(), now() + interval 3 day,'"
                        + address + "', '"
                        + payment + "', "
                        + user + ", "
                        + total + ", "
                        +sellerid+ ");";
                Statement statement = SqlUtil.getConnection().createStatement();
                statement.executeUpdate(sql);
            }

//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Order> getOrderBySeller(String sellerid) {
        List<Order> orders = new LinkedList<>();
        ResultSet result;

        try {
//            String sql = "SELECT `order`.`id`, `order`.`order_time`,`order`.`shipped_time`,`order`.`ship_to`,`order`.`status`,`order`.`payment`,`order_item`.`item_id`,`order_item`.`color`,`order_item`.`size`,`order`.`user`,`order_item`.`quantity`,`item`.`price`,`item`.`item_name`,`item`.`seller` FROM `clothing`.`order`,`clothing`.`order_item`,`clothing`.`item` where `order`.`id`=`order_item`.`order_id` and `item`.`id`=`order_item`.`item_id` and `item`.`seller`=" + sellerid + ";";
            String sql = "SELECT * FROM clothing.order where order.seller=" + sellerid + " ; ";
            Connection connection = SqlUtil.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("result: " + result);
            if (result != null) {
                while (result.next()) {
                    int orderId = result.getInt("order.id");
                    Date orderDate = result.getDate("order.order_time");
                    Date shippedDate = result.getDate("order.shipped_time");
                    String status = result.getString("order.status");
                    int userid = result.getInt("order.user");
                    User userById = AuthDAO.getUserById(userid);
//                    sql = "SELECT  `order_item`.`quantity`, `color`.`name`, `size`.`size_code`,`item`.`item_name`,`item`.`price`,  `order_item`.`item_id`,`order_item`.`color`,`order_item`.`size`  FROM `clothing`.`order_item`,`clothing`.`color`,`clothing`.`size`,`clothing`.`item` where `order_item`.`color`=`color`.`id` and `order_item`.`size`=`size`.`id` and `item`.`id`=`order_item`.`item_id` and `order_item`.`order_id`=" + sellerid + ";";
//                    Statement statement2 = connection.createStatement();
//                    ResultSet result2 = statement2.executeQuery(sql);
//                    Map<Item, Integer> itemMap = new HashMap<>();
//                    if (result2 != null) {
//                        while (result2.next()) {
//                            Item item = new Item(result2.getInt(6), result2.getString(4), result2.getDouble(5), null, null, null, new ItemDetail(null, new Size(result2.getInt(8), result2.getString(2), null), new Color(result.getInt(7), result2.getString(2))), null);
//                            itemMap.put(item, result2.getInt(5));
//                        }
//                    }
                    Order order = new Order(orderId, orderDate, shippedDate, null, status);
                    order.setBuyer(userById);
                    order.setPayment(result.getString("order.payment"));
                    order.setTotalAmount(result.getDouble("order.total"));
                    order.setShippedTime(result.getDate("order.shipped_time"));
                    System.out.println("ORDER: " + order);
//                    order.setItemQuantityMap(itemMap);
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return orders;
    }

    public boolean deleteOrderById(String id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM `clothing`.`order` WHERE id=" + id + ";";
            Connection connection = SqlUtil.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            flag = true;
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public Status getStatusById(int status) {
        Status status1 = null;
        ResultSet result;
        try {
            String sql = "SELECT `status`.`id`,    `status`.`status` FROM `clothing`.`status` where `status`.`id`=" + status + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    status1 = new Status(result.getInt(1), result.getString(2));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return status1;
    }

//    public List<Order> getOrderBySeller(int sellerId) {
//        List<Order> orders = new LinkedList<>();
//        ResultSet result;
//
//        try {
//            String sql = "SELECT `item`.`seller`,`item`.`item_name`,`item`.`price`,`order`.`id`,`order`.`order_time`,`order`.`shipped_time`,`order`.`ship_to`,`order`.`status`,`order`.`payment`,`order`.`item_id`, `order`.`color`,`order`.`size`,`order`.`user`,`order`.`quantity`,`item`.`style`,`item`.`category` FROM `clothing`.`order`, `clothing`.`item` where `item`.`id`=`order`.`item_id` and `item`.`seller`=" + ((Integer) sellerId).toString() + ";";
//            Statement statement = SqlUtil.getConnection().createStatement();
//            result = statement.executeQuery(sql);
//            if (result != null) {
//                while (result.next()) {
//                    Item item = null;
//
//                    int itemid = result.getInt(1);
//                    Style style = itemDAO.getStyleById(result.getInt(2));
//                    item = new Item(itemid, result.getString(4), result.getDouble(3), null, style, itemDAO.findItemDetailByItem(itemid), null, null);
//                    orders.add(new Order(result.getInt(1), result.getDate(2), result.getDate(3), null, getStatusById(result.getInt(5))));
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        }
//
//        return orders;
//    }
    public static boolean updateOrder(int orderId, String ordertime, String shippedTime, int sellerid, String address, int user, String payment, String status) {
        boolean flag = true;
        try {
            String sql = "UPDATE `clothing`.`order` "
                    + "SET "
                    + "`order_time` = '" + ordertime + "', "
                    + "`shipped_time` = '" + shippedTime + "', "
                    + "`ship_to` = " + address + ", "
                    + "`status` = '" + status + "', "
                    + "`payment` = '" + payment + "', "
                    + "`user` = " + user
                    + " WHERE `id` = " + orderId + " AND `seller` = " + sellerid + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static boolean updateOrder(String orderId, String shippedTime, String sellerid, String status) {
        boolean flag = true;
        try {
            String sql = "UPDATE `clothing`.`order` "
                    + "SET "
                    + "`shipped_time` = '" + shippedTime + "', "
                    + "`status` = '" + status + "' "
                    + " WHERE `id` = " + orderId + " AND `seller` = " + sellerid + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static boolean deleteOrder(String id) {
        boolean flag = true;
        try {
            String sql = "DELETE FROM `clothing`.`order` "
                    + " WHERE `id` = " + id + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

	public List<Order> getOrderByBuyer(int buyerId) {

        List<Order> orders = new LinkedList<>();
        ResultSet result;

        try {
//            String sql = "SELECT `order`.`id`, `order`.`order_time`,`order`.`shipped_time`,`order`.`ship_to`,`order`.`status`,`order`.`payment`,`order_item`.`item_id`,`order_item`.`color`,`order_item`.`size`,`order`.`user`,`order_item`.`quantity`,`item`.`price`,`item`.`item_name`,`item`.`seller` FROM `clothing`.`order`,`clothing`.`order_item`,`clothing`.`item` where `order`.`id`=`order_item`.`order_id` and `item`.`id`=`order_item`.`item_id` and `item`.`seller`=" + sellerid + ";";
            String sql = "SELECT * FROM clothing.order where order.user=" + buyerId + " ; ";
            Connection connection = SqlUtil.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("result: " + result);
            if (result != null) {
                while (result.next()) {
                    int orderId = result.getInt("order.id");
                    Date orderDate = result.getDate("order.order_time");
                    Date shippedDate = result.getDate("order.shipped_time");
                    String status = result.getString("order.status");
                    int userid = result.getInt("order.seller");
                    User seller = AuthDAO.getUserById(userid);
                    
                    User buyer = AuthDAO.getUserById(buyerId);

                    Order order = new Order(orderId, orderDate, shippedDate, null, status);
                    order.setBuyer(buyer);
                    order.setSeller(seller);
                    order.setPayment(result.getString("order.payment"));
                    order.setTotalAmount(result.getDouble("order.total"));
                    order.setShippedTime(result.getDate("order.shipped_time"));
                    System.out.println("ORDER: " + order);
//                    order.setItemQuantityMap(itemMap);
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return orders;
    
	}
}
