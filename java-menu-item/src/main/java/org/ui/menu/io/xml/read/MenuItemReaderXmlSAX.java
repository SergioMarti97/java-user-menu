package org.ui.menu.io.xml.read;

import org.ui.menu.MenuItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static org.ui.menu.io.xml.MenuItemXMLUtils.*;

public class MenuItemReaderXmlSAX extends DefaultHandler {

    private StringBuilder elementValue;

    private MenuItem mi;

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
        mi = new MenuItem();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase(TAG_MENU_ITEM)) {


            String id = attributes.getValue(ATTR_MENU_ITEM_ID);
            String name = attributes.getValue(ATTR_MENU_ITEM_NAME);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

}
