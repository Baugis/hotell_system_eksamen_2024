package org.eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    public static void main(String[] args) {

        // Laste inn JDBC Driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // String tableName = "tblkunde";
        // getData(tableName);
    }

    // Hent data fra tabeller i database
    public ArrayList<String> getData(String tabellNavn) {
        // Koble til database
        String url = "jdbc:postgresql://localhost:5432/hotell";
        String user = "postgres";
        String password = "postgres";
        ArrayList<String> tempListe = new ArrayList<>();

        // Koble til
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Lage statement
            try (Statement statement = connection.createStatement()) {

                // SQL query
                String query = "SELECT * FROM " + tabellNavn;
                ResultSet resultSet = statement.executeQuery(query);

                // Hente metadata, telle kolonner
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Gå gjennom hver rad og kolonne
                while (resultSet.next()) {

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnName + ": " + columnValue + "\t");
                        tempListe.add(columnValue);
                    }

                    // Legge til data fra tabell i ordliste med bruk av ArrayList og type?
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
        return tempListe;
    }

    // Send data til tabeller i database
}
