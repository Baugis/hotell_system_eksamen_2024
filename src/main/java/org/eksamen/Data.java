package org.eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {

    public static void main(String[] args) {

        // Load JDBC driver
        try {
            Class.forName("org.postgresql.Driver"); // This is for PostgreSQL.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }


        String tableName = "tblkunde";
        getData(tableName);
    }

    public static void getData(String tableName) {
        // JDBC URL, username and password of PostgreSQL server
        String url = "jdbc:postgresql://localhost:5432/hotell"; // Replace with your database URL
        String user = "postgres"; // Replace with your database username
        String password = "postgres"; // Replace with your database password

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Create a statement object to execute queries
            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM " + tableName; // Sample query
                ResultSet resultSet = statement.executeQuery(query);

                // Get the ResultSetMetaData
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Iterate through the result set and print each record
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnName + ": " + columnValue + "\t");
                    }
                    System.out.println();
                }

                // Close the result set
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
