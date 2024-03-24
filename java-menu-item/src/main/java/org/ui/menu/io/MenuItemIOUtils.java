package org.ui.menu.io;

import org.ui.menu.MenuItem;
import org.ui.menu.io.text.MenuItemReaderText;
import org.ui.menu.io.xml.read.MenuItemReaderXmlDOM;
import org.ui.menu.io.xml.write.MenuItemWriterXml;
import org.ui.menu.io.text.MenuItemWriterText;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MenuItemIOUtils {

    /**
     * Obtiene la extensión de un archivo
     * @param file el archivo
     * @return la extensión del archivo en un string
     */
    public static String getFileExtension(final File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf("."));
    }

    public static MenuItem readMenuItem(final File file, final String extension) throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = null;
        switch (extension) {
            case ".xml":
                mi = new MenuItemReaderXmlDOM().read(file);
                break;
            case ".txt":
                mi = new MenuItemReaderText().read(file);
                break;
            // TODO: 24/03/2024 crear lector de archivos formato JSON
        }
        return mi;
    }

    /**
     * Lee un archivo para crear el MenuItem que manejará el MenuManager
     * @param file el archivo con la información del MenuItem
     * @return MenuItem
     */
    public static MenuItem readMenuItem(final File file) throws ParserConfigurationException, IOException, SAXException {
        MenuItem mi = null;
        if (file != null && file.exists()) {
            mi = readMenuItem(file, getFileExtension(file));
        }
        return mi;
    }

    public static void saveMenuItem(final MenuItem mi, final File file, final String extension) throws ParserConfigurationException {
        switch (extension) {
            case ".xml":
                new MenuItemWriterXml().save(mi, file);
                break;
            case ".txt":
                new MenuItemWriterText().save(mi, file);
                break;
            // TODO: 24/03/2024 crear writer de archivos formato JSON
        }
    }

    public static void saveMenuItem(final MenuItem mi, final File file) throws IOException, ParserConfigurationException {
        if (file != null) {
            if (!file.exists()) {
                file.createNewFile();
                /*FileOutputStream fos = new FileOutputStream(file);
                fos.write("".getBytes(StandardCharsets.UTF_8));
                fos.flush();
                fos.close();*/
            }
            String extension = getFileExtension(file);
            saveMenuItem(mi, file, extension);
        }
    }

}
