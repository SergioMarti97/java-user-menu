package org.ui.menu;

/**
 * Esta clase implementa la funcionalidad para poder activar o desactivar una opción
 */
public interface IEnable {

    boolean isEnabled();

    void setEnabled(boolean b);

    void disable();

    void enable();

}
