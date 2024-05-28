package org.eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static void main(String[] args) {

        // Laste inn JDBC Driver
        try {
            Class.forName("org.postgresql.Driver"); // This is for PostgreSQL.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String tableName = "tblkunde";
        getData(tableName);
    }

    // Hent data fra tabeller i database
    public static void getData(String tableName) {
        // Koble til database
        String url = "jdbc:postgresql://localhost:5432/hotell";
        String user = "postgres";
        String password = "postgres";

        // Koble til
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Lage statement
            try (Statement statement = connection.createStatement()) {

                // SQL query
                String query = "SELECT * FROM " + tableName;
                ResultSet resultSet = statement.executeQuery(query);

                // Hente metadata, telle kolonner
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Gå gjennom hver rad og kolonne
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {

                        // Lage objekt og legge til i liste
                        String columnName = rsmd.getColumnName(i);
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnName + ": " + columnValue + "\t");
                    }
                    System.out.println();
                }

                // Avslutte
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Send data til tabeller i database
}
