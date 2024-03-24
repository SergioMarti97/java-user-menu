package org.ui.menu;

import org.geom.vector.vec2d.Vec2di;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.io.File;

import static org.ui.menu.io.MenuItemIOUtils.readMenuItem;
import static org.ui.menu.io.MenuItemIOUtils.saveMenuItem;

/**
 * Esta clase es la representación de un item o objeto
 * el cual pertenece a un menu
 */
public class MenuItem implements IGetSize, IEnable {

    /**
     * El identificador del item
     */
    protected int id = -1;

    /**
     * El nombre del item
     */
    protected String name;

    /**
     * Si la opción está disponible o no
     */
    protected boolean isEnabled = true;

    /**
     * Hash map del nombre del objeto y su indice
     */
    protected final HashMap<String, Integer> itemPointer = new HashMap<>();

    /**
     * Una lista con todos los items que pertence a este item
     */
    protected final ArrayList<MenuItem> items = new ArrayList<>();

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

    public MenuItem(final int id, final String name) {
        this.id = id;
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

    public MenuItem(MenuItem other) {
        this.id = other.id;
        this.name = other.name;
        this.isEnabled = other.isEnabled;
        itemPointer.putAll(new HashMap<>(other.itemPointer));
        items.addAll(new ArrayList<>(other.items));
    }

    public MenuItem(final File file) throws ParserConfigurationException, IOException, SAXException {
        read(file);
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

    public void copy(MenuItem other) {
        this.id = other.id;
        this.name = other.name;
        this.isEnabled = other.isEnabled;
        itemPointer.putAll(new HashMap<>(other.itemPointer));
        items.addAll(new ArrayList<>(other.items));
    }

    // input output methods

    public void read(final File file) throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = readMenuItem(file);
        assert mi != null;
        copy(mi);
    }

    public void read(final String filename) throws ParserConfigurationException, IOException, SAXException {
        read(new File(filename));
    }

    public void save(final File file) throws IOException, ParserConfigurationException {
        saveMenuItem(this, file);
    }

    public void save(final String filename) throws IOException, ParserConfigurationException {
        save(new File(filename));
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
    public Vec2di getSize() {
        // Por ahora, las opciones son simplemente una línea de texto
        return new Vec2di(name.length(), 1);
    }

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

    @Override
    public String toString() {
        return name + " children: " + items.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return getId() == menuItem.getId() && isEnabled() == menuItem.isEnabled() && Objects.equals(getName(), menuItem.getName()) && Objects.equals(getItemPointer(), menuItem.getItemPointer()) && Objects.equals(getItems(), menuItem.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), isEnabled(), getItemPointer(), getItems());
    }

}
