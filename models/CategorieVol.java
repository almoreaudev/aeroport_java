package models;

public enum CategorieVol {

    COURT("Courts-courriers"),
    MOYEN("Moyens-courriers"),
    LONG("Longs-courriers");

    private final String categorie;

    CategorieVol(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

}
