package com.studies.domain.localization.state;

import com.studies.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class StateID extends Identifier {

    private final String value;

    public StateID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static StateID unique() {
        return StateID.from(UUID.randomUUID());
    }

    public static StateID from(final String anId) {
        return new StateID(anId);
    }

    public static StateID from(final UUID anId) {
        return new StateID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StateID that = (StateID) o;

        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
