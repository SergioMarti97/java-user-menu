package org.ui.menu;

import org.ui.menu.io.text.MenuItemReaderText;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Stack;
import java.io.File;

/**
 * Esta clase maneja el menu
 * Un menu esta formado por items los cuales a su vez puede
 * contener items hijos
 * Maneja todos los items en un stack
 */
public class MenuManager {

    /**
     * La mejor opción para trabajar con los menus es un Stack:
     * First In, First Out.
     */
    protected final Stack<MenuItem> menus = new Stack<>();

    /**
     * Constructores
     */
    public MenuManager() {

    }

    public MenuManager(MenuItem mo) {
        menus.add(mo);
    }

    public MenuManager(final File file) throws ParserConfigurationException, IOException, SAXException {
        menus.add(read(file));
    }

    public MenuManager(final String filename) throws ParserConfigurationException, IOException, SAXException {
        this(new File(filename));
    }

    // input output methods

    public MenuItem read(final File file) throws ParserConfigurationException, IOException, SAXException {
        return new MenuItem(file);
    }

    public MenuItem read(final String filename) throws ParserConfigurationException, IOException, SAXException {
        return new MenuItem(filename);
    }

    public void save(final File file) throws IOException, ParserConfigurationException {
        if (!menus.isEmpty()) {
            Stack<?> copiedStack = (Stack<?>) menus.clone();
            do {
                copiedStack.pop();
            } while (menus.size() > 1);
            if (copiedStack.peek() instanceof MenuItem) {
                MenuItem peek = (MenuItem) copiedStack.peek();
                peek.save(file);
            }
        }
    }

    public void save(final String filename) throws IOException, ParserConfigurationException {
        save(new File(filename));
    }

    /**
     * Limpia el stack
     */
    public void clear() {
        menus.clear();
    }

    /**
     * Abre un menu item
     */
    public void open(MenuItem mo) {
        clear();
        menus.add(mo);
    }

    /**
     * Función para la acción de confirmar una opción del menu
     * @param index el índice de la opción
     * @return el objeto MenuItem seleccionado
     */
    public MenuItem onConfirm(int index) {
        if (menus.isEmpty()) {
            return null;
        }

        MenuItem next = menus.peek().onConfirm(index);

        if (next.equals(menus.peek())) {
            return menus.peek().getItem(index);
        } else {
            return menus.push(next);
        }
    }

    // Getters

    public Stack<MenuItem> getStack() {
        return menus;
    }

    public int getPeekNumChildren() {
        return menus.peek().numItems();
    }

}

