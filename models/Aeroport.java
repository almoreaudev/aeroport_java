package models;

public class Aeroport {

    private String codeAeroport;
    private String nom;
    private String ville;
    private String pays;

    public Aeroport(String codeAeroport, String nom, String ville, String pays) {
        this.codeAeroport = codeAeroport;
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
    }
}
