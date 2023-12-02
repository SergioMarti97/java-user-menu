package org.ui.menu.table;

import org.ui.menu.MenuItem;

/**
 * Esta clase implementa las funciones para que un MenuItem muestre sus opciones en forma de tabla,
 * organizado en filas y columnas
 * @param <T> Tipo MenuItem
 */
public interface ITable<T extends MenuItem> {

    // Is recommendable avoid a fields on an interface, but this is a common field that will be implemented as final
    // (i.e. constant) in all classes which inherits from this interface
    // Vec2di table = new Vec2di();

    int getNumRows();

    int getNumCols();

    T setNumRows(int numRows);

    T setNumCols(int numCols);

    T setTable(int numRows, int numCols);

}
