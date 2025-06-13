package dao;

import models.Vol;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class VolDAO {

    // Méthode pour insérer un vol
    public boolean insertVol(Vol vol) {
        String sql = "INSERT INTO vols (vol_number, departure_airport, arrival_airport, departure_time, arrival_time, status, aircraft) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vol.getVolNumber());
            stmt.setString(2, vol.getDepartureAirport());
            stmt.setString(3, vol.getArrivalAirport());
            stmt.setTimestamp(4, Timestamp.valueOf(vol.getDepartureTime()));
            stmt.setTimestamp(5, Timestamp.valueOf(vol.getArrivalTime()));
            stmt.setString(6, vol.getStatus());
            stmt.setString(7, vol.getAircraft());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour récupérer un vol par ID
    public Vol getVolById(int id) {
        String sql = "SELECT * FROM vols WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToVol(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour récupérer tous les vols
    public List<Vol> getAllVols() {
        List<Vol> vols = new ArrayList<>();
        String sql = "SELECT * FROM vols";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Fetching all flights from the database...");

            while (rs.next()) {
                System.out.println("Processing flight ID: " + rs.getInt("id"));
                vols.add(mapResultSetToVol(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }

    // Méthode pour mettre à jour un vol
    public boolean updateVol(Vol vol) {
        String sql = "UPDATE vols SET vol_number = ?, departure_airport = ?, arrival_airport = ?, " +
                     "departure_time = ?, arrival_time = ?, status = ?, aircraft = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vol.getVolNumber());
            stmt.setString(2, vol.getDepartureAirport());
            stmt.setString(3, vol.getArrivalAirport());
            stmt.setTimestamp(4, Timestamp.valueOf(vol.getDepartureTime()));
            stmt.setTimestamp(5, Timestamp.valueOf(vol.getArrivalTime()));
            stmt.setString(6, vol.getStatus());
            stmt.setString(7, vol.getAircraft());
            stmt.setInt(8, vol.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un vol
    public boolean deleteVol(int id) {
        String sql = "DELETE FROM vols WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mapping ResultSet -> Vol
    private Vol mapResultSetToVol(ResultSet rs) throws SQLException {
        return new Vol(
            rs.getInt("id"),
            rs.getString("vol_number"),
            rs.getString("departure_airport"),
            rs.getString("arrival_airport"),
            rs.getTimestamp("departure_time").toLocalDateTime(),
            rs.getTimestamp("arrival_time").toLocalDateTime(),
            rs.getString("status"),
            rs.getString("aircraft")
        );
    }
}
