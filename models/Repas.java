package models;

public class Repas {

    private int idRepas;
    private String typeRepas;
    private int idVol;

    public Repas(int idRepas, String typeRepas, int idVol) {
        this.idRepas = idRepas;
        this.typeRepas = typeRepas;
        this.idVol = idVol;
    }

    public int getIdRepas() {
        return idRepas;
    }
    public void setIdRepas(int idRepas) {
        this.idRepas = idRepas;
    }
    public String getTypeRepas() {
        return typeRepas;
    }
    public void setTypeRepas(String typeRepas) {
        this.typeRepas = typeRepas;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }


    @Override
    public String toString() {
        return "Repas{" +
                "idRepas=" + idRepas +
                ", typeRepas=" + typeRepas +
                ", idVol=" + idVol +
                '}';
    }
}
