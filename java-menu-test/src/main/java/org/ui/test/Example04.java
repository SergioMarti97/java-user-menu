package org.ui.test;

import org.ui.console.MenuManagerConsole;
import org.ui.menu.MenuItem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

public class Example04 {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        var mmc = new MenuManagerConsole(Paths.get("./", "files", "menu_test.xml").toString());
        mmc.run();
    }

}
