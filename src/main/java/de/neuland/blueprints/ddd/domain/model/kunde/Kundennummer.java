package de.neuland.blueprints.ddd.domain.model.kunde;


import de.neuland.blueprints.ddd.domain.model.ValueObject;

public class Kundennummer extends ValueObject<String> {

    public Kundennummer(String value) {
        super(value);
        if (value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
    }

}
