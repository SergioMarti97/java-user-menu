package org.ui.console.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputConsoleUtilsTest {

    @Test
    void getUserInputInt() {
        Integer r = UserInputConsoleUtils.getUserInputInt();
        assertTrue(r instanceof Integer);
    }

}