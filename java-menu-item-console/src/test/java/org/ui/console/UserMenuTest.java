package org.ui.console;

import org.ui.console.printer.MenuConsolePrinter;
import org.ui.menu.MenuItem;
import org.ui.menu.MenuManager;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.ui.console.input.UserInputConsoleUtils.getUserInputIntBetweenBounds;

public class UserMenuTest {

    static String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_magic_test.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        MenuManager mm = new MenuManager(new MenuItemReaderXmlDOM().read(filename));

        MenuConsolePrinter mcp = new MenuConsolePrinter();

        MenuItem command;
        do {
            mcp.show(mm);
            System.out.print("Introduza una opción: ");
            command = mm.onConfirm(getUserInputIntBetweenBounds(0, mm.getPeekNumChildren()) - 1);
            System.out.println("Seleccionado: " + command.getName());
            if (!command.hasItems() && mm.getStack().size() > 1) {
                mm.getStack().pop();
            }
            // manageAction(command.getId()); # <- Implementar aquí la funcionalidad
        } while (command.getId() != -1);
    }

}
