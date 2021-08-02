package de.neuland.blueprints.ddd.domain.model.bonitaet;

import de.neuland.blueprints.ddd.domain.model.ValueObject;


public class Zahlart extends ValueObject<String> {

    public Zahlart(String value) {
        super(value);
        if (value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
    }

}
