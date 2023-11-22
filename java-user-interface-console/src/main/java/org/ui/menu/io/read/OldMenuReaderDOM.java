package org.ui.menu.io.read;

import org.ui.console.MenuConsolePrinter;
import org.ui.menu.MenuItem;
import org.ui.menu.io.MenuItemIO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase construye un menu de consola a partir de la información almacenada en un archivo de texto
 * ¿Hacemos que se puedan leer varios tipos de archivos de almacenan la información? tipo: texto plano, xml y json
 */
public class OldMenuReaderDOM extends MenuItemIO {

    public static MenuItem readMenu(String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        Element root = doc.getDocumentElement();
        readMenu(root);
        return null;
    }

    public static void readMenu(Element root) {
        String id = root.getAttribute(ATTR_MENU_ITEM_UNIQUE_ID);

        NodeList nameNodeList = root.getElementsByTagName(TAG_MENU_ITEM_NAME);
        String name = nameNodeList.item(0).getTextContent();

        System.out.printf("%s id=%s name=%s\n", root.getTagName(), id, name);

        NodeList itemsNodeList = root.getElementsByTagName(TAG_LIST_CHILD_IDS);
        if (itemsNodeList.getLength() != 0) {
            Node itemsNode = itemsNodeList.item(0);
            if (itemsNode.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodeList = ((Element) itemsNode).getElementsByTagName(TAG_MENU_ITEM);
                for (int i = 0; i < childNodeList.getLength(); i++) {
                    Node node = childNodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        readMenu(element);
                    }
                }
            }
        }
    }

    private static Node getFirstNodeByName(NodeList list, String tagName) {
        Node node = null;
        for (int i = 0; i < list.getLength(); i++) {
            node = list.item(i);
            if (node.getNodeName().equals(tagName)) {
                return node;
            }
        }
        return node;
    }

    public static MenuItem readMenuItem2(Element root, MenuItem mi) {
        String id = root.getAttribute(ATTR_MENU_ITEM_UNIQUE_ID);

        NodeList nameNodeList = root.getElementsByTagName(TAG_MENU_ITEM_NAME);
        String name = nameNodeList.item(0).getTextContent();

        System.out.printf("%s id=%s name=%s\n", root.getTagName(), id, name);

        mi.setId(Integer.parseInt(id));
        mi.setName(name);

        NodeList itemsNodeList = root.getChildNodes();
        if (itemsNodeList.getLength() != 0) {

            Node itemsNode = getFirstNodeByName(itemsNodeList, TAG_LIST_CHILD_IDS);
            if (itemsNode != null) {

                if (itemsNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodeList = ((Element) itemsNode).getElementsByTagName(TAG_MENU_ITEM);

                    for (int i = 0; i < childNodeList.getLength(); i++) {
                        Node node = childNodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            MenuItem child = readMenuItem2(element, new MenuItem());
                            mi.add(child);
                        }
                    }

                }

            }

        }
        return mi;
    }

    public static MenuItem readMenuItem(String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        Element root = doc.getDocumentElement();
        return readMenuItem2(root, new MenuItem());
    }

    public static String getId(Node n) {
        var attributes = n.getAttributes();
        var node = attributes.getNamedItem("id");
        if (node != null) {
            return node.getTextContent();
        }
        return null;
    }

    public static void parse(final Document doc, final List<String> list, final Element e) {
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String id = getId(n);
                if (n.getNodeName().equals(TAG_MENU_ITEM_NAME)) {
                    list.add(n.getTextContent());
                }
                parse(doc, list, (Element) n);
            }
        }
    }

    public static List<String> recursiveDom(final String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        List<String> list = new ArrayList<>();
        parse(doc, list, doc.getDocumentElement());
        return list;
    }

    public static void parseMenuItem(final Document doc, MenuItem mi, final Element e) {
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String id = getId(n);
                if (n.getNodeName().equals(TAG_MENU_ITEM_NAME)) {
                    MenuItem child = mi.add(n.getTextContent());
                    if (id != null) {
                        child.setId(Integer.parseInt(id));
                    }
                    parseMenuItem(doc, mi, (Element) n);
                }
            }
        }
    }

    public static MenuItem recursiveParseMenuItem(final String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        MenuItem mi = new MenuItem();
        parseMenuItem(doc, mi, doc.getDocumentElement());
        return mi;
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test1.xml";
        // MenuItem mi = readMenuItem(filename);
        MenuItem mi = recursiveParseMenuItem(filename);
        MenuConsolePrinter mcp = new MenuConsolePrinter();
        mcp.show(mi);
        /*var l = recursiveDom(filename);
        for (var s : l) {
            System.out.println(s);
        }*/
    }

}
