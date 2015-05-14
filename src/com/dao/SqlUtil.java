package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtil {
	private static Connection con;
	public static Connection getConnection() {

        // DB connection
        String url = "jdbc:mysql://localhost:3306/clothing";
        String user = "root";
        String pass = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return con;
    }	
}
