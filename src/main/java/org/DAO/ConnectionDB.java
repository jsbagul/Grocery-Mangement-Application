package org.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection connection;
    static {
        String url="jdbc:mysql://localhost:3306/Grocery";
        try {
            connection=DriverManager.getConnection(url,"root","Redminote5@");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
