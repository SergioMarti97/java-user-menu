package org.ui.menu.cursor;

/**
 * Esta interfaz contiene las funciones necesarias para manejar un cursor dentro de las opciones
 * de un MenuItem
 * @param <T> Tipo MenuItem
 */
public interface ICursor<T> {

    void onUp();

    void onDown();

    void onLeft();

    void onRight();

    T onConfirm();

}
