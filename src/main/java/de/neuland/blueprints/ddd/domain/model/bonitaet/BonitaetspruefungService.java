package de.neuland.blueprints.ddd.domain.model.bonitaet;

import de.neuland.blueprints.ddd.domain.model.annotations.DomainService;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;

@DomainService
public class BonitaetspruefungService {

    public Pruefungsergebnis pruefe(Anfrageergebnis anfrageergebnis, Warenkorb warenkorb) {
        return new Pruefungsergebnis("erfolgreich");
    }
}
