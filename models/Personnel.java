package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personnel {

    private int idPersonnel;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private TypePersonnel codeType;

    public Personnel(int idPersonnel, String nom, String prenom, String dateNaissance, TypePersonnel codeType) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = convertStringToDate(dateNaissance);
        this.codeType = codeType;
    }

    private LocalDate convertStringToDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(strDate, formatter);

        return date;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }
    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
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
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public TypePersonnel getCodeType() {
        return codeType;
    }
    public void setCodeType(TypePersonnel codeType) {
        this.codeType = codeType;
    }
    @Override
    public String toString() {
        return "Personnel{" +
                "idPersonnel=" + idPersonnel +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", codeType=" + codeType +
                '}';
    }

}
