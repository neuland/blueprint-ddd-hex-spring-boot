package de.neuland.blueprints.ddd.adapter.primary.http;

import de.neuland.blueprints.ddd.application.WarenkorbApplicationService;
import de.neuland.blueprints.ddd.application.WarenkorbApplicationService.PositionHinzufügenCommand;
import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.PositionHinzufügenException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

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
        var warenkorb = applicationService.warenkorbErzeugen();
        var responseHeaders = new HttpHeaders();
        var location = "/warenkorb/" + warenkorb.warenkorbId().getValue();
        responseHeaders.setLocation(new URI(location));
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

    }

    // curl -H "Content-Type: application/json;charset=UTF-8" http://localhost:8080/warenkorb/123 -i
    @GetMapping(value = "/warenkorb/{id}", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WarenkorbDto> warenkorb(@PathVariable("id") String warenkorbId) {
        var maybeWarenkorbDto = applicationService.zeigeWarenkorb(warenkorbId).map(converter::convert);
        return maybeWarenkorbDto.map(warenkorbDto -> new ResponseEntity<>(warenkorbDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '{"artikelId":"xyz","anzahl":"1"}' http://localhost:8080/warenkorb/123/positionen -i
    @PostMapping(value = "/warenkorb/{id}/positionen", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity positionHinzufügen(@PathVariable("id") String warenkorbId, @RequestBody PositionHinzufügenDto dto) {
        var command = new PositionHinzufügenCommand(dto.getAnzahl(), dto.getArtikelId(), warenkorbId);
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
