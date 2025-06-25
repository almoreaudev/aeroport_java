package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Avion;
import utils.DataFormater;
import utils.DatabaseConnection;

public class AvionDAO {

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
