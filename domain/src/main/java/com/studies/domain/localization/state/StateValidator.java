package com.studies.domain.localization.state;

import com.studies.domain.validation.Error;
import com.studies.domain.validation.ValidationHandler;
import com.studies.domain.validation.Validator;

public class StateValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final State state;

    protected StateValidator(final State state, final ValidationHandler aHandler) {
        super(aHandler);
        this.state = state;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.state.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH)
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
    }
}
