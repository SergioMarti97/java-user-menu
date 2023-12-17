package org.ui.console;

import org.ui.console.printer.MenuConsolePrinter;
import org.ui.menu.MenuItem;
import org.ui.menu.MenuManager;

import static org.ui.console.input.UserInputConsoleUtils.getUserInputIntBetweenBounds;

/**
 * Esta clase implementa el bucle para navegar las opciones
 * todo implementar correctamente la forma de navegar submenus de varios niveles de profundidad
 */
public abstract class AbstractMenuManagerConsole extends MenuManager {

    protected MenuConsolePrinter mcp;

    protected MenuItem option = null;

    protected int exitId = -1;

    protected boolean isRunning = true;

    protected boolean askForUserInput = true;

    protected boolean showSelectedOption = true;

    protected String textQuestionForUserInput = "Introduzca una opción: ";

    protected String textSelectedOption = "Seleccionado: ";

    public AbstractMenuManagerConsole() {
        super();
        mcp = new MenuConsolePrinter();
    }

    public AbstractMenuManagerConsole(MenuItem mo) {
        super(mo);
        mcp = new MenuConsolePrinter();
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
            option = onConfirm(getUserInputIntBetweenBounds(0, getPeekNumChildren()) - 1);

            if (showSelectedOption) {
                System.out.println(textSelectedOption + option.getName());
            }

            if (!option.hasItems() && menus.size() > 1) {
                menus.pop();
            }

            manageAction(option.getId()); // <- Implementar aquí la funcionalidad
        } while (option.getId() != exitId || isRunning);
    }

    public void run(MenuItem mi) {
        open(mi);
        run();
    }

    public void stop() {
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

}
