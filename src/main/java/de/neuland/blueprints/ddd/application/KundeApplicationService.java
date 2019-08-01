package de.neuland.blueprints.ddd.application;

import de.neuland.blueprints.ddd.domain.model.kunde.*;
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
