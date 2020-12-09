package de.neuland.blueprints.ddd.adapter.primary.http;

import de.neuland.blueprints.ddd.application.WarenkorbApplicationService;
import de.neuland.blueprints.ddd.application.WarenkorbApplicationService.PositionHinzufügenCommand;
import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.PositionHinzufügenException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class WarenkorbController {

    private final WarenkorbApplicationService applicationService;
    private final WarenkorbDtoConverter converter;

    public WarenkorbController(WarenkorbApplicationService applicationService, WarenkorbDtoConverter converter) {
        this.applicationService = applicationService;
        this.converter = converter;
    }

    @PostMapping("/warenkoerbe")
    public ResponseEntity warenkorbErzeugen() throws URISyntaxException {
        final Warenkorb warenkorb = applicationService.warenkorbErzeugen();
        HttpHeaders responseHeaders = new HttpHeaders();
        final String location = "/warenkorb/" + warenkorb.warenkorbId().value();
        responseHeaders.setLocation(new URI(location));
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

    }

    // curl -H "Content-Type: application/json;charset=UTF-8" http://localhost:8080/warenkorb/123 -i
    @GetMapping(value = "/warenkorb/{id}", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WarenkorbDto> warenkorb(@PathVariable("id") String warenkorbId) {
        final Optional<WarenkorbDto> maybeWarenkorbDto = applicationService.zeigeWarenkorb(warenkorbId).map(converter::convert);
        if (maybeWarenkorbDto.isPresent())
            return new ResponseEntity<>(maybeWarenkorbDto.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '{"artikelId":"xyz","anzahl":"1"}' http://localhost:8080/warenkorb/123/positionen -i
    @PostMapping(value = "/warenkorb/{id}/positionen", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity positionHinzufügen(@PathVariable("id") String warenkorbId, @RequestBody PositionHinzufügenDto dto) {
        PositionHinzufügenCommand command = new PositionHinzufügenCommand(dto.getAnzahl(), dto.getArtikelId(), warenkorbId);
        ResponseEntity responseEntity;
        try {
            applicationService.positionHinzufügen(command);
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (PositionHinzufügenException e) {
            responseEntity = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return responseEntity;
    }

}
