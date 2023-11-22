package org.ui.menu.io;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MenuItemIO {

    public static final String TAG_MENU_ITEM = "menu_item";

    public static final String ATTR_MENU_ITEM_ID = "id";

    public static final String ATTR_MENU_ITEM_NAME = "name";

    protected static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder();
    }

    protected static Document getDocument(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = getDocumentBuilder();
        Document doc = db.parse(new File(filename));
        doc.getDocumentElement().normalize();
        return doc;
    }

}
