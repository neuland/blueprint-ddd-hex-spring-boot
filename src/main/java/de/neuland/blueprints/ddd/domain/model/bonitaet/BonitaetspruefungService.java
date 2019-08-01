package de.neuland.blueprints.ddd.domain.model.bonitaet;

import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

@Service
public class BonitaetspruefungService {

    public Pruefungsergebnis pruefe(Anfrageergebnis anfrageergebnis, Warenkorb warenkorb) {
        return new Pruefungsergebnis("erfolgreich");
    }
}
