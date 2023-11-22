package org.ui.menu;

public class MenuTestUtils {

    public static MenuItem buildTestMenuItem() {
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

}
