package com.company.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    public Connection connect() {
        String ipStr = "127.0.0.1:8889";
        String dbName = "Hospital";
        String url = "jdbc:mysql://" + ipStr + "/" + dbName;
        String user = "root";
        String password = "root";
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
