package org.ui.menu.io.read;

import org.ui.menu.MenuItem;
import org.ui.menu.io.MenuItemIO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MenuItemReaderDOM extends MenuItemIO {

    static class MenuItemInfo {

        int id;

        String name;

        List<Integer> childUniqueIds;

        public MenuItemInfo(int id, String name, List<Integer> childUniqueIds) {
            this.id = id;
            this.name = name;
            this.childUniqueIds = childUniqueIds;
        }

        @Override
        public String toString() {
            return "MenuItemInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", childUniqueIds=" + childUniqueIds +
                    '}';
        }
    }

    private static Node getNodeByTagName(Element e, String tag, int index) {
        var childNodes = e.getElementsByTagName(tag);
        if (childNodes.getLength() == 0 || childNodes.getLength() >= index) {
            return null;
        }
        return childNodes.item(index);
    }

    private static Element castNodeToElement(Node n) {
        if (n != null) {
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) n;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static Element getElementByTagName(Element e, String tag, int index) {
        return castNodeToElement(getNodeByTagName(e, tag, index));
    }

    private static String getTagContent(Element e, String tag, int index) {
        Node n = getNodeByTagName(e, tag, index);
        if (n != null) {
            return n.getTextContent();
        } else {
            return null;
        }
    }

    public static HashMap<Integer, MenuItemInfo> readMenuItemsInfo(Element root) {
        HashMap<Integer, MenuItemInfo> miiMap = new HashMap<>();

        NodeList miNodeList = root.getElementsByTagName(TAG_MENU_ITEM);

        for (int i = 0; i < miNodeList.getLength(); i++) {
            var miNode = miNodeList.item(i);
            var miElement = castNodeToElement(miNode);

            int uniqueId = Integer.parseInt(miElement.getAttributeNode(ATTR_MENU_ITEM_UNIQUE_ID).getTextContent());
            String miStrId = getTagContent(miElement, TAG_MENU_ITEM_ID, 0);
            int miId = -1;
            if (miStrId != null) {
                miId = Integer.parseInt(miStrId);
            }
            String miName = getTagContent(miElement, TAG_MENU_ITEM_NAME, 0);

            List<Integer> uniqueIds = new ArrayList<>();
            var listChildUniqueIdsElement = getElementByTagName(miElement, TAG_LIST_CHILD_IDS, 0);
            if (listChildUniqueIdsElement != null) {
                NodeList uniqueChildIdsNodeList = listChildUniqueIdsElement.getElementsByTagName(TAG_CHILD_ID);
                for (int j = 0; j < uniqueChildIdsNodeList.getLength(); j++) {
                    var childUniqueId = castNodeToElement(uniqueChildIdsNodeList.item(j));
                    uniqueIds.add(Integer.parseInt(childUniqueId.getTextContent()));
                }
            }

            miiMap.put(uniqueId, new MenuItemInfo(miId, miName, uniqueIds));
        }

        return miiMap;
    }

    public static MenuItem read(String filename) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getDocument(filename);
        Element root = doc.getDocumentElement();
        HashMap<Integer, MenuItemInfo> miiMap = readMenuItemsInfo(root);

        for (var e : miiMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue().toString());
        }
        return null;
    }


}
