
package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bank_database";

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER =
            "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection()
            throws SQLException {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver not found.", e);
        }

        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }
}
