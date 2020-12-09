package de.neuland.blueprints.ddd.domain.model.kunde;

import de.neuland.blueprints.ddd.domain.model.Entity;
import de.neuland.blueprints.ddd.domain.model.Identity;

@Entity
public class Kunde {

    @Identity
    private final Kundennummer kundennummer;
    private final String name;

    public Kunde(Kundennummer kundennummer, String name) {
        this.kundennummer = kundennummer;
        this.name = name;
    }

    public Kundennummer kundennummer() {
        return kundennummer;
    }
}
