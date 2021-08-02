package de.neuland.blueprints.ddd.adapter.primary.http;

import de.neuland.blueprints.ddd.domain.model.warenkorb.Position;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
class WarenkorbDtoConverter {

    WarenkorbDto convert(Warenkorb warenkorb) {
        var positionen = warenkorb.positionen();
        var positionDtos = positionen.stream().map(convert()).collect(Collectors.toList());
        return new WarenkorbDto(warenkorb.warenkorbId().getValue(), positionDtos);
    }

    private Function<Position, PositionDto> convert() {
        return position -> {
            var artikelId = position.artikelId().getValue();
            var anzahl = position.anzahl().getValue();
            return new PositionDto(artikelId, String.valueOf(anzahl));
        };
    }
}
