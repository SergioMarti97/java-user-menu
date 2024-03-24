package org.ui.console;

import org.ui.console.printer.MenuConsolePrinter;
import org.ui.menu.MenuItem;
import org.ui.menu.MenuManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static org.ui.console.input.UserInputConsoleUtils.getUserInputIntBetweenBounds;

import java.io.File;
import java.io.IOException;

/**
 * Esta clase implementa el bucle para navegar las opciones
 */
public abstract class AbstractMenuManagerConsole extends MenuManager {

    protected MenuConsolePrinter mcp = new MenuConsolePrinter();

    protected MenuItem option = null;

    protected int exitId = -1;

    protected int closeMenuId = 0;

    protected boolean isRunning = true;

    protected boolean askForUserInput = true;

    protected boolean showSelectedOption = true;

    protected boolean returnToMainMenuAfterConfirm = true;

    protected String textQuestionForUserInput = "Introduzca una opción: ";

    protected String textSelectedOption = "Seleccionado: ";

    protected MenuItem exitOption = new MenuItem("salir");

    protected MenuItem closeMenuOption = new MenuItem(0, "cerrar menu actual");

    public AbstractMenuManagerConsole() {
        super();
    }

    public AbstractMenuManagerConsole(MenuItem mo) {
        super(mo);
    }

    public AbstractMenuManagerConsole(final File file) throws ParserConfigurationException, IOException, SAXException {
        super(file);
    }

    public AbstractMenuManagerConsole(final String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    public abstract void manageAction(int optionId);

    public void run() {
        isRunning = true;
        if (menus.isEmpty()) {
            System.out.println("No hay abierto ningún menú. Abre primero un menú.");
        }
        do {
            mcp.show(this);

            if (askForUserInput) {
                System.out.print(textQuestionForUserInput);
            }

            // todo implementar el código para seleccionar por texto similar
            // todo implementar el código para seleccionar por número
            int selectedIdOption = getUserInputIntBetweenBounds(-1, getPeekNumChildren());
            if (selectedIdOption == exitId) {
                stop();
            } else if (selectedIdOption == closeMenuId) {
                option = closeMenuOption;
                menus.pop();
                if (menus.isEmpty()) {
                    stop();
                }
            } else {
                option = onConfirm(selectedIdOption -1);
            }

            if (showSelectedOption) {
                System.out.println(textSelectedOption + option.getName());
            }

            if (!option.hasItems() && menus.size() > 1 && option != closeMenuOption) {
                if (returnToMainMenuAfterConfirm) {
                    do {
                        menus.pop();
                    } while (menus.size() > 1);
                } else {
                    menus.pop();
                }
            }

            manageAction(option.getId()); // <- Implementar aquí la funcionalidad

        } while (!menus.isEmpty() && (isRunning || option.getId() != exitId));
    }

    public void run(MenuItem mi) {
        open(mi);
        run();
    }

    public void stop() {
        option = exitOption;
        isRunning = false;
    }

    // Getter & setter

    public MenuConsolePrinter getConsolePrinter() {
        return mcp;
    }

    public void setConsolePrinter(MenuConsolePrinter mcp) {
        this.mcp = mcp;
    }

    public MenuItem getOption() {
        return option;
    }

    public int getExitId() {
        return exitId;
    }

    public void setExitId(int exitId) {
        this.exitId = exitId;
    }

    public int getCloseMenuId() {
        return closeMenuId;
    }

    public void setCloseMenuId(int closeMenuId) {
        this.closeMenuId = closeMenuId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isAskForUserInput() {
        return askForUserInput;
    }

    public void setAskForUserInput(boolean askForUserInput) {
        this.askForUserInput = askForUserInput;
    }

    public boolean isShowSelectedOption() {
        return showSelectedOption;
    }

    public void setShowSelectedOption(boolean showSelectedOption) {
        this.showSelectedOption = showSelectedOption;
    }

    public boolean isReturnToMainMenuAfterConfirm() {
        return returnToMainMenuAfterConfirm;
    }

    public void setReturnToMainMenuAfterConfirm(boolean returnToMainMenuAfterConfirm) {
        this.returnToMainMenuAfterConfirm = returnToMainMenuAfterConfirm;
    }

    public String getTextQuestionForUserInput() {
        return textQuestionForUserInput;
    }

    public void setTextQuestionForUserInput(String textQuestionForUserInput) {
        this.textQuestionForUserInput = textQuestionForUserInput;
    }

    public String getTextSelectedOption() {
        return textSelectedOption;
    }

    public void setTextSelectedOption(String textSelectedOption) {
        this.textSelectedOption = textSelectedOption;
    }

    public MenuItem getExitOption() {
        return exitOption;
    }

    public void setExitOption(MenuItem exitOption) {
        this.exitOption = exitOption;
    }

    public MenuItem getCloseMenuOption() {
        return closeMenuOption;
    }

    public void setCloseMenuOption(MenuItem closeMenuOption) {
        this.closeMenuOption = closeMenuOption;
    }

}
