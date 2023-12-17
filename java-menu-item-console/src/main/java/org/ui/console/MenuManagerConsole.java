package org.ui.console;

import org.ui.menu.MenuItem;

import java.util.HashMap;

public class MenuManagerConsole extends AbstractMenuManagerConsole {

    protected HashMap<Integer, Runnable> actions;

    public MenuManagerConsole() {
        actions = new HashMap<>();
    }

    public MenuManagerConsole(MenuItem mo) {
        super(mo);
        actions = new HashMap<>();
    }

    @Override
    public void manageAction(int optionId) {
        Runnable runnable = actions.get(optionId);
        if (runnable != null) {
            runnable.run();
        }
    }

    public HashMap<Integer, Runnable> getActions() {
        return actions;
    }

    public void setActions(HashMap<Integer, Runnable> actions) {
        this.actions = actions;
    }

    public void addAction(int optionId, Runnable action) {
        actions.put(optionId, action);
    }

}
