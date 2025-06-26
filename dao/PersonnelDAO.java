package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import models.Personnel;
import models.TypePersonnel;
import utils.DatabaseConnection;
import utils.DataFormater;


public class PersonnelDAO {
    
    public ArrayList<Personnel> getAllPersonnels() {
        ArrayList<Personnel> personnelList = new ArrayList<>();
        String query = "SELECT * FROM Personnel";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                personnelList.add(mapResultSetToPersonnel(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnelList;
    }

    public void addPersonnel(Personnel personnel) {
        String query = "INSERT INTO Personnel (nom, prenom, dateNaissance, codeType) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, personnel.getNom());
            stmt.setString(2, personnel.getPrenom());

            DataFormater dataFormater = new DataFormater();
            // Assure-toi que personnel.getDateNaissance() est au format "yyyy-MM-dd HH:mm"
            stmt.setTimestamp(3, dataFormater.simpleParseDate(personnel.getDateNaissance()));

            stmt.setString(4, personnel.getCodeType().name());

            stmt.executeUpdate();

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
}

    }

    public Personnel mapResultSetToPersonnel(ResultSet rs) throws SQLException {
        int idPersonnel = rs.getInt("idPersonnel");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        DataFormater dataFormater = new DataFormater();
        String dateEmbauche = dataFormater.formatDate(rs.getTimestamp("dateNaissance"));

        TypePersonnel tp = TypePersonnel.valueOf(rs.getString("codeType").toUpperCase());

        Personnel personnel = new Personnel(idPersonnel, nom, prenom, dateEmbauche, tp);
        return personnel;
    }
}
