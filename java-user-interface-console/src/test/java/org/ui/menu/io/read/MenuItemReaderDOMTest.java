package org.ui.menu.io.read;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemReaderDOMTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test_2.xml";

    @Test
    void read() throws ParserConfigurationException, IOException, SAXException {
        MenuItemReaderDOM.read(path);
    }

}