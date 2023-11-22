package org.ui.menu.io;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MenuItemIO {

    public static final String TAG_ROOT_ELEMENT = "menu";

    public static final String TAG_MENU_ITEM = "menu_item";

    public static final String ATTR_MENU_ITEM_UNIQUE_ID = "unique_id";

    public static final String TAG_MENU_ITEM_ID = "id";

    public static final String TAG_MENU_ITEM_NAME = "name";

    public static final String TAG_LIST_CHILD_IDS = "list_child_unique_ids";

    public static final String TAG_CHILD_ID = "child_unique_id";

    public static final String ATTR_MENU_ITEM_ID = "id";

    public static final String ATTR_MENU_ITEM_NAME = "name";

    public static final String outputEncoding = "UTF-8";

    public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder();
    }

    public static Document getDocument(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = getDocumentBuilder();
        Document doc = db.parse(new File(filename));
        doc.getDocumentElement().normalize();
        return doc;
    }

}
