package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Aeroport;
import utils.DatabaseConnection;

public class aeroportDAO {
    
    public Aeroport getAeroportByCode(String code) {
        String sql = "SELECT *\n" + //
                        "FROM Aeroport a\n" + //
                        "WHERE a.codeAeroport = ?;\n" + //
                        "";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToAeroportVol(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Aeroport> getAllAeroports() {
        ArrayList<Aeroport> aeroports = new ArrayList<>();
        String sql = "SELECT * FROM Aeroport;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aeroport a = mapResultSetToAeroportVol(rs);
                aeroports.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeroports;
    }



    private Aeroport mapResultSetToAeroportVol(ResultSet rs) throws SQLException {
        String idVol = rs.getString("codeAeroport");
        String nom = rs.getString("nom");
        String ville = rs.getString("ville");
        String pays = rs.getString("pays");

        Aeroport a = new Aeroport(idVol, nom, ville, pays);
        return a;
    }
}
