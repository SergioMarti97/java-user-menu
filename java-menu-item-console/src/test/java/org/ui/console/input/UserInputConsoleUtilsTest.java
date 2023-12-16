package org.ui.console.input;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.ui.console.input.UserInputConsoleUtils.*;

class UserInputConsoleUtilsTest {

    @Test
    void getUserInputIntTest() {
        Integer r = getUserInputInt();
        assertTrue(r instanceof Integer);
    }

    @Test
    void getUserInputIntListTest() {
        String delimiter = ",";
        List<Integer> list = Arrays.asList(1, 10, 11, 30, -9, 14, 15);

        StringBuilder out = new StringBuilder();
        int min = list.get(0);
        int max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            int v = list.get(i);
            out.append(v);
            if (i < list.size() - 1) {
                out.append(delimiter);
            }

            if (v > max) {
                max = v;
            }
            if (v < min) {
                min = v;
            }
        }
        ByteArrayInputStream testIn = new ByteArrayInputStream(out.toString().getBytes());
        System.setIn(testIn);

        var l = getUserInputIntListBetweenBounds(min -1, max, delimiter);
        assertEquals(list, l);
    }

}