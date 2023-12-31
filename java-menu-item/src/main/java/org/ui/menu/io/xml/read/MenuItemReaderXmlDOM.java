package org.ui.menu.io.xml.read;

import org.ui.menu.MenuItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.ui.menu.io.xml.MenuItemXMLUtils.*;

public class MenuItemReaderXmlDOM {

    /**
     * Esta función utiliza la tecnología DOM para leer un archivo XML
     * @param mi el item del menú raíz
     * @param root el elemento raíz
     */
    public void parseMenuItem(MenuItem mi, Element root) {
        String id = root.getAttribute(ATTR_MENU_ITEM_ID);
        if (!id.equals("")) {
            mi.setId(Integer.parseInt(id));
        }
        String name = root.getAttribute(ATTR_MENU_ITEM_NAME);
        if (!name.equals("")) {
            mi.setName(name);
        }
        
        NodeList itemsNodeList = root.getChildNodes();
        if (itemsNodeList.getLength() != 0) {
            for (int i = 0; i < itemsNodeList.getLength(); i++) {
                Node node = itemsNodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    MenuItem child = new MenuItem();
                    parseMenuItem(child, (Element) node);
                    mi.add(child);
                }
            }
        }
    }

    public MenuItem read(File file) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(file);
        MenuItem mi = new MenuItem();
        parseMenuItem(mi, doc.getDocumentElement());
        return mi;
    }

    public MenuItem read(String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        MenuItem mi = new MenuItem();
        parseMenuItem(mi, doc.getDocumentElement());
        return mi;
    }

}
