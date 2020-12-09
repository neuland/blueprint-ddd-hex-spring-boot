package de.neuland.blueprints.ddd.domain.model.bonitaet;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class Zahlart {

    private final String value;

    public Zahlart(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zahlart that = (Zahlart) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}
