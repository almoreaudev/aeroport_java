package dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.A_pour_escale;
import models.Aeroport;
import utils.DataFormater;
import utils.DatabaseConnection;

public class a_pour_escaleDAO {

    // public List<Aeroport> getEscalesByIdVol(int idVol) {
    public  List<Aeroport> getEscalesByIdVol(int idVol) {
        List<Aeroport> escales = new ArrayList<>();
        String sql = "SELECT a.*\n" + 
                     "FROM Aeroport a\n" + 
                     "JOIN a_pour_escale aes ON a.codeAeroport = aes.codeAeroport\n" + 
                     "WHERE aes.idVol = ?;\n";

        try (Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVol);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                escales.add(mapResultSetToAeroport(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return escales;
    }

    public List<A_pour_escale> getA_pour_escaleByIdVol(int idVol) {
        String sql = "SELECT * FROM a_pour_escale WHERE idVol = ?;";

        List<A_pour_escale> aEscaleList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVol);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                aEscaleList.add(mapResultSetToA_pour_escale(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aEscaleList;
    }

    private A_pour_escale mapResultSetToA_pour_escale(ResultSet rs) throws SQLException {
        int idVol = rs.getInt("idVol");
        String codeAeroport = rs.getString("codeAeroport");
        DataFormater dataFormater = new DataFormater();
        String dateHeureArrivee = dataFormater.formatDate(rs.getTimestamp("dateArriveeEscale"));
        String dateHeureDepart = dataFormater.formatDate(rs.getTimestamp("dateDepartEscale"));

        A_pour_escale aEscale = new A_pour_escale(idVol, codeAeroport, dateHeureArrivee, dateHeureDepart);
        return aEscale;
    }

    private Aeroport mapResultSetToAeroport(ResultSet rs) throws SQLException {
        String codeAeroport = rs.getString("codeAeroport");
        String nom = rs.getString("nom");
        String ville = rs.getString("ville");
        String pays = rs.getString("pays");

        Aeroport a = new Aeroport(codeAeroport, nom, ville, pays);
        return a;
    }
    
}
