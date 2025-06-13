package models;

public enum TypePersonnel {

    PILOT("Pilote"),
    COPIL("Copilote"),
    CHEFCAB("Chef de cabine"),
    HOT("Hôtesse de l'air"),
    STEW("Steward");

    private final String type;

    TypePersonnel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
