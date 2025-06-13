package models;

public class Repas {

    private int idRepas;
    private TypeRepas typeRepas;

    public Repas(int idRepas, TypeRepas typeRepas) {
        this.idRepas = idRepas;
        this.typeRepas = typeRepas;
    }

    public int getIdRepas() {
        return idRepas;
    }
    public void setIdRepas(int idRepas) {
        this.idRepas = idRepas;
    }
    public TypeRepas getTypeRepas() {
        return typeRepas;
    }
    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }
    @Override
    public String toString() {
        return "Repas{" +
                "idRepas=" + idRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }
}
