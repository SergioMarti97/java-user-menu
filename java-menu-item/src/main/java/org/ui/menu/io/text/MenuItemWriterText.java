package org.ui.menu.io.text;

import org.ui.menu.MenuItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MenuItemWriterText {

    private String ident = "    ";

    private String SEP_STR = " ";

    private boolean isWriteWithDeque = false;

    private String toString(MenuItem mi, int ident) {
        // return String.format("%s%d%s%s\n", IDENT_STR.repeat(ident), mi.getId(), SEP_STR, mi.getName());
        return String.format("%s%s\n", this.ident.repeat(ident), mi.getName());
    }

    /**
     * Esta función es recursiva
     */
    public void write(BufferedWriter bw, MenuItem mi, int ident) throws IOException {
        bw.write(toString(mi, ident));
        if (mi.hasItems()) {
            ident++;
            for (var items : mi.getItems()) {
                write(bw, items, ident);
            }
        }
    }

    /**
     * Esta función no es recursiva
     */
    public void writeDeque(BufferedWriter bw, MenuItem mi, int ident) throws IOException {
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

    public void write(File file, MenuItem mi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            if (isWriteWithDeque) {
                writeDeque(bw, mi, 0);
            } else {
                write(bw, mi, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String filename, MenuItem mi) {
        write(new File(filename), mi);
    }

    // Getter & Setter

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public boolean isWriteWithDeque() {
        return isWriteWithDeque;
    }

    public void setWriteWithDeque(boolean writeWithDeque) {
        isWriteWithDeque = writeWithDeque;
    }

}
