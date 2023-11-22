package org.ui.menu.io.read;

import org.ui.menu.MenuItem;
import org.ui.menu.io.MenuItemIO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MenuItemHandler extends DefaultHandler {

    public final String TAG_MENU_ITEM = "menu_item";

    public final String ATTR_MENU_ITEM_ID = "id";

    public final String TAG_MENU_ITEM_NAME = "name";

    public final String TAG_MENU_ITEM_CHILDREN = "items";

    public final String outputEncoding = "UTF-8";

    private StringBuilder elementValue;

    private MenuItem root;

    private int depth;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // super.characters(ch, start, length);
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        // super.startDocument();
        root = new MenuItem();
        depth = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case TAG_MENU_ITEM:
                int id = Integer.parseInt(attributes.getValue(ATTR_MENU_ITEM_ID));
                root.setId(id);
                break;
            case TAG_MENU_ITEM_NAME:
                elementValue = new StringBuilder();
                break;
            case TAG_MENU_ITEM_CHILDREN:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // super.endElement(uri, localName, qName);
        switch (qName) {
            case TAG_MENU_ITEM:
                break;
            case TAG_MENU_ITEM_NAME:
                root.setName(elementValue.toString());
                break;
            case TAG_MENU_ITEM_CHILDREN:
                depth--;
                break;
        }
    }

    public MenuItem getMenuItem() {
        return root;
    }

}
