package org.ui.menu.io.text;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemWriterTextTest {


    static String filenameXml = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    static String filename1 = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.txt";

    static String filename2 = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test_gen.txt";

    @Test
    void write() {
        MenuItem mi1 = MenuItemReaderText.read(filename1);
        MenuItemWriterText.write(filename2, mi1);
        MenuItem mi2 = MenuItemReaderText.read(filename2);
        assertEquals(mi1.numItems(), mi2.numItems());
    }

    @Test
    void writeDeque() throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = MenuItemReaderXmlDOM.read(filenameXml);
        MenuItemWriterText.writeDeque(filename2, mi);
    }

    @Test
    void xmlToText() throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = MenuItemReaderXmlDOM.read(filenameXml);
        MenuItemWriterText.write(filename2, mi);
    }

}