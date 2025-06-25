package dao;

import models.Utilisateur;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {

    // Vérifie si l'utilisateur existe (connexion)
    public Utilisateur verifierConnexion(String email, String motDePasse) {
        String sql = "SELECT * FROM Utilisateur WHERE email = ? AND motDePasse = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, motDePasse);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Aucun utilisateur trouvé
    }

    public boolean creerUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, motDePasse, adresse, numPasseport, numCarteIdentite) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMotDePasse());
            stmt.setString(5, utilisateur.getAdresse());
            stmt.setString(6, utilisateur.getNumPasseport());
            stmt.setString(7, utilisateur.getNumCarteIdentite());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // true si insertion réussie

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        String sql = "SELECT * FROM Utilisateur WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Aucun utilisateur trouvé
    }

    // Mapping entre le ResultSet et l'objet Utilisateur
    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("motDePasse"));
        utilisateur.setAdresse(rs.getString("adresse"));
        utilisateur.setNumPasseport(rs.getString("numPasseport"));
        utilisateur.setNumCarteIdentite(rs.getString("numCarteIdentite"));
        return utilisateur;
    }
}
