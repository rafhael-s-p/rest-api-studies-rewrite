package com.studies.domain.localization.state;

import com.studies.domain.exceptions.DomainException;
import com.studies.domain.validation.handle.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    void givenValidParams_whenCallNewState_thenInstantiateANewState() {
        final var expectedName = "California";

        final var currentState = State.newState(expectedName);

        Assertions.assertNotNull(currentState);
        Assertions.assertNotNull(currentState.getId());
        Assertions.assertEquals(expectedName, currentState.getName());
    }

    @Test
    void givenInvalidParams_whenCallNewStateAndValidate_thenShouldReceiveOneError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var currentState = State.newState(null);
        final var currentException =
                Assertions.assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());

    }
}
