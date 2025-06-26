package models;

/*
 * 
 * -- CarteFidelite
CREATE TABLE IF NOT EXISTS CarteFidelite (
    idCarte INT AUTO_INCREMENT PRIMARY KEY,
    idUtilisateur INT NOT NULL,
    totalMiles INT NOT NULL DEFAULT 0,
    dateInscription DATETIME NOT NULL,
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
);

 */

public class CarteFidelite {
    private int idCarte;
    private int idUtilisateur;
    private int totalMiles;
    private String dateInscription;
    public CarteFidelite(int idCarte, int idUtilisateur, int totalMiles, String dateInscription) {
        this.idCarte = idCarte;
        this.idUtilisateur = idUtilisateur;
        this.totalMiles = totalMiles;
        this.dateInscription = dateInscription;
    }

    public int getIdCarte() {
        return idCarte;
    }
    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public int getTotalMiles() {
        return totalMiles;
    }
    public void setTotalMiles(int totalMiles) {
        this.totalMiles = totalMiles;
    }
    public String getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }
    @Override
    public String toString() {
        return "CarteFidelite{" +
                "idCarte=" + idCarte +
                ", idUtilisateur=" + idUtilisateur +
                ", totalMiles=" + totalMiles +
                ", dateInscription='" + dateInscription + '\'' +
                '}';
    }
}
