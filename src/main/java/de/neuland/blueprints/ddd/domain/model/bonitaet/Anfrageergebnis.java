package de.neuland.blueprints.ddd.domain.model.bonitaet;

public class Anfrageergebnis {

    private final String ergebnis;

    public Anfrageergebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }

    public String getErgebnis() {
        return ergebnis;
    }
}
