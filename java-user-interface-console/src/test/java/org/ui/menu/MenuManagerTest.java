package org.ui.menu;

import org.ui.console.MenuConsolePrinter;
import org.ui.console.UserConsoleUtils;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest {

    /**
     * El scanner para leer el input del usuario por consola
     */
    static Scanner scanner;

    /**
     * Manejador del menu
     */
    static MenuManager mm;

    /**
     * El objeto que permite mostrar por pantalla el menu
     */
    static MenuConsolePrinter mp;

    /**
     * Esta función crea el menu del programa
     */
    static void createMenu() {
        mm = new MenuManager();
        MenuItem mi = new MenuItem("Menu");
        mi.add("Listar archivos y directorios").setId(1);

        MenuItem fileMenu = mi.add("Menu Archivos").setId(2);
        fileMenu.add("Crear fichero").setId(3);
        fileMenu.add("Obtener propiedades/información de un archivo").setId(4);
        fileMenu.add("Eliminar archivos").setId(5);

        MenuItem dirMenu = mi.add("Menu Directorios").setId(6);
        dirMenu.add("Crear directorio").setId(7);
        dirMenu.add("Obtener propiedades/información de un directorio").setId(8);
        dirMenu.add("Eliminar directorios").setId(9);

        mi.add("Cambiar directorio de trabajo").setId(10);
        mi.add("Salir").setId(-1);

        mm.open(mi);
    }

    static void createMenuPrinter() {
        mp = new MenuConsolePrinter();
        mp.setIndexBracketLeft("[");
        mp.setIndexBracketRight("]");
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        createMenu();
        createMenuPrinter();

        MenuItem command;
        do {
            mp.show(mm.getStack().peek());
            System.out.print("Introduza una opción: ");
            command = mm.onConfirm(UserConsoleUtils.getUserInputIntBetweenBounds(0, mm.getStack().peek().numChildren()) - 1);
            System.out.println("Seleccionado: " + command.getName());
            if (!command.hasChildren() && mm.getStack().size() > 1) {
                mm.getStack().pop();
            }
            // manageAction(command.getId());
        } while (command.getId() != -1);
    }

}