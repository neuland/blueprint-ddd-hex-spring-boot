package de.neuland.blueprints.ddd.domain.model.kunde;

import de.neuland.blueprints.ddd.domain.model.Identity;
import de.neuland.blueprints.ddd.domain.model.annotations.Entity;

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
