


package models;

import java.time.LocalDateTime;

public class A_pour_escale {

    private int idVol;
    private String codeAeroport;

    private String dateArriveeEscale;
    private String dateDepartEscale;


    public A_pour_escale() {
    }

    public A_pour_escale(int idVol, String codeAeroport, String dateArriveeEscale, String dateDepartEscale) {
        this.idVol = idVol;
        this.codeAeroport = codeAeroport;
        this.dateArriveeEscale = dateArriveeEscale;
        this.dateDepartEscale = dateDepartEscale;
    }
    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getCodeAeroport() {
        return codeAeroport;
    }

    public void setCodeAeroport(String codeAeroport) {
        this.codeAeroport = codeAeroport;
    }

    public String getDateArriveeEscale() {
        return dateArriveeEscale;
    }

    public void setDateArriveeEscale(String dateArriveeEscale) {
        this.dateArriveeEscale = dateArriveeEscale;
    }

    public String getDateDepartEscale() {
        return dateDepartEscale;
    }

    public void setDateDepartEscale(String dateDepartEscale) {
        this.dateDepartEscale = dateDepartEscale;
    }

    @Override
    public String toString() {
        return "A_pour_escale{" +
                "idVol=" + idVol +
                ", codeAeroport='" + codeAeroport + '\'' +
                ", dateArriveeEscale='" + dateArriveeEscale + '\'' +
                ", dateDepartEscale='" + dateDepartEscale + '\'' +
                '}';
    }
}