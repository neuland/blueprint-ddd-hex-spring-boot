package de.neuland.blueprints.ddd.adapter.primary.http;

import de.neuland.blueprints.ddd.domain.model.warenkorb.Position;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
class WarenkorbDtoConverter {

    WarenkorbDto convert(Warenkorb warenkorb) {
        final List<Position> positionen = warenkorb.positionen();
        final List<PositionDto> positionDtos = positionen.stream().map(convert()).collect(Collectors.toList());
        return new WarenkorbDto(warenkorb.warenkorbId().value(), positionDtos);
    }

    private Function<Position, PositionDto> convert() {
        return position -> {
            final String artikelId = position.artikelId().value();
            final int anzahl = position.anzahl().value();
            return new PositionDto(artikelId, String.valueOf(anzahl));
        };
    }
}
