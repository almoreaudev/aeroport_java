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

}
