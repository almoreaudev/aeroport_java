package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Avion;
import utils.DataFormater;
import utils.DatabaseConnection;

import java.util.ArrayList;
import models.Vol;
import dao.VolDAO;


public class AvionDAO {

    public ArrayList<Avion> getAllAvions() {
        ArrayList<Avion> avions = new java.util.ArrayList<>();
        String query = "SELECT * FROM Avion";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                avions.add(mapResultSetToAvion(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avions;
    }

    public Avion getAvionById(int idAvion) {
        String query = "SELECT * FROM Avion WHERE idAvion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAvion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToAvion(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getCapacitePassagersById(int idAvion) {
        String query = "SELECT ta.capacitePassagers FROM TypeAvion ta JOIN Avion a ON ta.codeType = a.codeType WHERE a.idAvion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAvion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("capacitePassagers");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if no avion found or error occurs
    }

    public ArrayList<Vol> getVolsOfIdAvion(int idAvion) {
        ArrayList<Vol> vols = new ArrayList<>();
        String sql = "SELECT v.* FROM Vol v JOIN Avion a ON v.idAvion = a.idAvion WHERE a.idAvion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvion);
            ResultSet rs = stmt.executeQuery();

            VolDAO volDAO = new VolDAO();
            while (rs.next()) {
                vols.add(volDAO.mapResultSetToVol(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vols;
    }

    public ArrayList<Vol> getVolsByIdAvionForNextWeek(int idAvion) {
        ArrayList<Vol> vols = new ArrayList<>();
        String sql = "SELECT v.* FROM Vol v JOIN Avion a ON v.idAvion = a.idAvion WHERE a.idAvion = ? AND v.dateDepart >= NOW() AND v.dateDepart < NOW() + INTERVAL 7 DAY";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvion);
            ResultSet rs = stmt.executeQuery();

            VolDAO volDAO = new VolDAO();
            while (rs.next()) {
                vols.add(volDAO.mapResultSetToVol(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }

    // idAvion, dateEntretien, dateControleSecurite, codeType, capacitePassagers
    private Avion mapResultSetToAvion(ResultSet rs) throws SQLException {
        int idAvion = rs.getInt("idAvion");
        DataFormater dataFormater = new DataFormater();
        String dateEntretien = dataFormater.formatDate(rs.getTimestamp("dateEntretien"));
        String dateControleSecurite = dataFormater.formatDate(rs.getTimestamp("dateControleSecurite"));
        String codeType = rs.getString("codeType");
        int capacitePassagers = this.getCapacitePassagersById(idAvion);

        return new Avion(idAvion, dateEntretien, dateControleSecurite, codeType, capacitePassagers);
    }
    
}
