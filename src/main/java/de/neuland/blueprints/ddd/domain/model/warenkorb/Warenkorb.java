package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.Identity;
import de.neuland.blueprints.ddd.domain.model.annotations.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Warenkorb {

    @Identity
    private final WarenkorbId warenkorbId;

    private List<Position> positionen = new ArrayList<>();

    private Gesamtsumme gesamtsumme;

    public Warenkorb() {
        this.warenkorbId = new WarenkorbId(UUID.randomUUID().toString().substring(0, 5));
    }

    public Position positionHinzufügen(ArtikelId artikelId, Anzahl anzahl) throws PositionHinzufügenException {
        if (artikelId == null || anzahl == null || anzahl.getValue() > 10)
            throw new PositionHinzufügenException();

        final PositionId positionId = new PositionId(UUID.randomUUID().toString().substring(0, 5));
        final Position position = new Position(positionId, artikelId, anzahl);
        positionen.add(position);
        return position;
    }

    public static Warenkorb fromDb(WarenkorbId warenkorbId) {
        return new Warenkorb(warenkorbId);
    }

    private Warenkorb(WarenkorbId warenkorbId) {
        this.warenkorbId = warenkorbId;
    }

    public WarenkorbId warenkorbId() {
        return warenkorbId;
    }

    public List<Position> positionen() {
        return positionen;
    }

    public Gesamtsumme gesamtsumme() {
        return gesamtsumme;
    }

}
