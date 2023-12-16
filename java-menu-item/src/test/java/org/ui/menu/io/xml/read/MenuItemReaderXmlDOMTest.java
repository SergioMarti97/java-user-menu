package org.ui.menu.io.xml.read;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuTestUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemReaderXmlDOMTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void read() throws ParserConfigurationException, IOException, SAXException {
        var mi = new MenuItemReaderXmlDOM().read(path);
        assertEquals(mi, MenuTestUtils.buildTestMenuItem());
    }

}