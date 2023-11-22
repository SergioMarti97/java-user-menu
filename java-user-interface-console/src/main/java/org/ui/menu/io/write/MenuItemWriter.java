package org.ui.menu.io.write;

import org.ui.menu.MenuItem;
import org.ui.menu.io.MenuItemIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MenuItemWriter extends MenuItemIO {

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }

    private static void writeXml(Document doc, String path) {
        try (FileOutputStream output = new FileOutputStream(path)) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static Element createElement(Document doc, String tagName) {
        return doc.createElement(tagName);
    }

    public static Element createElement(Document doc, String tagName, String content) {
        Element e = createElement(doc, tagName);
        e.setTextContent(content);
        return e;
    }

    public static void save(Document doc, Element menu, MenuItem mi) {
        Element menuItemElement = createElement(doc, TAG_MENU_ITEM);
        menuItemElement.setAttribute(ATTR_MENU_ITEM_UNIQUE_ID, String.format("%d", mi.hashCode()));
        menuItemElement.appendChild(createElement(doc, TAG_MENU_ITEM_ID, String.format("%d", mi.getId())));
        menuItemElement.appendChild(createElement(doc, TAG_MENU_ITEM_NAME, mi.getName()));

        if (mi.hasChildren()) {
            Element listChildIds = doc.createElement(TAG_LIST_CHILD_IDS);
            for (var child : mi.getItems()) {
                listChildIds.appendChild(createElement(doc, TAG_CHILD_ID, String.format("%d", child.hashCode())));
            }
            menuItemElement.appendChild(listChildIds);
        }
        menu.appendChild(menuItemElement);

        for (var child : mi.getItems()) {
            save(doc, menu, child);
        }
    }

    public static Element save(Document doc, MenuItem mi) {
        Element menu = doc.createElement(TAG_ROOT_ELEMENT);
        save(doc, menu, mi);
        return menu;
    }

    public static void save(MenuItem mi, String path) throws ParserConfigurationException {
        DocumentBuilder db = getDocumentBuilder();
        Document doc = db.newDocument();
        doc.appendChild(save(doc, mi));
        writeXml(doc, path);
    }

}
