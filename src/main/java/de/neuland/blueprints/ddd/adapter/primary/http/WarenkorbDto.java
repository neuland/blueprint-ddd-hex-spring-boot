package de.neuland.blueprints.ddd.adapter.primary.http;

import lombok.Value;

import java.util.List;

@Value
class WarenkorbDto {

    String warenkorbId;
    List<PositionDto> positionen;

}
