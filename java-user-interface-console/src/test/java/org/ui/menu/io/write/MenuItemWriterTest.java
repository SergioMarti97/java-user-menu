package org.ui.menu.io.write;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;

import javax.xml.parsers.ParserConfigurationException;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemWriterTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test_3.xml";

    MenuItem buildTestMenuItem() {
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

        return mi;
    }

    @Test
    void saveMenu() {
        var mi = buildTestMenuItem();
        try {
            NewMenuItemWriter.save(mi, path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


}