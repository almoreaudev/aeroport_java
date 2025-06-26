package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
