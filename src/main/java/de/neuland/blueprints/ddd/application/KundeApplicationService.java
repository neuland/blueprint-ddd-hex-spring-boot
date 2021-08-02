package de.neuland.blueprints.ddd.application;

import de.neuland.blueprints.ddd.domain.model.kunde.Kunde;
import de.neuland.blueprints.ddd.domain.model.kunde.KundeRepository;
import de.neuland.blueprints.ddd.domain.model.kunde.Kundennummer;
import org.springframework.stereotype.Service;

@Service
public class KundeApplicationService {

    private final KundeRepository kundeRepository;

    public KundeApplicationService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public void anlegen(KundeAnlegenCommand command) {
        var kundennummer = new Kundennummer(command.id);
        var name = command.name;
        var kunde = new Kunde(kundennummer, name);
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
