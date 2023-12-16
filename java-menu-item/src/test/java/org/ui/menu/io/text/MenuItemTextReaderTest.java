package org.ui.menu.io.text;

import org.junit.jupiter.api.Test;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemTextReaderTest {

    static String filenameXml = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    static String filenameTxt = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.txt";

    @Test
    void countIdent() {
        assertEquals(MenuItemTextReader.countIdent("        Crear fichero"), 2);
    }

    @Test
    void read() throws ParserConfigurationException, IOException, SAXException {
        var mi1 = MenuItemReaderXmlDOM.read(filenameXml);
        var mi2 = MenuItemTextReader.read(filenameTxt);
        assertEquals(mi1.numItems(), mi2.numItems());
    }

}