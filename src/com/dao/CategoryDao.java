/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;


import com.model.Category;
import com.model.Item;
import com.model.ItemDetail;
import com.mysql.jdbc.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shruti
 */
public class CategoryDao {

    String url = "jdbc:mysql://localhost:3306/clothing";

    public CategoryDao() {
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

    public Item findByName(String name) {
        return null;
    }

    public Collection<Item> getItemsByCategory(Category cat) {
        return null;
    }

    public Collection<Item> getItemsLikeName(String Name) {
        return null;
    }

    private static ItemDetail map(ResultSet resultSet) throws SQLException {

        // String id = resultSet.getLong("id"));
        // fetch all column data and pass it to ItemDetail Constructor
        //
        return new ItemDetail(/* pass all argumenst */);
    }

    public List<Category> getCategories(String parentCategory) {
        List<Category> categorys = new LinkedList<>();
        ResultSet result = null;

        try {
            String sql = "SELECT `category`.`id`,`category`.`category_name`,`category`.`description` FROM `clothing`.`category` where `category`.`parent_category`="
                    + parentCategory + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Category category = new Category(result.getInt(1),
                            result.getString(2), result.getString(3), null);
                    categorys.add(category);
                }
            }
        } catch (SQLException ex) {

        }

        return categorys;

    }

   
    public List<Category> getRootCategories() {
        List<Category> categorys = new LinkedList<>();
        ResultSet result = null;

        try {
            String sql = "SELECT `category`.`id`,`category`.`category_name`,`category`.`description` FROM `clothing`.`category` where `category`.`parent_category` is NULL;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Category category = new Category(result.getInt(1),
                            result.getString(2), result.getString(3), null);
                    categorys.add(category);
                }
            }
        } catch (SQLException ex) {

        }

        return categorys;

    }

    public List<Category> getAllCategories() {
        List<Category> categorys = new LinkedList<>();
        ResultSet result = null;

        try {
            String sql = "SELECT `category`.`id`,`category`.`category_name`,`category`.`description` FROM `clothing`.`category` ;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Category category = new Category(result.getInt(1),
                            result.getString(2), result.getString(3), null);
                    categorys.add(category);
                }
            }
        } catch (SQLException ex) {

        }

        return categorys;

    }

    public boolean addCategory(String name, String description,
            String parentCategory, String[] styleNames) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
                    (MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            System.out.println( name+ " " + description+ " a" + parentCategory+"a");
            try {
//                if (parentCategory != null && !parentCategory.equals("")) {
//                      System.out.println("there");
//                    String sql = "INSERT INTO `clothing`.`category` ( `category_name`, `description`, `parent_category`) VALUES (?,?,?);";
//                    ps = SqlUtil.getConnection().prepareStatement(sql);
//                    ps.setString(1, name);
//                    ps.setString(2, description);
//                    ps.setInt(4, Integer.parseInt(parentCategory));
//                    ps.executeUpdate();
//                } else {
//                  
                    String sql = "INSERT INTO `clothing`.`category` ( `category_name`, `description`, `parent_category`) VALUES ('"+name+"','"+description+"',"+parentCategory+");";
                    Statement statement = SqlUtil.getConnection().createStatement();
                    
                    statement.executeUpdate(sql);
                    for(int i=0;i<styleNames.length;i++){
                        addStyle(styleNames[i]);
//                        addCategoryStyle(name, sql)
                    }
//                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return false;
    }
    public boolean addCategoryStyle(String category, String style) {
            boolean flag = true;
            System.out.println( category+ " " + style);
            try {
//                if (parentCategory != null && !parentCategory.equals("")) {
//                      System.out.println("there");
//                    String sql = "INSERT INTO `clothing`.`category` ( `category_name`, `description`, `parent_category`) VALUES (?,?,?);";
//                    ps = SqlUtil.getConnection().prepareStatement(sql);
//                    ps.setString(1, name);
//                    ps.setString(2, description);
//                    ps.setInt(4, Integer.parseInt(parentCategory));
//                    ps.executeUpdate();
//                } else {
//                  
                    String sql = "INSERT INTO `clothing`.`category_style` (`category_id`, `style_id`) VALUES ("+category+","+style+");";
                    Statement statement = SqlUtil.getConnection().createStatement();
                    
                    statement.executeUpdate(sql);
//                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = false;
            }
            return flag;
      }
    public boolean addStyle(String name) {
            boolean flag = true;
            System.out.println( name);
            try {
//                if (parentCategory != null && !parentCategory.equals("")) {
//                      System.out.println("there");
//                    String sql = "INSERT INTO `clothing`.`category` ( `category_name`, `description`, `parent_category`) VALUES (?,?,?);";
//                    ps = SqlUtil.getConnection().prepareStatement(sql);
//                    ps.setString(1, name);
//                    ps.setString(2, description);
//                    ps.setInt(4, Integer.parseInt(parentCategory));
//                    ps.executeUpdate();
//                } else {
//                  
                    String sql = "INSERT INTO `clothing`.`style` (`name`) VALUES ('"+name+"');";
                    Statement statement = SqlUtil.getConnection().createStatement();
                    
                    statement.executeUpdate(sql);
//                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = false;
            }
            return flag;
      }

    public boolean updateCategory(String id, String name, String description,
            String parentCategory) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
                    (MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            try {
                String sql = "UPDATE `clothing`.`category` SET `category_name` = ?, `description` =?, `parent_category` =" + parentCategory + "  WHERE `id` =" + id + " ;";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setInt(4, Integer.parseInt(parentCategory));
                ps.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return false;
    }
}
