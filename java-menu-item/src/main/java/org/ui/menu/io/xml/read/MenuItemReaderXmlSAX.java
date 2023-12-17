package org.ui.menu.io.xml.read;

import org.ui.menu.MenuItem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuItemReaderXmlSAX {

    public MenuItem read(File file) {
        MenuItem mi = null;
        try (FileInputStream stream = new FileInputStream(file)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MenuItemReaderXmlSAXHandler handler = new MenuItemReaderXmlSAXHandler();
            parser.parse(stream, handler);
            mi = handler.getMenuItem();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return mi;
    }

    public MenuItem read(String filename) {
        return read(new File(filename));
    }

}
