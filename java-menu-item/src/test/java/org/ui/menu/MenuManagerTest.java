package org.ui.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest {

    /**
     * Manejador del menu
     */
    MenuManager mm;

    @Test
    void constructor() {
        MenuManager mm = new MenuManager(MenuTestUtils.buildTestMenuItem());
    }

}