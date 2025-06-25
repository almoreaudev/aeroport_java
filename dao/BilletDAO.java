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
