package org.ui.menu.io.text;

import org.ui.menu.MenuItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuItemReaderText {

    public static Pattern FOUR_SPACES_PATTERN = Pattern.compile("^ {4}");

    public static String SEP_STR = " ";

    public static int countIdent(String line) {
        Matcher countIdentMatcher = FOUR_SPACES_PATTERN.matcher(line);
        int count = 0;
        while (countIdentMatcher.find()) {
            count++;
        }
        return count;
    }

    public static MenuItem read(String filename) {
        MenuItem mi = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Stack<MenuItem> stack = new Stack<>();

            int count = 0, actualLevel = 0;
            String line = br.readLine();
            while (line != null) {
                int ident = countIdent(line);
                line = line.replaceAll(FOUR_SPACES_PATTERN.pattern(), "");

                if (count == 0) {
                    actualLevel = ident;
                    mi = new MenuItem(line);
                    stack.push(mi);
                } else {
                    /*if (ident == actualLevel) {
                        stack.pop();
                    } else if (ident < actualLevel) {
                        stack.pop();
                        stack.pop();
                    }*/ // código anterior
                    // código nuevo
                    if (!stack.isEmpty()) { // seguro?
                        if (ident <= actualLevel) {
                            stack.pop();
                            if (ident != actualLevel) {
                                stack.pop();
                            }
                        }
                    }

                    MenuItem child = stack.peek().add(line);
                    stack.push(child);
                    actualLevel = ident;
                }

                line = br.readLine();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mi;
    }

}
