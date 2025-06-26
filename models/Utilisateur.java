package models;

public class Utilisateur {
    
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String adresse;
    private String numPasseport;
    private String numCarteIdentite;
    private boolean superUtilisateur;

    public Utilisateur() {
        // Constructeur par défaut
    }

    public Utilisateur(int idUtilisateur, String nom, String prenom, String email, String motDePasse, String adresse, String numPasseport, String numCarteIdentite, boolean superUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.numPasseport = numPasseport;
        this.numCarteIdentite = numCarteIdentite;
        this.superUtilisateur = superUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumPasseport() {
        return numPasseport;
    }

    public void setNumPasseport(String numPasseport) {
        this.numPasseport = numPasseport;
    }

    public String getNumCarteIdentite() {
        return numCarteIdentite;
    }

    public void setNumCarteIdentite(String numCarteIdentite) {
        this.numCarteIdentite = numCarteIdentite;
    }

    public boolean isSuperUtilisateur() {
        return superUtilisateur;
    }

    public void setSuperUtilisateur(boolean superUtilisateur) {
        this.superUtilisateur = superUtilisateur;
    }



    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numPasseport='" + numPasseport + '\'' +
                ", numCarteIdentite='" + numCarteIdentite + '\'' +
                ", superUtilisateur=" + superUtilisateur +
                '}';
    }
}
