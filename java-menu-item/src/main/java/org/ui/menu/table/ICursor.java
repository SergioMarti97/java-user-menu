package org.ui.menu.table;

import org.ui.menu.MenuItem;

/**
 * Esta interfaz contiene las funciones necesarias para manejar un cursor dentro de las opciones
 * de un MenuItem
 * @param <T> Tipo MenuItem
 */
public interface ICursor<T extends MenuItem> {

    void onUp();

    void onDown();

    void onLeft();

    void onRight();

    T onConfirm();

}
