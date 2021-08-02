package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

public class PositionId extends ValueObject<String> {

    public PositionId(String value) {
        super(value);
        if (value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
    }

}
