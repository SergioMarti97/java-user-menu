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

    public static MenuItem buildTestMagicMenuItem() {
        MenuItem mi = new MenuItem("Main");
        mi.add("Attack").setId(101);

        MenuItem magic = mi.add("Magic");

        MenuItem whiteMagic = magic.add("White");
        whiteMagic.add("Cure").setId(401);
        whiteMagic.add("Cura").setId(402);
        whiteMagic.add("Curaga").setId(403);
        whiteMagic.add("Esuna").setId(404);

        MenuItem blackMagic = magic.add("Black");
        blackMagic.add("Fire").setId(201);
        blackMagic.add("Fira").setId(202);
        blackMagic.add("Firaga").setId(203);
        blackMagic.add("Blizzard").setId(204);
        blackMagic.add("Blizzara").setId(205);
        blackMagic.add("Blizzaga").setId(206);
        blackMagic.add("Thunder").setId(207);
        blackMagic.add("Thundara").setId(208);
        blackMagic.add("Thundaga").setId(209);
        blackMagic.add("Quake").setId(210);
        blackMagic.add("Quake2").setId(211);
        blackMagic.add("Quake3").setId(212);
        blackMagic.add("Bio").setId(213);
        blackMagic.add("Bio1").setId(214);
        blackMagic.add("Bio2").setId(215);
        blackMagic.add("Demi").setId(216);
        blackMagic.add("Demi1").setId(217);
        blackMagic.add("Demi2").setId(218);

        mi.add("Defend").setId(102);

        MenuItem items = mi.add("Items");
        items.add("Potion").setId(301);
        items.add("Ether").setId(302);
        items.add("Elixir").setId(303);

        mi.add("Defend").setId(103);

        return mi;
    }

}
