package org.ui.menu;

import org.ui.menu.enable.IEnable;
import org.ui.menu.table.MenuItemTableCursor;

public class MenuItemComplete extends MenuItemTableCursor implements IEnable {

    /**
     * Si la opción está disponible o no
     */
    protected boolean isEnabled = true;

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        isEnabled = b;
    }

    @Override
    public void disable() {
        isEnabled = false;
    }

    @Override
    public void enable() {
        isEnabled = true;
    }

}
