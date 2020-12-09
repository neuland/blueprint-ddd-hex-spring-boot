package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class PositionId {

    private String value;

    public PositionId(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
        this.value = value;
    }

    public String value() {
        return value;
    }
}
