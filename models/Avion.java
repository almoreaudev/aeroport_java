package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import utils.DataFormater;

public class Avion {

    private int idAvion;
    private String dateEntretien;
    private String dateControleSecurite;
    private String typeAvion;
    private int capacitePassagers;

    public Avion(int idAvion, String dateEntretien, String dateControleSecurite, String typeAvion, int capacitePassagers) {
        this.idAvion = idAvion;
        this.dateEntretien = dateEntretien;
        this.dateControleSecurite = dateControleSecurite;
        this.typeAvion = typeAvion;
    }

    public boolean isControleEnRetard(){
        // Si le contrôle a été fait il y a plus de 6 mois, on retourne true
        return new DataFormater().isDateMoreThan(dateControleSecurite, 6);
    }

    public boolean isEntretienEnRetard(){
        // Si l'entretien a été fait il y a plus de 12 mois, on retourne true
        return new DataFormater().isDateMoreThan(dateEntretien, 12);
    }


    public int getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
    public String getDateEntretien() {
        return dateEntretien;
    }
    public void setDateEntretien(String dateEntretien) {
        this.dateEntretien = dateEntretien;
    }
    public String getDateControleSecurite() {
        return dateControleSecurite;
    }
    public void setDateControleSecurite(String dateControleSecurite) {
        this.dateControleSecurite = dateControleSecurite;
    }
    public String getTypeAvion() {
        return typeAvion;
    }
    public void setTypeAvion(String typeAvion) {
        this.typeAvion = typeAvion;
    }

    public int getCapacitePassagers() {
        return capacitePassagers;
    }

    public void setCapacitePassagers(int capacitePassagers) {
        this.capacitePassagers = capacitePassagers;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "idAvion=" + idAvion +
                ", dateEntretien=" + dateEntretien +
                ", dateControleSecurite=" + dateControleSecurite +
                ", typeAvion=" + typeAvion +
                ", capacitePassagers=" + capacitePassagers +
                '}';
    }

}
