package org.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la representación de un item o objeto
 * el cual pertenece a un menu
 */
public class MenuItem {

    /**
     * El identificador del item
     */
    private int id = -1;

    /**
     * El nombre del item
     */
    private String name;

    /**
     * Hash map del nombre del objeto y su indice
     */
    private final HashMap<String, Integer> itemPointer = new HashMap<>();

    /**
     * Una lista con todos los items que pertence a este item
     */
    private final ArrayList<MenuItem> items = new ArrayList<>();

    /**
     * Constructor vacio
     */
    public MenuItem() {
        name = "root";
    }

    /**
     * Constructor parametrizado
     * @param name el nombre de este item del menu
     */
    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem setId(int id) {
        this.id = id;
        return this;
    }

    public MenuItem setName(String name) {
        this.name = name;
        return this;
    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }

    public MenuItem getItem(String option) {
        return getItem(itemPointer.get(option));
    }

    public MenuItem add(String option) {
        if (!itemPointer.containsKey(option)) {
            itemPointer.put(option, items.size());
            items.add(new MenuItem(option));
        }
        return getItem(option);
    }

    // todo: comprobar que esta bien
    public MenuItem add(MenuItem child) {
        if (!itemPointer.containsKey(child.getName())) {
            itemPointer.put(child.getName(), items.size());
            items.add(child);
        }
        return getItem(child.getName());
    }

    public MenuItem onConfirm(int index) {
        if (getItem(index).hasItems()) {
            return getItem(index);
        } else {
            return this;
        }
    }

    public boolean hasItems() {
        return !itemPointer.isEmpty();
    }

    public int numItems() {
        return items.size();
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getItemPointer() {
        return itemPointer;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name + " children: " + items.size();
    }

}
