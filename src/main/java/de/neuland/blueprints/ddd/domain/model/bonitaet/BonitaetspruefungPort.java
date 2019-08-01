package de.neuland.blueprints.ddd.domain.model.bonitaet;

import de.neuland.blueprints.ddd.domain.model.warenkorb.Gesamtsumme;

public interface BonitaetspruefungPort {

    Anfrageergebnis pruefeBonitaet(Gesamtsumme gesamtsumme, Zahlart zahlart);

}
