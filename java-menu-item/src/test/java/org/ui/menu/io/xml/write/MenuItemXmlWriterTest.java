package org.ui.menu.io.xml.write;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuTestUtils;

import javax.xml.parsers.ParserConfigurationException;

class MenuItemXmlWriterTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void save() {
        var mi = MenuTestUtils.buildTestMenuItem();
        try {
            MenuItemXmlWriter.save(mi, path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


}