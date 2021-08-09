package com.lawson.expenseTracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {
    private static String url = "jdbc:mysql://localhost:3306/giraffe";
    private static String root = "root";
    private static String password = "090796";

//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public void setRoot(String root) {
//        this.root = root;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }



    private SqlConnector() {
    }

    public static Connection connect(String url, String root, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, root, password);
            System.out.println("Get connected for free with education connection!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, root, password);
            System.out.println("Get connected for free with education connection!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
