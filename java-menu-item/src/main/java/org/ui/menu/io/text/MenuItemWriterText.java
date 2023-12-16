package org.ui.menu.io.text;

import org.ui.menu.MenuItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MenuItemWriterText {

    public static String IDENT_STR = "    ";

    public static String SEP_STR = " ";

    public static String toString(MenuItem mi, int ident) {
        // return String.format("%s%d%s%s\n", IDENT_STR.repeat(ident), mi.getId(), SEP_STR, mi.getName());
        return String.format("%s%s\n", IDENT_STR.repeat(ident), mi.getName());
    }

    public static void write(BufferedWriter bw, MenuItem mi, int ident) throws IOException {
        bw.write(toString(mi, ident));
        if (mi.hasItems()) {
            ident++;
            for (var items : mi.getItems()) {
                write(bw, items, ident);
            }
        }
    }

    public static void write(String filename, MenuItem mi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            write(bw, mi, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDeque(BufferedWriter bw, MenuItem mi, int ident) throws IOException {
        if (mi == null) {
            return;
        }

        Stack<Map.Entry<MenuItem, Integer>> stack = new Stack<>();
        stack.push(Map.entry(mi, ident));

        while (!stack.isEmpty()) {
            var e = stack.pop();
            MenuItem child = e.getKey();
            int childIdent = e.getValue();
            bw.write(toString(child, childIdent));

            if (child.hasItems()) {
                for (var item : child.getItems()) {
                    stack.push(Map.entry(item, childIdent + 1));
                }
            }
        }
    }

    public static void writeDeque(String filename, MenuItem mi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            writeDeque(bw, mi, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
