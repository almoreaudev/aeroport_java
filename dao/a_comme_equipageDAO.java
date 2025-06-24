package dao;

import models.Personnel;
import models.TypePersonnel;
import utils.DatabaseConnection;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.Timestamp;

public class a_comme_equipageDAO {
    

    // Récupérer l'équipage d'un vol
    public List<Personnel> getEquipageByVolId(int volId) {

        List<Personnel> equipage = new ArrayList<>();
        String sql = "SELECT p.*\n" + //
                        "FROM Personnel p\n" + //
                        "JOIN a_comme_equipage ace ON p.idPersonnel = ace.idPersonnel\n" + //
                        "WHERE ace.idVol = ?;\n" + //
                        "";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, volId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                equipage.add(mapResultSetToPersonnel(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipage;
    }

    private Personnel mapResultSetToPersonnel(ResultSet rs) throws SQLException {
        int idPersonnel = rs.getInt("idPersonnel");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        String dateNaissance = formatDate(rs.getTimestamp("dateNaissance"));
        TypePersonnel codeType = TypePersonnel.valueOf(rs.getString("codeType").toUpperCase());

        Personnel p = new Personnel(idPersonnel, nom, prenom, dateNaissance, codeType);
        return p;
    }

    private String formatDate(Timestamp timestamp) {
        LocalDate date = timestamp.toLocalDateTime().toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.atStartOfDay().format(formatter);
    }
}
