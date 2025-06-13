package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/airport_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Méthode statique pour obtenir la connexion
    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");
        try {
            // Chargement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
        }
        return null;

        
    }
}
