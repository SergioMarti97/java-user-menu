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

    static MenuItemReaderText r = new MenuItemReaderText();

    static MenuItemWriterText w = new MenuItemWriterText();

    @Test
    void write() {
        MenuItem mi1 = r.read(filename1);
        w.save(mi1, filename2);
        MenuItem mi2 = r.read(filename2);
        assertEquals(mi1.numItems(), mi2.numItems());
    }

    @Test
    void writeDeque() throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = new MenuItemReaderXmlDOM().read(filenameXml);
        boolean prev = w.isWriteWithDeque();
        w.setWriteWithDeque(true);
        w.save(mi, filename2);
        w.setWriteWithDeque(prev);
    }

    @Test
    void xmlToText() throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = new MenuItemReaderXmlDOM().read(filenameXml);
        w.save(mi, filename2);
    }

}