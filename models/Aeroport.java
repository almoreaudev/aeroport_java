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

    public String getCodeAeroport() {
        return codeAeroport;
    }

    public void setCodeAeroport(String codeAeroport) {
        this.codeAeroport = codeAeroport;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "codeAeroport='" + codeAeroport + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
