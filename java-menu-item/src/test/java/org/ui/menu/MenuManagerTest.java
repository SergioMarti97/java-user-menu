package org.ui.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest {

    /**
     * Manejador del menu
     */
    MenuManager mm;

    @Test
    void constructor() {
        MenuManager mm = new MenuManager(MenuTestUtils.buildTestMenuItem());

        /*MenuItem command;
        do {
            // mp.show(mm.getStack().peek());
            // System.out.print("Introduza una opción: ");
            // command = mm.onConfirm(UserConsoleUtils.getUserInputIntBetweenBounds(0, mm.getStack().peek().numChildren()) - 1);
            // System.out.println("Seleccionado: " + command.getName());
            if (!command.hasChildren() && mm.getStack().size() > 1) {
                mm.getStack().pop();
            }
            // manageAction(command.getId());
        } while (command.getId() != -1);*/
    }

}