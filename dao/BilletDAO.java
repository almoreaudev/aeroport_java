package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import models.BilletAvion;
import utils.DatabaseConnection;

public class BilletDAO {
    
    public List<BilletAvion> getBilletsByIdUtilisateur(int idUtilisateur) {
        String sql = "SELECT * FROM billet WHERE idUtilisateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUtilisateur);
            ResultSet rs = stmt.executeQuery();

            List<BilletAvion> billets = new ArrayList<>();
            while (rs.next()) {

                billets.add(mapResultSetToBillet(rs));
            }
            return billets; // Return the list of billets found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list if no billets found or in case of an error
    }

    public boolean addBillet(BilletAvion billet) {
        String sql = "INSERT INTO billet (idUtilisateur, idVol, codeCategorie, prenom, nom, prix) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billet.getIdUtilisateur());
            stmt.setInt(2, billet.getIdVol());
            stmt.setString(3, billet.getCodeCategorie());
            stmt.setString(4, billet.getPrenom());
            stmt.setString(5, billet.getNom());
            stmt.setDouble(6, billet.getPrix());

            stmt.executeUpdate();

            return true; // Return true if the insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred during insertion
        }

    }

    // vol.statut = "A venir" || vol.statut = "En cours"
    // idUtilisateur = idUtilisateur
    public List<BilletAvion> getBilletsByIdUtilisateurAndStatut(int idUtilisateur, String statut) {
        String sql = "SELECT b.* FROM billet b JOIN vol v ON b.idVol = v.idVol WHERE b.idUtilisateur = ? AND v.statut = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUtilisateur);
            stmt.setString(2, statut);
            ResultSet rs = stmt.executeQuery();

            List<BilletAvion> billets = new ArrayList<>();
            while (rs.next()) {
                billets.add(mapResultSetToBillet(rs));
            }
            return billets; // Return the list of billets found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list if no billets found or in case of an error
    }


    public List<BilletAvion> getBilletsByIdVol(int idVol) {
        String sql = "SELECT * FROM billet WHERE idVol = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVol);
            ResultSet rs = stmt.executeQuery();

            List<BilletAvion> billets = new ArrayList<>();
            while (rs.next()) {
                billets.add(mapResultSetToBillet(rs));
            }
            return billets; // Return the list of billets found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list if no billets found or in case
        // of an error
    }

    private BilletAvion mapResultSetToBillet(ResultSet rs) throws SQLException {
        int idBillet = rs.getInt("idBillet");
        int idUtilisateur = rs.getInt("idUtilisateur");
        int idVol = rs.getInt("idVol");
        String codeCategorie = rs.getString("codeCategorie");
        String prenom = rs.getString("prenom");
        String nom = rs.getString("nom");
        double prix = rs.getDouble("prix");

        return new BilletAvion(idBillet, idUtilisateur, idVol, codeCategorie, prenom, nom, prix);
    }
}
