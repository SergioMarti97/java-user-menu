package org.ui.console;

import org.junit.jupiter.api.Test;
import org.ui.menu.io.read.MenuItemReaderDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MenuConsolePrinterTest {

    final String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void show() throws ParserConfigurationException, IOException, SAXException {
        var mi = MenuItemReaderDOM.read(filename);
        MenuConsolePrinter mcp = new MenuConsolePrinter();
        mcp.setIndexBracketLeft("");
        mcp.setIndexBracketRight(".-");
        mcp.show(mi);
    }

}