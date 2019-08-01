package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.Entity;

@Entity
public class Position {

    private final PositionId positionId;
    private final ArtikelId artikelId;
    private final Anzahl anzahl;

    Position(PositionId positionId, ArtikelId artikelId, Anzahl anzahl) {
        this.positionId = positionId;
        this.artikelId = artikelId;
        this.anzahl = anzahl;
    }

    public ArtikelId artikelId() {
        return artikelId;
    }

    public Anzahl anzahl() {
        return anzahl;
    }
}
