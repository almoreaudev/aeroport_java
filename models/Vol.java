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

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDate dateArrive) {
        this.dateArrive = dateArrive;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getCarburantNecessaire() {
        return carburantNecessaire;
    }

    public void setCarburantNecessaire(int carburantNecessaire) {
        this.carburantNecessaire = carburantNecessaire;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public CategorieVol getCodeTypeVol() {
        return codeTypeVol;
    }

    public void setCodeTypeVol(CategorieVol codeTypeVol) {
        this.codeTypeVol = codeTypeVol;
    }

    public int getCodeAeroportDepart() {
        return codeAeroportDepart;
    }

    public void setCodeAeroportDepart(int codeAeroportDepart) {
        this.codeAeroportDepart = codeAeroportDepart;
    }

    public int getCodeAeroportArrive() {
        return codeAeroportArrive;
    }

    public void setCodeAeroportArrive(int codeAeroportArrive) {
        this.codeAeroportArrive = codeAeroportArrive;
    }

}
