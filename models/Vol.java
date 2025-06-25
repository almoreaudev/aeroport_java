package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import utils.DataFormater;

public class Vol {
    private int idVol;
    private String dateDepart;
    private String dateArrivee;
    private double distance;
    private String statut;
    private int carburantNecessaire;
    private String duree;
    private int idAvion;
    private CategorieVol codeTypeVol;
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrive;
    private List<Personnel> equipage = new ArrayList<Personnel>();
    private List<Repas> repasList = new ArrayList<Repas>();
    private List<Aeroport> escales = new ArrayList<Aeroport>();
    private List<A_pour_escale> escalesDetails = new ArrayList<A_pour_escale>();

    public Vol() {
    }

    public Vol(int idVol, String dateDepart, String dateArrivee, double distance, String statut, int carburantNecessaire, String duree, int idAvion, CategorieVol codeTypeVol, List<Personnel> equipage, List<Repas> repasList) {
        this.idVol = idVol;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.distance = distance;
        this.statut = statut;
        this.carburantNecessaire = carburantNecessaire;
        this.duree = duree;
        this.idAvion = idAvion;
        this.codeTypeVol = codeTypeVol;
        this.equipage = equipage;
        this.repasList = repasList;
    }



    public void setEscales(List<Aeroport> escales) {
        this.escales = escales;
    }

    public List<Aeroport> getEscales() {
        return escales;
    }

    public void setAeroportDepart (Aeroport a){
        this.aeroportDepart = a;
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportArrive (Aeroport a){
        this.aeroportArrive = a;
    }

    public Aeroport getAeroportArrive(){
        return aeroportArrive;
    }


    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
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

    public List<A_pour_escale> getEscalesDetails() {
        return escalesDetails;
    }

    public void setEscalesDetails(List<A_pour_escale> escalesDetails) {
        this.escalesDetails = escalesDetails;
    }

    public double getDureeTotal(){
        DataFormater df = new DataFormater();
        double totalDuree = df.convertirEnMinutes(this.duree);
        for (A_pour_escale escale : escalesDetails) {
            
            double es = df.calculerDifferenceEnMinutes(escale.getDateDepartEscale(), escale.getDateArriveeEscale());
            totalDuree += es;
        }
        return totalDuree;
    }

    public String getDureeTotalString(){
        DataFormater df = new DataFormater();
        double totalDuree = df.convertirEnMinutes(this.duree);
        for (A_pour_escale escale : escalesDetails) {
            double es = df.calculerDifferenceEnMinutes(escale.getDateDepartEscale(), escale.getDateArriveeEscale());
            totalDuree += es;
        }
        return df.convertirMinutesEnHeure(totalDuree);
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
                ", aeroportDepart=" + aeroportDepart.toString() +
                ", aeroportArrive=" + aeroportArrive.toString() +
                ", equipage=" + equipage.toString() +
                ", repasList=" + repasList +
                ", escales=" + escales.toString() +
                ", escalesDetails=" + escalesDetails.toString() +
                '}';
    }

}
