package org.ui.test;

import org.ui.console.MenuManagerConsole;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Example02 {

    static String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_magic_test.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        MenuManagerConsole mmc = new MenuManagerConsole(new MenuItemReaderXmlDOM().read(filename));

        // Implementar aquí las acciones de cada opción
        mmc.addAction(1, () -> System.out.println("algo"));

        mmc.run();
    }

}
