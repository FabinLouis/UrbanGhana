package com.java;



import java.sql.*;


public class DBcon {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Optional: Load MySQL Driver (not strictly needed in JDBC 4.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/urbanghana", 
                "root", 
                ""
            );
            System.out.println("Connected Successfully");
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed at DBcon:");
            e.printStackTrace();
        }

        return con;
    }
}