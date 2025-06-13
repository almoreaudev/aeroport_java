package dao;

import utils.DatabaseConnection;

import models.Vol;
import models.CategorieVol;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class VolDAO {


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
        System.out.println("VolDAO.getAllVol() called");
        List<Vol> vols = new ArrayList<>();
        String sql = "SELECT * FROM vol";

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Fetching all flights from the database...");

            while (rs.next()) {
                System.out.println("Processing flight ID: " + rs.getInt("idVol"));
                vols.add(mapResultSetToVol(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }

   

    // Méthode pour supprimer un vol
    public boolean deleteVol(int id) {
        String sql = "DELETE FROM vol WHERE id = ?";

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
        int idVol = rs.getInt("idVol");
        String dateDepartStr = formatDate(rs.getTimestamp("dateDepart"));
        String dateArriveeStr = formatDate(rs.getTimestamp("dateArrivee"));
        double distance = rs.getDouble("distance");
        String statut = rs.getString("statut");
        int carburantNecessaire = rs.getInt("carburantNecessaire");
        String duree = rs.getString("duree");
        int idAvion = rs.getInt("idAvion");
        String codeTypeVolStr = rs.getString("codeCategorieVol");
        String codeAeroportDepart = rs.getString("codeAeroportDepart");
        String codeAeroportArrive = rs.getString("codeAeroportArrivee");

        CategorieVol codeTypeVol = CategorieVol.valueOf(codeTypeVolStr);
        // Vol(int idVol, String dateDepart, String dateArrivee, double distance, String statut, int carburantNecessaire, String duree, int idAvion, CategorieVol codeTypeVol, int codeAeroportDepart, int codeAeroportArrive)
        return new Vol(idVol, dateDepartStr, dateArriveeStr, distance, statut, carburantNecessaire, duree, idAvion, codeTypeVol, codeAeroportDepart, codeAeroportArrive);
    }

    private String formatDate(Timestamp timestamp) {
        LocalDate date = timestamp.toLocalDateTime().toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.atStartOfDay().format(formatter);
    }

}
