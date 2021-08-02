package de.neuland.blueprints.ddd.application;

import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Anzahl;
import de.neuland.blueprints.ddd.domain.model.warenkorb.ArtikelId;
import de.neuland.blueprints.ddd.domain.model.warenkorb.PositionHinzufügenException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbId;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarenkorbApplicationService {

    private final WarenkorbRepository warenkorbRepository;

    public WarenkorbApplicationService(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
    }

    public Optional<Warenkorb> zeigeWarenkorb(String warenkorbId) {
        return warenkorbRepository.findAsOption(new WarenkorbId(warenkorbId));
    }

    public void positionHinzufügen(PositionHinzufügenCommand command) throws EntityNotFoundException, PositionHinzufügenException {
        var anzahl = new Anzahl(command.anzahl);
        var artikelId = new ArtikelId(command.artikelId);
        var warenkorbId = new WarenkorbId(command.warenkorbId);

        var warenkorb = warenkorbRepository.find(warenkorbId);
        warenkorb.positionHinzufügen(artikelId, anzahl);
        warenkorbRepository.save(warenkorb);
    }

    public Warenkorb warenkorbErzeugen() {
        var warenkorb = new Warenkorb();
        warenkorbRepository.save(warenkorb);
        return warenkorb;
    }


    public static class PositionHinzufügenCommand {

        private final String anzahl;
        private final String artikelId;
        private final String warenkorbId;

        public PositionHinzufügenCommand(String anzahl, String artikelId, String warenkorbId) {
            this.warenkorbId = warenkorbId;
            this.artikelId = artikelId;
            this.anzahl = anzahl;
        }
    }

}
