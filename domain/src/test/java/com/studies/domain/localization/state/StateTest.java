package com.studies.domain.localization.state;

import com.studies.domain.exceptions.DomainException;
import com.studies.domain.utils.RandomStringGenerator;
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
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var currentState = State.newState(expectedName);
        final var currentException =
                Assertions.assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());

    }

    @Test
    void givenAnInvalidEmptyName_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                Assertions.assertThrows(DomainException.class, () -> currentState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNameLengthLesserThan3_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = "Ca";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                Assertions.assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNameLengthGreaterThan255_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = RandomStringGenerator.generateRandomString(257);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                Assertions.assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }
}
