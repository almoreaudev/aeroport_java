package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import models.CarteFidelite;
import utils.DatabaseConnection;
import models.Utilisateur;
import utils.DataFormater;


public class CarteFideliteDAO {
    
    public CarteFidelite getCarteFideliteByIdUtilisateur(int idUser) {
        String sql = "SELECT * FROM CarteFidelite WHERE idUtilisateur = ?;";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCarteFidelite(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CarteFidelite mapResultSetToCarteFidelite(ResultSet rs) throws SQLException {
        int idCarte = rs.getInt("idCarte");
        int idUser = rs.getInt("idUtilisateur");
        int points = rs.getInt("totalMiles");
        DataFormater dataFormater = new DataFormater();
        String dateInscription = dataFormater.formatDate(rs.getTimestamp("dateInscription"));
        
        
        CarteFidelite carteFidelite = new CarteFidelite(idCarte, idUser, points, dateInscription);
        
        return carteFidelite;
    }
}
