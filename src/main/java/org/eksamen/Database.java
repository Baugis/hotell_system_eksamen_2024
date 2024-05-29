package org.eksamen;

import org.eksamen.Entity.Kunder;
import org.eksamen.Entity.Rom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class Database {

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

    // Send data to table in the database
    public void sendData(String query, ArrayList<?> data) {
        // Connect to the database
        String url = "jdbc:postgresql://localhost:5432/hotell";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Prepare the SQL statement
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the parameters for the prepared statement based on the type of ArrayList
                for (Object value : data) {
                    if (value instanceof Rom) {
                        Rom rom = (Rom) value;
                        if (!romExistsInDatabase(rom, connection)) {
                            statement.setInt(1, rom.getRomid());
                            statement.setInt(2, rom.getRomnummer());
                            statement.setString(3, rom.getRomtype());
                            statement.setFloat(4, rom.getPris());
                            statement.addBatch();
                        }
                    } else if (value instanceof Kunder) {
                        Kunder kunde = (Kunder) value;
                        if (!kundeExistsInDatabase(kunde, connection)) {
                            statement.setInt(1, kunde.getKundeid());
                            statement.setString(2, kunde.getNavn());
                            statement.setString(3, kunde.getEpost());
                            statement.setString(4, kunde.getTelefon());
                            statement.addBatch();
                        }
                    }
                }
                // Execute the batch update
                statement.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Check if a Rom already exists in the database based on unique identifier (romid)
    private boolean romExistsInDatabase(Rom rom, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblrom WHERE romid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rom.getRomid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

    // Check if a Kunde already exists in the database based on unique identifier (kundeid)
    private boolean kundeExistsInDatabase(Kunder kunde, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblkunde WHERE kundeid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, kunde.getKundeid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

}
