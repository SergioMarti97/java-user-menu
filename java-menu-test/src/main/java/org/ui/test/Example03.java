package org.ui.test;

import org.ui.console.MenuManagerConsole;
import org.ui.menu.MenuItem;

/**
 * Ejemplo 3: crear un menú programáticamente
 */
public class Example03 {

    public static void main(String[] args) {
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

        int lastId = 10;
        MenuItem extraMenu = mi.add("Menu extra!").setId(0);
        for (int i = 0; i < 10; i++) {
            int id = i + lastId;
            extraMenu.add(String.format("Opción extra %d", id)).setId(id);
        }

        mi.add("Salir").setId(-1);

        // Mostrar por consola
        MenuManagerConsole mmc = new MenuManagerConsole(mi);
        mmc.run();
    }

}
