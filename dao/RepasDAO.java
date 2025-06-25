package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import models.Repas;
import utils.DatabaseConnection;

public class RepasDAO {
    
    public List<Repas> getRepasByIdVol(int idVol) {
        List<Repas> repasList = new ArrayList<>();
        String sql = "SELECT * FROM Repas WHERE idVol = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVol);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                repasList.add(mapResultSetToRepas(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repasList;
    }

    //idRepas, codeType, idVol
    private Repas mapResultSetToRepas(ResultSet rs) throws SQLException {
        int idRepas = rs.getInt("idRepas");
        String codeType = rs.getString("codeType");
        int idVol = rs.getInt("idVol");

        Repas repas = new Repas(idRepas, codeType, idVol);
        return repas;
    }
    
}
