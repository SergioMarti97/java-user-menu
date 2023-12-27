package org.ui.console;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerConsoleTest {

    final String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    final MenuManagerConsole mmc = new MenuManagerConsole();

    MenuItem readMenu(String filename) {
        try {
            return new MenuItemReaderXmlDOM().read(filename);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new MenuItem();
    }

    @Test
    void run() {
        mmc.open(readMenu(filename));
        // mmc.run();
    }

}