package models;

import models.CodeCategorie;

public class Categoriepassager {


    private CodeCategorie codeCategorie;
    private String libelle;
    private double tarif;

    public Categoriepassager(CodeCategorie codeCategorie, String libelle, double tarif) {
        this.codeCategorie = codeCategorie;
        this.libelle = libelle;
        this.tarif = tarif;
    }

    public CodeCategorie getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(CodeCategorie codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
}
