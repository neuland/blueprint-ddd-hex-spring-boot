package de.neuland_bfi.blueprints.ddd.application;

import de.neuland_bfi.blueprints.ddd.domain.model.kunde.Kunde;
import de.neuland_bfi.blueprints.ddd.domain.model.kunde.KundeRepository;
import de.neuland_bfi.blueprints.ddd.domain.model.kunde.Kundennummer;
import org.springframework.stereotype.Service;

@Service
public class KundeApplicationService {

    private KundeRepository kundeRepository;

    public KundeApplicationService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public void anlegen(KundeAnlegenCommand command) {
        final Kundennummer kundennummer = new Kundennummer(command.id);
        final String name = command.name;

        final Kunde kunde = new Kunde(kundennummer, name);
        kundeRepository.save(kunde);
    }

    public static class KundeAnlegenCommand {

        private String id;
        private String name;

        public KundeAnlegenCommand(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
