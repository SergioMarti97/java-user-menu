package org.ui.menu.enable;

import org.ui.menu.MenuItem;

public class MenuItemEnabled extends MenuItem implements IEnable {

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
