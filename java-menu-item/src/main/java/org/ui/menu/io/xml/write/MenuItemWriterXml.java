package org.ui.menu.io.xml.write;

import org.ui.menu.MenuItem;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.ui.menu.io.xml.MenuItemXMLUtils.*;

public class MenuItemWriterXml {

    private void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }

    private void writeXml(Document doc, String path) {
        try (FileOutputStream output = new FileOutputStream(path)) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void writeXml(Document doc, File file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element createElement(Document doc, String tagName) {
        return doc.createElement(tagName);
    }

    private void setElementAttributes(Element e, MenuItem mi) {
        e.setAttribute(ATTR_MENU_ITEM_ID, String.format("%d", mi.getId()));
        e.setAttribute(ATTR_MENU_ITEM_NAME, mi.getName());
        // todo: implementar atributos diferentes para otros tipos de MenuItems
    }

    public void save(Document doc, Element e, MenuItem mi) {
        setElementAttributes(e, mi);
        if (mi.hasItems()) {
            for (var child : mi.getItems()) {
                Element e1 = createElement(doc, TAG_MENU_ITEM);
                save(doc, e1, child);
                e.appendChild(e1);
            }
        }
    }

    public Element save(Document doc, MenuItem mi) {
        Element menu = doc.createElement(TAG_MENU_ITEM);
        save(doc, menu, mi);
        return menu;
    }

    public void save(MenuItem mi, String path) throws ParserConfigurationException {
        DocumentBuilder db = getDocumentBuilder();
        Document doc = db.newDocument();
        doc.appendChild(save(doc, mi));
        writeXml(doc, path);
    }

    public void save(MenuItem mi, File file) throws ParserConfigurationException {
        DocumentBuilder db = getDocumentBuilder();
        Document doc = db.newDocument();
        doc.appendChild(save(doc, mi));
        writeXml(doc, file);
    }

}
