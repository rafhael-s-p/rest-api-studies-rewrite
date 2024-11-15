package com.studies.domain.localization.state;

import com.studies.domain.validation.Error;
import com.studies.domain.validation.ValidationHandler;
import com.studies.domain.validation.Validator;

public class StateValidator extends Validator {

    private final State state;

    protected StateValidator(final State state, final ValidationHandler aHandler) {
        super(aHandler);
        this.state = state;
    }

    @Override
    public void validate() {
        if (this.state.getName() == null)
            this.validationHandler().append(new Error("'name' should not be null"));
    }
}
