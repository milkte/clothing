package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.model.Category;
import com.model.Color;
import com.model.Item;
import com.model.ItemDetail;
import com.model.Seller;
import com.model.Size;
import com.model.Style;
import com.mysql.jdbc.MySQLConnection;

public class ItemDAOImpl implements ItemDAO {

    private String url = "jdbc:mysql://localhost:3306/clothing";
    private static final String FIND_BY_ID = " Query 1";
    private static final String FIND_BY_NAME = " Query 2";
    private static final String FIND_BY_CATEGORY = " Query 3";
    private static final String ITEMS_LIKE_NAME = " Query 4 ";

    private DAOFactory daoFactory;

    public ItemDAOImpl() {
    }

    ItemDAOImpl(DAOFactory dao) {
        this.daoFactory = dao;
    }

    @Override
    public Item findbyID(String id) {
//        ItemDetail item=null;
//        try {
//                Connection connection = daoFactory.SqlUtil.getConnection();
//                PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false, id);
//                ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                item = map(resultSet);
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//        return item;
        return null;
    }

    @Override
    public Item findByName(String name) {
        return null;
    }

    @Override
    public Collection<Item> getItemsByCategory(Category cat) {
        return null;
    }

    @Override
    public Collection<Item> getItemsLikeName(String name) {
        List<Item> items = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT * FROM `clothing`.`item` where LOWER(`item`.`item_name`)  like '%" + name.toLowerCase() + "%';";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Item item = new Item();
                    item.setItemid(result.getInt("id"));
                    item.setPrice(result.getDouble("price"));
                    item.setItemName(result.getString("item_name"));
                    item.setSeller(AuthDAO.getSellerById(result.getInt("seller")));
                    item.setStyle(getStyleById(result.getInt("style")));
                    items.add(item);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return items;
    }

    private static ItemDetail map(ResultSet resultSet) throws SQLException {

        // String id = resultSet.getLong("id"));
        // fetch all column data and pass it to ItemDetail Constructor
        //
        return new ItemDetail(/* pass all argumenst */);
    }

    public List<Item> getItemsByCategory(int categoryid) {
        Set<Item> itemSet = new HashSet<>();
        ResultSet result;
        List<Item>items = new LinkedList<Item>();
        try {
            String sql = "SELECT `item`.`id`,"
                    + "    `item`.`style`,"
                    + "    `item`.`selected_item`,"
                    + "    `item`.`price`,"
                    + "    `item`.`item_name`,"
                    + "    `usermaster`.`userid`,"
                    + "    `usermaster`.`username`,"
                    + "    `style`.`name`,"
                    + "    `size`.`size_code`,"
                    + "	`color`.`name`"
                    + " FROM  `clothing`.`item_detail`,`clothing`.`usermaster`,`clothing`.`item` ,`clothing`.`style`,`clothing`.`color`,`clothing`.`size`"
                    + " where   `item_detail`.`item_id`= `item`.`id`and  `item_detail`.`color`= `color`.`id` and `item_detail`.`size`=`size`.`id` and `item`.`seller`=`usermaster`.`userid`  and `item`.`category`=" + categoryid
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Item item = new Item();
                    
                    Seller seller = new Seller();
                    seller.setUserId(result.getInt(6));
                    seller.setUserName(result.getString(7));
					item.setSeller(seller );
                    item.setItemid(result.getInt(1));
                    item.setPrice(result.getDouble(4));
                    item.setItemName(result.getString(5));
                    item.setStyle(new Style(result.getInt(2), result.getString(8)));
                    item.setCategory(new Category(categoryid, null, null, null));

                    itemSet.add(item);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        items.addAll(itemSet);
        return items ;

    }

    public List<Item> getItemsByName(String name) {
        List<Item> items = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `item`.`id`,"
                    + "    `item`.`style`,"
                    + "    `item`.`selected_item`,"
                    + "    `item`.`price`,"
                    + "    `item`.`item_name`,"
                    + "    `item`.`seller`,"
                    + "    `style`.`name`,"
                    + "    `size`.`size_code`,"
                    + "	`color`.`name`"
                    + " FROM  `clothing`.`item_detail`,`clothing`.`item` ,`clothing`.`style`,`clothing`.`color`,`clothing`.`size`"
                    + " where   `item_detail`.`item_id`= `item`.`id`and  `item_detail`.`color`= `color`.`id` and `item_detail`.`size`=`size`.`id` and `item`.`style`=`style`.`id` and and LOWER(`item`.`item_name`)  like '%" + name.toLowerCase() + "%';";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Item item = new Item();
                    item.setItemid(result.getInt(1));
                    item.setPrice(result.getDouble(4));
                    item.setItemName(result.getString(5));
                    item.setStyle(new Style(result.getInt(2), result.getString(7)));

                    items.add(item);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return items;

    }

    public List<Item> searchItems(String listOfColors, String size) {
        List<Item> items = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `item`.`id`,"
                    + "    `item`.`style`,"
                    + "    `item`.`selected_item`,"
                    + "    `item`.`price`,"
                    + "    `item`.`item_name`,"
                    + "    `item`.`seller`,"
                    + "    `style`.`name`,"
                    + "    `size`.`size_code`,"
                    + "	`color`.`name`"
                    + " FROM  `clothing`.`item_detail`,`clothing`.`item` ,`clothing`.`style`,`clothing`.`color`,`clothing`.`size`"
                    + " where   `item_detail`.`item_id`= `item`.`id`and  `item_detail`.`color`= `color`.`id` and `item_detail`.`size`=`size`.`id` and `item`.`style`=`style`.`id`  ";
            if (listOfColors != null && !listOfColors.isEmpty()) {
                sql += " and `color`.`id` in (" + listOfColors + ") ";
            }
            if (size != null && !size.isEmpty()) {
                sql += " and `item_detail`.`size` in (" + size + ") ";
            }
            sql += " ;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Item item = new Item();
                    item.setItemid(result.getInt(1));
                    item.setPrice(result.getDouble(4));
                    item.setItemName(result.getString(5));
                    item.setStyle(new Style(result.getInt(2), result.getString(7)));

                    items.add(item);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return items;

    }

    public List<ItemDetail> findItemDetailByItem(int itemId) {
        List<ItemDetail> categorys = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `item_detail`.`item_id`,"
                    + "    `item_detail`.`color`,"
                    + "    `item_detail`.`size`"
                    + " FROM  `clothing`.`item_detail`  where  `item_detail`.`item_id`=" + itemId
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    Size size = getSizeById(result.getInt(3));
                    Color color = getColorById(result.getInt(2));
                    ItemDetail itemDetail = new ItemDetail(null, size, color);
                    categorys.add(itemDetail);
                }
                System.out.println("Item details= " + categorys);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return categorys;

    }

