package dao;

import utils.DatabaseConnection;
import utils.DataFormater;

import models.Vol;
import models.A_pour_escale;
import models.Aeroport;
import models.CategorieVol;
import models.Repas;

import models.Personnel;
import dao.a_comme_equipageDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;


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
                Vol vol = mapResultSetToVol(rs);
                vols.add(vol);
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
        DataFormater dataFormater = new DataFormater();
        String dateDepartStr = dataFormater.formatDate(rs.getTimestamp("dateDepart"));
        String dateArriveeStr = dataFormater.formatDate(rs.getTimestamp("dateArrivee"));
        double distance = rs.getDouble("distance");
        String statut = rs.getString("statut");
        int carburantNecessaire = rs.getInt("carburantNecessaire");
        String duree = rs.getString("duree");
        int idAvion = rs.getInt("idAvion");
        String codeTypeVolStr = rs.getString("codeCategorieVol");
        String codeAeroportDepart = rs.getString("codeAeroportDepart");
        String codeAeroportArrive = rs.getString("codeAeroportArrivee");

        CategorieVol codeTypeVol = CategorieVol.valueOf(codeTypeVolStr);
        List<Personnel> equipage = new a_comme_equipageDAO().getEquipageByVolId(idVol);

        Vol vol = new Vol(idVol, dateDepartStr, dateArriveeStr, distance, statut, carburantNecessaire, duree, idAvion, codeTypeVol, equipage, new ArrayList<>());
        
        // Récupération des repas associés au vol
        List<Repas> repasList = new RepasDAO().getRepasByIdVol(idVol);
        vol.setRepasList(repasList);

        Aeroport ad = new aeroportDAO().getAeroportByCode(codeAeroportDepart);
        vol.setAeroportDepart(ad);
        Aeroport aa = new aeroportDAO().getAeroportByCode(codeAeroportArrive);
        vol.setAeroportArrive(aa);
        
        List<A_pour_escale> escalesDetails = new a_pour_escaleDAO().getA_pour_escaleByIdVol(idVol);
        vol.setEscalesDetails(escalesDetails);

        List<Aeroport> escales = new a_pour_escaleDAO().getEscalesByIdVol(idVol);
        vol.setEscales(escales);

        return vol;
    }


}
