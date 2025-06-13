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

}