    public boolean addItem(String name, Double price,
            String[] color, String[] size, String style, String category,String[] stock,int sellerId) {
        boolean flag = true;
        Statement statement= null;
        try {

        	 String sql = "INSERT INTO `clothing`.`item` (`style`, `price`,`item_name`,`category`,`seller`) VALUES (" + style + "," + price + ",'" + name + "'," + category +"," + sellerId + ");";
        	  statement = SqlUtil.getConnection().createStatement();

            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys != null && generatedKeys.next()) {
                int itemId = generatedKeys.getInt(1);
                for (int i = 0; i < color.length; i++) {
                    addItemdetails(itemId, color[i], size[i],stock[i]);
                    //                        addCategoryStyle(name, sql)
                }
            }

//                }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            flag = false;
        }
        return flag;

    }

    public boolean addItemdetails(int itemId, String color, String size, String stock) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
                    (MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            try {
                String sql = "INSERT INTO `clothing`.`item_detail` (`item_detail`.`item_id`,`color`,`size`,`stock`) VALUES (" + itemId + "," + color + "," + size + "," + stock + ");";
                Statement statement = SqlUtil.getConnection().createStatement();

                statement.executeUpdate(sql);

//                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return false;
    }

    public boolean addSttock(int itemId, String color, String size,int stock) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
                    (MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            try {
                String sql = "UPDATE `clothing`.`item_detail` SET `stock` = `stock` + "+stock+" WHERE `item_id` = "+itemId+" AND `color` = "+color+" AND `size` = "+size+";";
                Statement statement = SqlUtil.getConnection().createStatement();

                statement.executeUpdate(sql);

//                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return false;
    }
    public boolean reduceSttock(String itemId, String color, String size,int stock) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement(
                    (MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            try {
                String sql = "UPDATE `clothing`.`item_detail` SET `stock` = `stock` -"+stock+" WHERE `item_id` = "+itemId+" AND `color` = "+color+" AND `size` = "+size+";";
                Statement statement = SqlUtil.getConnection().createStatement();

                statement.executeUpdate(sql);

//                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return false;
    }

    public Color getColorById(int id) {
        Color color = null;
        ResultSet result;

        try {
            String sql = "SELECT `color`.`id`,"
                    + "    `color`.`name`"
                    + " FROM  `clothing`.`color`  where  `color`.`id`=" + id
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.next()) {
                color = new Color(result.getInt(1), result.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return color;

    }

    public Size getSizeById(int id) {
        Size size = null;
        ResultSet result;

        try {
            String sql = "SELECT `size`.`id`,"
                    + "    `size`.`size_code`,"
                    + "    `size`.`description`"
                    + " FROM  `clothing`.`size`  where  `size`.`id`=" + id
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.next()) {
                size = new Size(result.getInt(1), result.getString(2), result.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return size;
    }

    public Style getStyleById(int id) {
        Style style = null;
        ResultSet result;

        try {
            String sql = "SELECT `style`.`id`, `style`.`name` FROM `clothing`.`style` where `style`.`id`=" + id;
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.next()) {
                style = new Style(result.getInt(1), result.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return style;
    }

    public List<Color> getAllColors() {
        List<Color> colors = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `color`.`id`,`color`.`name` FROM `clothing`.`color`;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);

            if (result != null) {
                while (result.next()) {
                    colors.add(new Color(result.getInt(1), result.getString(2)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return colors;
    }

    public List<Size> getAllSizes() {
        List<Size> sizes = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `size`.`id`, `size`.`size_code`, `size`.`description` FROM `clothing`.`size`;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    sizes.add(new Size(result.getInt(1), result.getString(2), result.getString(3)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return sizes;
    }

    public List<Style> getStyleByCategory(int categoryId) {
        List<Style> styles = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `style`.`id`, `style`.`name` FROM `clothing`.`style`, `clothing`.`category_style` where `category_style`.`style_id`=`style`.`id` and `category_style`.`category_id`=" + categoryId + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    styles.add(new Style(result.getInt(1), result.getString(2)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return styles;
    }

    public Item getItemById(int id) {
        Item item = null;
        ResultSet result;

        try {
            String sql = "SELECT `item`.`id`,"
                    + "    `item`.`style`,"
                    + "    `item`.`price`,"
                    + "    `item`.`item_name`,"
                    + "    `item`.`seller`,"
                    + "    `item`.`category` "
                    + " FROM  `clothing`.`item` "
                    + " where  `item`.`id`=" + id
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.next()) {
                System.out.println("inside");
                int itemid = result.getInt(1);
                Style style = getStyleById(result.getInt(2));
                Seller seller = new Seller(result.getInt(5), null, null);
                item = new Item(itemid, result.getString(4), result.getDouble(3), seller, style, findItemDetailByItem(itemid), null, null);
                System.out.println("items: " + item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return item;
    }

    public List<Item> getItemBySeller(int id) {
        List<Item> items = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `item`.`id`,"
                    + "    `item`.`style`,"
                    + "    `item`.`price`,"
                    + "    `item`.`item_name`,"
                    + "    `item`.`seller`,"
                    + "    `item`.`category` "
                    + " FROM  `clothing`.`item` "
                    + " where  `item`.`seller`=" + id
                    + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null) {
                while (result.next()) {
                    System.out.println("inside");
                    int itemid = result.getInt(1);
                    Style style = getStyleById(result.getInt(2));
                    items.add(new Item(itemid, result.getString(4), result.getDouble(3), null, style, findItemDetailByItem(itemid), null, null));
                    System.out.println("items: " + items);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return items;
    }

    public ItemDetail getItem(int id, int color, int size) {
        ItemDetail itemDetail = new ItemDetail();
        ResultSet result;

        try {
            String sql = "SELECT   `item`.`item_name`,`color`.`name`,`size`.`size_code`,`item_detail`.`main_image`, `item_detail`.`alternate_images` FROM `clothing`.`item_detail`, `clothing`.`item`, `clothing`.`size`,`clothing`.`color` where `item`.`id`=`item_detail`.`item_id` and `item_detail`.`size`= `size`.`id` and `item_detail`.`color`=`color`.`id` and `item`.`id`=" + id + " and `item_detail`.`size`= " + size + " and `item_detail`.`color`= " + color + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null) {
                while (result.next()) {
                    System.out.println("inside");

                    Item item = new Item();
                    item.setItemid(id);
                    item.setItemName(result.getString(1));

                    itemDetail.setItem(item);
                    itemDetail.setColor(new Color(color, result.getString(2)));
                    itemDetail.setSize(new Size(size, result.getString(3), null));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return itemDetail;
    }
   

}
