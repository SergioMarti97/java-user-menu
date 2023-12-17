package org.ui.menu.io.xml.read;

import org.ui.menu.MenuItem;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

import static org.ui.menu.io.xml.MenuItemXMLUtils.*;

/**
 * @see "https://www.baeldung.com/java-sax-parser"
 * @see "http://mohammed-technical.blogspot.com/2010/08/parsing-complex-nested-xml-with.html"
 */
public class MenuItemReaderXmlSAXHandler extends DefaultHandler {

    private StringBuilder buffer;

    private MenuItem mi = null;

    private Stack<MenuItem> stack;

    @Override
    public void characters(char[] ch, int start, int length) {
        if (buffer == null) {
            buffer = new StringBuilder();
        } else {
            buffer.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        stack = new Stack<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase(TAG_MENU_ITEM)) {
            String id = attributes.getValue(ATTR_MENU_ITEM_ID);
            String name = attributes.getValue(ATTR_MENU_ITEM_NAME);

            MenuItem currentMi = new MenuItem(name).setId(Integer.parseInt(id));

            MenuItem lastMi = getLastMenuItem();
            if (lastMi != null) {
                lastMi.add(currentMi);
            } else {
                mi = currentMi;
            }
            stack.push(currentMi);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase(TAG_MENU_ITEM)) {
            stack.pop();
        }
        buffer.setLength(0);
    }

    private MenuItem getLastMenuItem() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public MenuItem getMenuItem() {
        return mi;
    }

}
