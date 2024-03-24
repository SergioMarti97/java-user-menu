package org.ui.menu.io;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuItem;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemIOUtilsTest {

    @Test
    void saveMenuItem() throws IOException, ParserConfigurationException {
        MenuItemIOUtils.saveMenuItem(new MenuItem(), new File(Paths.get("..", "files", "menu_save_test.xml").toString()));
    }

}