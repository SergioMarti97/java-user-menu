package org.ui.menu.io.text;

import org.ui.menu.MenuItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuItemTextReader {

    public static Pattern TABULATION_PATTERN = Pattern.compile(" {4}");

    public static int countIdent(String line) {
        Matcher countIdentMatcher = TABULATION_PATTERN.matcher(line);
        int count = 0;
        while (countIdentMatcher.find()) {
            count++;
        }
        return count;
    }

    public static MenuItem parseMenuItem(MenuItem mi, List<String> info, int row, int level) {
        String line = info.get(row);
        int ident = countIdent(line);
        line = line.replaceAll(TABULATION_PATTERN.pattern(), "");

        if (ident == level) {
            mi.setName(line);
        } else if (ident > level) {
            MenuItem child = new MenuItem();
            mi.add(child);
        }

        return null;
    }

    public static MenuItem read(String filename) {
        List<String> info = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    System.out.println(line);
                    info.add(line);
                } else {
                    break;
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseMenuItem(new MenuItem(), info, 0, 0);
    }

}
