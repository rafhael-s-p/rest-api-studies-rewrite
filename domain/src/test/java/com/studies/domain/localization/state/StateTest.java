package com.studies.domain.localization.state;

import com.studies.domain.exceptions.DomainException;
import com.studies.domain.utils.RandomStringGenerator;
import com.studies.domain.validation.handle.ThrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    @Test
    void givenValidParams_whenCallNewState_thenInstantiateANewState() {
        final var expectedName = "California";

        final var currentState = State.newState(expectedName);

        assertNotNull(currentState);
        assertNotNull(currentState.getId());
        assertEquals(expectedName, currentState.getName());
    }

    @Test
    void givenInvalidParams_whenCallNewStateAndValidate_thenShouldReceiveOneError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var currentState = State.newState(expectedName);
        final var currentException =
                assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, currentException.getErrors().size());
        assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());

    }

    @Test
    void givenAnInvalidEmptyName_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                assertThrows(DomainException.class, () -> currentState.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, currentException.getErrors().size());
        assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNameLengthLesserThan3_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = "Ca";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, currentException.getErrors().size());
        assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNameLengthGreaterThan255_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = RandomStringGenerator.generateRandomString(257);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var currentState =
                State.newState(expectedName);

        final var currentException =
                assertThrows(DomainException.class,
                        () -> currentState.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, currentException.getErrors().size());
        assertEquals(expectedErrorMessage, currentException.getErrors().getFirst().message());
    }

    @Test
    void givenAValidState_whenCallUpdate_thenReturnUpdatedState() {
        final var nameWithTypo = "California";
        final var expectedName = "Callifornia";

        final var aState = State.newState(nameWithTypo);

        assertDoesNotThrow(() -> aState.validate(new ThrowsValidationHandler()));

        final var currentState = aState.update(expectedName);

        assertDoesNotThrow(() -> currentState.validate(new ThrowsValidationHandler()));

        assertEquals(aState.getId(), currentState.getId());
        assertEquals(expectedName, currentState.getName());
    }

    @Test
    void givenAValidState_whenCallUpdateWithInvalidParams_thenReturnStateUpdated() {
        final String expectedName = null;

        final var aState = State.newState("California");

        assertDoesNotThrow(() -> aState.validate(new ThrowsValidationHandler()));

        final var currentState = aState.update(expectedName);

        assertEquals(aState.getId(), currentState.getId());
        assertEquals(expectedName, currentState.getName());
    }
}
