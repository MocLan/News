package com.news.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {
    private String url;
    private String user;
    private String password;
    private Connection connection = null;

    public MySQLUtil(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private Connection connetionMySQL()   {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection(){
        return connetionMySQL();
    }
}
