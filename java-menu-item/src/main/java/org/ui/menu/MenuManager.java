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

    // TODO: 24/03/2024 esta función debería formar parte de MenuItem
    // TODO: 24/03/2024 implementar la función "write" para escribir el menu que está trabajando actualmente
    /**
     * Lee un archivo para crear el MenuItem que manejará el MenuManager
     * @param file el archivo con la información del MenuItem
     * @return MenuItem
     */
    public MenuItem read(final File file) throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = null;
        if (file != null && file.exists()) {
            String name = file.getName();
            String extension = name.substring(name.lastIndexOf("."));
            switch (extension) {
                case ".xml":
                    mi = new MenuItemReaderXmlDOM().read(file);
                    break;
                case ".txt":
                    mi = new MenuItemReaderText().read(file);
                    break;
            }
        }
        return mi;
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

