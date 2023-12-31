package org.ui.console.printer;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class MenuConsolePrinterTest {

    final String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    final MenuConsolePrinter mcp = new MenuConsolePrinter();

    MenuItem readMenu(String filename) {
        try {
            return new MenuItemReaderXmlDOM().read(filename);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new MenuItem();
    }

    @Test
    void showMenu() {
        var mi = readMenu(filename);
        mcp.setIndexBracketLeft("[");
        mcp.setIndexBracketRight("]");
        mcp.setExpandChildren(false);
        mcp.show(mi);
    }

    @Test
    void showMenuSubmenuMarker() {
        var mi = readMenu(filename);
        mcp.setShowSubmenuMarker(true);
        mcp.show(mi);
    }



}