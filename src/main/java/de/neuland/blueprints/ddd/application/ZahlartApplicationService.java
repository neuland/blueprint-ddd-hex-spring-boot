package de.neuland.blueprints.ddd.application;

import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.bonitaet.*;
import de.neuland.blueprints.ddd.domain.model.warenkorb.*;
import org.springframework.stereotype.Service;

@Service
public class ZahlartApplicationService {

    private BonitaetspruefungPort bonitaetspruefungPort;
    private WarenkorbRepository warenkorbRepository;
    private BonitaetspruefungService bonitaetspruefungService;

    public ZahlartApplicationService(BonitaetspruefungPort bonitaetspruefungPort, WarenkorbRepository warenkorbRepository, BonitaetspruefungService bonitaetspruefungService) {
        this.bonitaetspruefungPort = bonitaetspruefungPort;
        this.warenkorbRepository = warenkorbRepository;
        this.bonitaetspruefungService = bonitaetspruefungService;
    }

    public Pruefungsergebnis waehleZahlart(ZahlartWaehlenCommand command) throws EntityNotFoundException {
        final WarenkorbId warenkorbId = new WarenkorbId(command.warenkorbId);
        final Zahlart zahlart = new Zahlart(command.zahlartId);

        final Warenkorb warenkorb = warenkorbRepository.find(warenkorbId);
        final Gesamtsumme gesamtsumme = warenkorb.gesamtsumme();
        final Anfrageergebnis anfrageergebnis = bonitaetspruefungPort.pruefeBonitaet(gesamtsumme, zahlart);
        warenkorbRepository.save(warenkorb);
        final Pruefungsergebnis pruefungsergebnis = bonitaetspruefungService.pruefe(anfrageergebnis, warenkorb);
        return pruefungsergebnis;
    }

    public static class ZahlartWaehlenCommand {

        private String warenkorbId;
        private final String zahlartId;

        public ZahlartWaehlenCommand(String warenkorbId, String zahlartId) {
            this.warenkorbId = warenkorbId;
            this.zahlartId = zahlartId;
        }
    }

}
