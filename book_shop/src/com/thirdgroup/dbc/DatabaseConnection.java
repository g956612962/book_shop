package com.thirdgroup.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/book_shop";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "gy971102";
    private Connection conn = null;

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBDRIVER);
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() throws SQLException {
        if (this.conn != null)
            this.conn.close();
    }

}
