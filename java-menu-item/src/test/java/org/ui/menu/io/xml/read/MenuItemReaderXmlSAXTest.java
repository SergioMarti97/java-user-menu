package org.ui.menu.io.xml.read;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemReaderXmlSAXTest {

    static final String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    static MenuItemReaderXmlSAX readerSAX = new MenuItemReaderXmlSAX();

    @Test
    void read() {
        var mi1 = readerSAX.read(filename);
        var mi2 = MenuTestUtils.buildTestMenuItem();
        assertEquals(mi1, mi2);
    }

}