package de.neuland.blueprints.ddd.adapter.secondary.persistence;

import de.neuland.blueprints.ddd.domain.model.bonitaet.Anfrageergebnis;
import de.neuland.blueprints.ddd.domain.model.bonitaet.BonitaetspruefungPort;
import de.neuland.blueprints.ddd.domain.model.bonitaet.Zahlart;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Gesamtsumme;
import org.springframework.stereotype.Component;

@Component
public class BonitaetspruefungAdapter implements BonitaetspruefungPort {
    @Override
    public Anfrageergebnis pruefeBonitaet(Gesamtsumme gesamtsumme, Zahlart zahlart) {
        // Hier z.B. externen Service anfragen
        return new Anfrageergebnis("erfolgreich");
    }
}
