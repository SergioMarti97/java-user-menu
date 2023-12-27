package org.ui.console.printer;

import org.geom.vector.vec2d.Vec2di;
import org.ui.menu.MenuItem;
import org.ui.menu.table.MenuItemTable;

// todo: para mostrar una tabla con más opciones que espacio, mostrar en varias veces
// todo: añadir una opción para indicar con un simbolito que una opción es un submenu
public class MenuTableConsolePrinter extends MenuConsolePrinter {

    protected Vec2di cellSize = new Vec2di();

    public void build(MenuItem root) {
        for (var m : root.getItems()) {
            if (m.hasItems()) {
                build(m);
            }
            cellSize.setX(Math.max(m.getSize().getX(), cellSize.getX()));
            cellSize.setY(Math.max(m.getSize().getY(), cellSize.getY()));
        }
    }

    public void render(MenuItemTable m) {

    }

}
