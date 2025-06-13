package models;

public enum TypeAvion {

    A320(180),
    B737(160),
    B777(550),
    A380(850);

    private final int capacite;

    TypeAvion(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    @Override
    public String toString() {
        return "TypeAvion{" +
                "capacite=" + capacite +
                '}';
    }
}
