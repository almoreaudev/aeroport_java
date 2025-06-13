package models;

public enum TypeRepas {

    SNACK("Snack"),
    PETIT_DEJ("Petit déjeuner"),
    DEJEUNER("Déjeuner"),
    DINER("Dîner");

    private final String type;

    TypeRepas(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
