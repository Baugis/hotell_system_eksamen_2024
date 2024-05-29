package org.eksamen;

import org.eksamen.Entity.*;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/hotell";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public ArrayList<String> getData(String tabellNavn) {
        ArrayList<String> tempListe = new ArrayList<>();

        String query = "SELECT * FROM " + tabellNavn;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = resultSet.getString(i);
                    tempListe.add(columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempListe;
    }

    public void sendData(String query, ArrayList<?> data) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (Object value : data) {
                if (value instanceof Rom) {
                    Rom rom = (Rom) value;
                    if (!entityExistsInDatabase("tblrom", "romid", rom.getRomid(), connection)) {
                        statement.setInt(1, rom.getRomid());
                        statement.setInt(2, rom.getRomnummer());
                        statement.setString(3, rom.getRomtype());
                        statement.setFloat(4, rom.getPris());
                        statement.addBatch();
                    }
                } else if (value instanceof Kunder) {
                    Kunder kunde = (Kunder) value;
                    if (!entityExistsInDatabase("tblkunde", "kundeid", kunde.getKundeid(), connection)) {
                        statement.setInt(1, kunde.getKundeid());
                        statement.setString(2, kunde.getNavn());
                        statement.setString(3, kunde.getEpost());
                        statement.setString(4, kunde.getTelefon());
                        statement.addBatch();
                    }
                } else if (value instanceof Avbestillinger) {
                    Avbestillinger avbestilling = (Avbestillinger) value;
                    if (!entityExistsInDatabase("tblavbestilling", "avbestillingid", avbestilling.getAvbestillingsid(), connection)) {
                        statement.setInt(1, avbestilling.getAvbestillingsid());
                        statement.setInt(2, avbestilling.getReservasjonsid());
                        statement.setString(3, avbestilling.getAvbestillingdato());
                        statement.addBatch();
                    }
                } else if (value instanceof Innsjekkinger) {
                    Innsjekkinger innsjekking = (Innsjekkinger) value;
                    if (!entityExistsInDatabase("tblinnsjekking", "innsjekkingid", innsjekking.getInnsjekkingsid(), connection)) {
                        statement.setInt(1, innsjekking.getInnsjekkingsid());
                        statement.setInt(2, innsjekking.getReservasjonsid());
                        statement.setString(3, innsjekking.getInnsjekkingdato());
                        statement.addBatch();
                    }
                } else if (value instanceof Reservasjoner) {
                    Reservasjoner reservasjon = (Reservasjoner) value;
                    upsertReservasjon(connection, reservasjon);
                } else if (value instanceof Utsjekkinger) {
                    Utsjekkinger utsjekking = (Utsjekkinger) value;
                    if (!entityExistsInDatabase("tblutsjekking", "utsjekkingid", utsjekking.getUtsjekkingid(), connection)) {
                        statement.setInt(1, utsjekking.getUtsjekkingid());
                        statement.setInt(2, utsjekking.getReservasjonid());
                        statement.setString(3, utsjekking.getUtsjekkingdato());
                        statement.addBatch();
                    }
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean entityExistsInDatabase(String tableName, String idColumn, int id, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idColumn + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    private void upsertReservasjon(Connection connection, Reservasjoner reservasjon) throws SQLException {
        String query = "INSERT INTO tblreservasjon (reservasjonid, kundeid, romid, startdato, sluttdato, status) VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (reservasjonid) DO UPDATE SET kundeid = EXCLUDED.kundeid, romid = EXCLUDED.romid, startdato = EXCLUDED.startdato, sluttdato = EXCLUDED.sluttdato, status = EXCLUDED.status";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservasjon.getReservasjonid());
            statement.setInt(2, reservasjon.getKundeid());
            statement.setInt(3, reservasjon.getRomid());
            statement.setDate(4, Date.valueOf(reservasjon.getStartdato()));
            statement.setDate(5, Date.valueOf(reservasjon.getSluttdato()));
            statement.setString(6, reservasjon.getStatus());
            statement.executeUpdate();
        }
    }
}
