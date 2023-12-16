package org.ui.menu.io.xml.write;

import org.junit.jupiter.api.Test;
import org.ui.menu.MenuTestUtils;

import javax.xml.parsers.ParserConfigurationException;

class MenuItemWriterXmlTest {

    String path = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_test.xml";

    @Test
    void save() {
        var mi = MenuTestUtils.buildTestMenuItem();
        try {
            new MenuItemWriterXml().save(mi, path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


}