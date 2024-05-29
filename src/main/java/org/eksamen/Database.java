package org.eksamen;

import org.eksamen.Entity.*;

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
                    } else if (value instanceof Avbestillinger) {
                        Avbestillinger avbestillinger = (Avbestillinger) value;
                        if (!avbestillingExistsInDatabase(avbestillinger, connection)) {
                            statement.setInt(1, avbestillinger.getAvbestillingsid());
                            statement.setInt(2, avbestillinger.getReservasjonsid());
                            statement.setString(3, avbestillinger.getAvbestillingdato());
                        }
                    } else if (value instanceof Innsjekkinger) {
                        Innsjekkinger innsjekkinger = (Innsjekkinger) value;
                        if (!innsjekkingExistsInDatabase(innsjekkinger, connection)) {
                            statement.setInt(1, innsjekkinger.getInnsjekkingsid());
                            statement.setInt(2, innsjekkinger.getReservasjonsid());
                            statement.setString(1, innsjekkinger.getInnsjekkingdato());
                        }
                    } else if (value instanceof Reservasjoner) {
                        Reservasjoner reservasjoner = (Reservasjoner) value;
                        if (!reservasjonExistsInDatabase(reservasjoner, connection)) {
                            statement.setInt(1, reservasjoner.getReservasjonid());
                            statement.setInt(2, reservasjoner.getKundeid());
                            statement.setInt(3, reservasjoner.getRomid());
                            statement.setString(4, reservasjoner.getStartdato());
                            statement.setString(5, reservasjoner.getSluttdato());
                            statement.setString(6, reservasjoner.getStatus());
                        }
                    } else if (value instanceof Utsjekkinger) {
                        Utsjekkinger utsjekkinger = (Utsjekkinger) value;
                        if (!utsjekkingExistsInDatabase(utsjekkinger, connection)) {
                            statement.setInt(1, utsjekkinger.getUtsjekkingid());
                            statement.setInt(2, utsjekkinger.getReservasjonid());
                            statement.setString(3, utsjekkinger.getUtsjekkingdato());
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

    // Check if a Avbestillinger already exists in the database based on unique identifier (avbestillingid)
    private boolean avbestillingExistsInDatabase(Avbestillinger avbestilling, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblavbestilling WHERE avbestillingid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, avbestilling.getAvbestillingsid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

    // Check if a Innsjekkinger already exists in the database based on unique identifier (innsjekingid)
    private boolean innsjekkingExistsInDatabase(Innsjekkinger innsjekking, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblinnsjekking WHERE innsjekkingid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, innsjekking.getInnsjekkingsid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

    // Check if a Reservasjoner already exists in the database based on unique identifier (reservasjonid)
    private boolean reservasjonExistsInDatabase(Reservasjoner reservasjon, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblreservasjon WHERE reservasjonid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservasjon.getReservasjonid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

    // Check if a Utsjekkinger already exists in the database based on unique identifier (utsjekkingid)
    private boolean utsjekkingExistsInDatabase(Utsjekkinger utsjekking, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM tblutsjekking WHERE utsjekkingid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, utsjekking.getReservasjonid());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0 (Rom exists), false otherwise
            }
        }
    }

}
