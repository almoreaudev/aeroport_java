package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Avion {

    private int idAvion;
    private LocalDate dateEntretien;
    private LocalDate dateControleSecurite;
    private TypeAvion typeAvion;

    public Avion(int idAvion, String dateEntretien, String dateControleSecurite, TypeAvion typeAvion) {
        this.idAvion = idAvion;
        this.dateEntretien = convertStringToDate(dateEntretien);
        this.dateControleSecurite = convertStringToDate(dateControleSecurite);
    }

    private LocalDate convertStringToDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(strDate, formatter);

        return date;
    }

    public int getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
    public LocalDate getDateEntretien() {
        return dateEntretien;
    }
    public void setDateEntretien(LocalDate dateEntretien) {
        this.dateEntretien = dateEntretien;
    }
    public LocalDate getDateControleSecurite() {
        return dateControleSecurite;
    }
    public void setDateControleSecurite(LocalDate dateControleSecurite) {
        this.dateControleSecurite = dateControleSecurite;
    }
    public TypeAvion getTypeAvion() {
        return typeAvion;
    }
    public void setTypeAvion(TypeAvion typeAvion) {
        this.typeAvion = typeAvion;
    }
    @Override
    public String toString() {
        return "Avion{" +
                "idAvion=" + idAvion +
                ", dateEntretien=" + dateEntretien +
                ", dateControleSecurite=" + dateControleSecurite +
                ", typeAvion=" + typeAvion +
                '}';
    }

}
