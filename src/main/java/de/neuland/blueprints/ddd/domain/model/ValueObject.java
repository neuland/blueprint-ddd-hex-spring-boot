package de.neuland.blueprints.ddd.domain.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class ValueObject<T> {

    protected T value;

    protected ValueObject(T value) {
        if (value == null)
            throw new IllegalArgumentException(String.format("'%s.value' must not be null!", getClass().getSimpleName()));
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), value);
    }

    public static <T> T getValueOrNull(ValueObject<T> valueObject) {
        return getValueOrDefault(valueObject, null);
    }

    public static <T> T getValueOrDefault(ValueObject<T> valueObject, T defaultValue) {
        return Optional.ofNullable(valueObject).map(ValueObject::getValue).orElse(defaultValue);
    }

    public static <U, T extends ValueObject<U>> T orNull(U value, Function<U, T> mapper) {
        return value == null ? null : mapper.apply(value);
    }
}
