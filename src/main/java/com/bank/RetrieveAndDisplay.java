
package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveAndDisplay {

    public static void main(String[] args) {

        String sql = """
                SELECT account_id,
                       account_number,
                       account_type,
                       customer_id,
                       balance,
                       currency,
                       status
                FROM bank_account
                WHERE balance > ?
                  AND status = ?
                """;

        try (Connection connection =
                     BankDatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, 100000);
            statement.setString(2, "Active");

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                boolean found = false;

                while (resultSet.next()) {
                    found = true;

                    System.out.println(
                            "Account ID: "
                                    + resultSet.getInt("account_id"));

                    System.out.println(
                            "Account Number: "
                                    + resultSet.getString("account_number"));

                    System.out.println(
                            "Account Type: "
                                    + resultSet.getString("account_type"));

                    System.out.println(
                            "Customer ID: "
                                    + resultSet.getInt("customer_id"));

                    System.out.println(
                            "Balance: "
                                    + resultSet.getBigDecimal("balance"));

                    System.out.println(
                            "Currency: "
                                    + resultSet.getString("currency"));

                    System.out.println(
                            "Status: "
                                    + resultSet.getString("status"));

                    System.out.println(
                            "-------------------------------------");
                }

                if (!found) {
                    System.out.println(
                            "No matching bank accounts found.");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
