package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Categoriepassager;
import models.CodeCategorie;
import utils.DatabaseConnection;

public class CategoriepassagerDAO {
    
    public Categoriepassager getCategoriePassagerByCode(String code) {
        String sql = "SELECT * FROM Categoriepassager WHERE codeCategorie = ?;";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCategoriepassager(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Categoriepassager mapResultSetToCategoriepassager(ResultSet rs) throws SQLException {
        String codeCategorie = rs.getString("codeCategorie");
        CodeCategorie codeCat = CodeCategorie.valueOf(codeCategorie.toUpperCase());
        String libelle = rs.getString("libelle");
        Double tarif = rs.getDouble("tarif");
        
        Categoriepassager categorie = new Categoriepassager(codeCat, libelle, tarif);
        return categorie;
    }
}
