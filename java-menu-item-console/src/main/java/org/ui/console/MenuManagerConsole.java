package org.ui.console;

import org.ui.menu.MenuItem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

import java.io.IOException;
import java.util.HashMap;

public class MenuManagerConsole extends AbstractMenuManagerConsole {

    protected HashMap<Integer, Runnable> actions = new HashMap<>();

    public MenuManagerConsole() {
    }

    public MenuManagerConsole(MenuItem mo) {
        super(mo);
    }

    public MenuManagerConsole(final File file) throws ParserConfigurationException, IOException, SAXException {
        super(file);
    }

    public MenuManagerConsole(final String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
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

    public Runnable replaceAction(int optionId, Runnable action) {
        return actions.replace(optionId, action);
    }

    public void addAction(int optionId, Runnable action) {
        actions.put(optionId, action);
    }

}
