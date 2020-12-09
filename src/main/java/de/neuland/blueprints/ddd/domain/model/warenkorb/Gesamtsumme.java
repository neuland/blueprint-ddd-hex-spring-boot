package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

import java.util.Objects;

@ValueObject
public class Gesamtsumme {

    private final int value;

    public Gesamtsumme(int value) {
        if (value > 0)
            throw new IllegalArgumentException("'value' must not be zero");
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gesamtsumme that = (Gesamtsumme) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
