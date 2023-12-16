package org.ui.console.printer;

import org.junit.jupiter.api.Test;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class MenuConsolePrinterTest {

    final String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void show() throws ParserConfigurationException, IOException, SAXException {
        var mi = MenuItemReaderXmlDOM.read(filename);
        MenuConsolePrinter mcp = new MenuConsolePrinter();
        mcp.setIndexBracketLeft("[");
        mcp.setIndexBracketRight("]");
        mcp.setExpandChildren(false);
        mcp.show(mi);
    }

}