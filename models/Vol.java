package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Vol {
    private int idVol;
    private LocalDate dateDepart;
    private LocalDate dateArrivee;
    private double distance;
    private String statut;
    private int carburantNecessaire;
    private String duree;
    private int idAvion;
    private CategorieVol codeTypeVol;
    private String codeAeroportDepart;
    private String codeAeroportArrive;
    private List<Personnel> equipage = new ArrayList<Personnel>();
    private List<Repas> repasList = new ArrayList<Repas>();

    public Vol(int idVol, String dateDepart, String dateArrivee, double distance, String statut, int carburantNecessaire, String duree, int idAvion, CategorieVol codeTypeVol, String codeAeroportDepart, String codeAeroportArrive, List<Personnel> equipage, List<Repas> repasList) {
        this.idVol = idVol;
        this.dateDepart = convertStringToDate(dateDepart);
        this.dateArrivee = convertStringToDate(dateArrivee);
        this.distance = distance;
        this.statut = statut;
        this.carburantNecessaire = carburantNecessaire;
        this.duree = duree;
        this.idAvion = idAvion;
        this.codeTypeVol = codeTypeVol;
        this.codeAeroportDepart = codeAeroportDepart;
        this.codeAeroportArrive = codeAeroportArrive;
        this.equipage = equipage;
        this.repasList = repasList;
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

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
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

    public String getCodeAeroportDepart() {
        return codeAeroportDepart;
    }

    public void setCodeAeroportDepart(String codeAeroportDepart) {
        this.codeAeroportDepart = codeAeroportDepart;
    }

    public String getCodeAeroportArrive() {
        return codeAeroportArrive;
    }

    public void setCodeAeroportArrive(String codeAeroportArrive) {
        this.codeAeroportArrive = codeAeroportArrive;
    }

    public List<Personnel> getEquipage() {
        return equipage;
    }

    public void setEquipage(List<Personnel> equipage) {
        this.equipage = equipage;
    }

    public List<Repas> getRepasList() {
        return repasList;
    }

    public void setRepasList(List<Repas> repasList) {
        this.repasList = repasList;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "idVol=" + idVol +
                ", dateDepart=" + dateDepart +
                ", dateArrivee=" + dateArrivee +
                ", distance=" + distance +
                ", statut='" + statut + '\'' +
                ", carburantNecessaire=" + carburantNecessaire +
                ", duree='" + duree + '\'' +
                ", idAvion=" + idAvion +
                ", codeTypeVol=" + codeTypeVol +
                ", codeAeroportDepart='" + codeAeroportDepart + '\'' +
                ", codeAeroportArrive='" + codeAeroportArrive + '\'' +
                ", equipage=" + equipage +
                ", repasList=" + repasList +
                '}';
    }

}
