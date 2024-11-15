package com.studies.domain.localization.state;

import com.studies.domain.AggregateRoot;
import com.studies.domain.validation.ValidationHandler;

public class State extends AggregateRoot<StateID> {

    private String name;

    public State(final StateID anId,
                 final String aName) {
        super(anId);
        this.name = aName;
    }

    public static State newState(final String aName) {
        final var id = StateID.unique();
        return new State(id, aName);
    }

    public StateID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State update(final String anUpdatedName) {
        this.name = anUpdatedName;
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new StateValidator(this, handler).validate();
    }
}
