package org.ui.menu.io.write;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuTestUtils;

import javax.xml.parsers.ParserConfigurationException;

class MenuItemWriterTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void save() {
        var mi = MenuTestUtils.buildTestMenuItem();
        try {
            MenuItemWriter.save(mi, path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


}