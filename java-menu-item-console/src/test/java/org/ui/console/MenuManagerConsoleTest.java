package org.ui.console;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerConsoleTest {

    final String filename = Paths.get("../files/menu_test.xml").toString();

    final MenuManagerConsole mmc = new MenuManagerConsole();

    MenuItem readMenu() {
        try {
            return new MenuItemReaderXmlDOM().read(filename);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new MenuItem();
    }

    @Test
    void open() {
        mmc.open(readMenu());
        // mmc.run();
    }

    @Test
    void testFileConstructor() throws ParserConfigurationException, IOException, SAXException {
        new MenuManagerConsole(new File(filename));
    }

    @Test
    void testFilenameConstructor() throws ParserConfigurationException, IOException, SAXException {
        new MenuManagerConsole(filename);
    }

    @Test
    void getActions() {
        assertNotNull(mmc.getActions());
    }

    @Test
    void setActions() {
        mmc.setActions(new HashMap<>());
    }

    @Test
    void replaceAction() {
        mmc.replaceAction(0, () -> System.out.println("action replaced"));
    }

    @Test
    void addAction() {
        mmc.addAction(0, () -> System.out.println("action added"));
    }

}