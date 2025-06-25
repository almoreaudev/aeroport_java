package models;

public class BilletAvion {
    
    private int idBillet;
    private int idUtilisateur;
    private int idVol;
    private String codeCategorie;
    private String prenom;
    private String nom;
    private double prix;

    public BilletAvion(int idBillet, int idUtilisateur, int idVol, String codeCategorie, String prenom, String nom, double prix) {
        this.idBillet = idBillet;
        this.idUtilisateur = idUtilisateur;
        this.idVol = idVol;
        this.codeCategorie = codeCategorie;
        this.prenom = prenom;
        this.nom = nom;
        this.prix = prix;
    }

    public int getIdBillet() {
        return idBillet;
    }
    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public int getIdVol() {
        return idVol;
    }
    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }
    public String getCodeCategorie() {
        return codeCategorie;
    }
    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "BilletAvion{" +
                "idBillet=" + idBillet +
                ", idUtilisateur=" + idUtilisateur +
                ", idVol=" + idVol +
                ", codeCategorie='" + codeCategorie + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }

}
