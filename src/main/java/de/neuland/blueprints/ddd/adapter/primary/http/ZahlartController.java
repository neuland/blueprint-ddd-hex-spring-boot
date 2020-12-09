package de.neuland.blueprints.ddd.adapter.primary.http;

import de.neuland.blueprints.ddd.application.ZahlartApplicationService;
import de.neuland.blueprints.ddd.application.ZahlartApplicationService.ZahlartWaehlenCommand;
import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class ZahlartController {

    private final ZahlartApplicationService applicationService;

    public ZahlartController(ZahlartApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PutMapping("/zahlart")
    public ResponseEntity waehleZahlart(String warenkorbId, String zahlartId) {
        final ZahlartWaehlenCommand command = new ZahlartWaehlenCommand(warenkorbId, zahlartId);
        ResponseEntity responseEntity;
        try {
            applicationService.waehleZahlart(command);
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

}
