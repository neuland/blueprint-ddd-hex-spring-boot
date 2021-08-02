package de.neuland.blueprints.ddd.application;

import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.bonitaet.BonitaetspruefungPort;
import de.neuland.blueprints.ddd.domain.model.bonitaet.BonitaetspruefungService;
import de.neuland.blueprints.ddd.domain.model.bonitaet.Pruefungsergebnis;
import de.neuland.blueprints.ddd.domain.model.bonitaet.Zahlart;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbId;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbRepository;
import org.springframework.stereotype.Service;

@Service
public class ZahlartApplicationService {

    private final BonitaetspruefungPort bonitaetspruefungPort;
    private final WarenkorbRepository warenkorbRepository;
    private final BonitaetspruefungService bonitaetspruefungService;

    public ZahlartApplicationService(BonitaetspruefungPort bonitaetspruefungPort, WarenkorbRepository warenkorbRepository, BonitaetspruefungService bonitaetspruefungService) {
        this.bonitaetspruefungPort = bonitaetspruefungPort;
        this.warenkorbRepository = warenkorbRepository;
        this.bonitaetspruefungService = bonitaetspruefungService;
    }

    public Pruefungsergebnis waehleZahlart(ZahlartWaehlenCommand command) throws EntityNotFoundException {
        var warenkorbId = new WarenkorbId(command.warenkorbId);
        var zahlart = new Zahlart(command.zahlartId);

        var warenkorb = warenkorbRepository.find(warenkorbId);
        var gesamtsumme = warenkorb.gesamtsumme();
        var anfrageergebnis = bonitaetspruefungPort.pruefeBonitaet(gesamtsumme, zahlart);
        warenkorbRepository.save(warenkorb);
        return bonitaetspruefungService.pruefe(anfrageergebnis, warenkorb);
    }

    public static class ZahlartWaehlenCommand {

        private final String warenkorbId;
        private final String zahlartId;

        public ZahlartWaehlenCommand(String warenkorbId, String zahlartId) {
            this.warenkorbId = warenkorbId;
            this.zahlartId = zahlartId;
        }
    }

}
