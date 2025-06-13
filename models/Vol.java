package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vol {
    private int idVol;
    private LocalDate dateDepart;
    private LocalDate dateArrive;
    private double distance;
    private String statut;
    private int carburantNecessaire;
    private String duree;
    private int idAvion;
    private CategorieVol codeTypeVol;
    private int codeAeroportDepart;
    private int codeAeroportArrive;

    public Vol(int idVol, String dateDepart, String dateArrive, double distance, String statut, int carburantNecessaire, String duree, int idAvion, CategorieVol codeTypeVol, int codeAeroportDepart, int codeAeroportArrive) {
        this.idVol = idVol;
        this.dateDepart = convertStringToDate(dateDepart);
        this.dateArrive = convertStringToDate(dateArrive);
        this.distance = distance;
        this.statut = statut;
        this.carburantNecessaire = carburantNecessaire;
        this.duree = duree;
        this.idAvion = idAvion;
        this.codeTypeVol = codeTypeVol;
        this.codeAeroportDepart = codeAeroportDepart;
        this.codeAeroportArrive = codeAeroportArrive;
    }

    private LocalDate convertStringToDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDate date = LocalDate.parse(strDate, formatter);

        return date;
    }

}
