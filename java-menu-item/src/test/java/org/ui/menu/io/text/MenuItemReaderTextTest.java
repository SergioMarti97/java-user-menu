package org.ui.menu.io.text;

import org.junit.jupiter.api.Test;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemReaderTextTest {

    static String filenameXml = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    static String filenameTxt = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.txt";

    static MenuItemReaderText reader = new MenuItemReaderText();

    @Test
    void countIdent() {
        assertEquals(reader.countIdent("        Crear fichero"), 2);
    }

    @Test
    void read() throws ParserConfigurationException, IOException, SAXException {
        var mi1 = new MenuItemReaderXmlDOM().read(filenameXml);
        var mi2 = reader.read(filenameTxt);
        assertEquals(mi1.numItems(), mi2.numItems());
    }

}