package org.ui.console;

import org.ui.menu.MenuItem;
import org.ui.menu.MenuManager;

/**
 * Esta clase sirve para mostrar en pantalla un MenuItem
 * Tiene una serie de características configurables
 */
public class MenuConsolePrinter {

    private String itemNameBracketLeft = "# --- ";

    private String itemNameBracketRight = " --- #";

    private String itemHeaderNameJoin = "";

    private String indexBracketLeft = "(";

    private String indexBracketRight = ")";

    private String indexJoin = " ";

    private String itemSeparator = " ";

    private boolean showItemHeaderName = true;

    private boolean itemHeaderNameLineBreak = true;

    private boolean showIndexes = true;

    private boolean showItemsIds = false;

    private boolean startIndexAtZero = false;

    private boolean showItemsNames = true;

    private boolean itemsOnOneLine = false;

    public String render(MenuItem item) {
        StringBuilder out = new StringBuilder();
        if (showItemHeaderName) {
            out.append(itemNameBracketLeft);
            out.append(item.getName());
            out.append(itemNameBracketRight);
            out.append(!itemHeaderNameLineBreak ? itemHeaderNameJoin : '\n');
        }
        if (showItemsIds || showItemsNames) {
            for (int i = 0; i < item.numItems(); i++) {
                if (showIndexes) {
                    out.append(indexBracketLeft);
                    out.append(showItemsIds ? item.getItem(i).getId() : (startIndexAtZero ? i : i + 1));
                    out.append(indexBracketRight);
                    out.append(indexJoin);
                }
                if (showItemsNames) {
                    out.append(item.getItem(i).getName());
                }
                out.append(itemsOnOneLine ? itemSeparator : '\n');
                //out.append('(').append(i + 1).append(") ").append(item.getItem(i).getName()).append('\n'); // <- Indices
                //out.append('(').append(item.getItem(i).getId()).append(") ").append(item.getItem(i).getName()); // <- IDs
            }
        }
        return out.toString();
    }

    public void show(MenuItem item) {
        System.out.print(render(item));
    }

    public void show(MenuManager mm) {
        if (mm.getStack().isEmpty()) {
            return;
        }
        show(mm.getStack().peek());
    }

    // Getters & Setters

    public String getItemNameBracketLeft() {
        return itemNameBracketLeft;
    }

    public void setItemNameBracketLeft(String itemNameBracketLeft) {
        this.itemNameBracketLeft = itemNameBracketLeft;
    }

    public String getItemNameBracketRight() {
        return itemNameBracketRight;
    }

    public void setItemNameBracketRight(String itemNameBracketRight) {
        this.itemNameBracketRight = itemNameBracketRight;
    }

    public String getItemHeaderNameJoin() {
        return itemHeaderNameJoin;
    }

    public void setItemHeaderNameJoin(String itemHeaderNameJoin) {
        this.itemHeaderNameJoin = itemHeaderNameJoin;
    }

    public String getIndexBracketLeft() {
        return indexBracketLeft;
    }

    public void setIndexBracketLeft(String indexBracketLeft) {
        this.indexBracketLeft = indexBracketLeft;
    }

    public String getIndexBracketRight() {
        return indexBracketRight;
    }

    public void setIndexBracketRight(String indexBracketRight) {
        this.indexBracketRight = indexBracketRight;
    }

    public String getIndexJoin() {
        return indexJoin;
    }

    public void setIndexJoin(String indexJoin) {
        this.indexJoin = indexJoin;
    }

    public String getItemSeparator() {
        return itemSeparator;
    }

    public void setItemSeparator(String itemSeparator) {
        this.itemSeparator = itemSeparator;
    }

    public boolean isShowItemHeaderName() {
        return showItemHeaderName;
    }

    public void setShowItemHeaderName(boolean showItemHeaderName) {
        this.showItemHeaderName = showItemHeaderName;
    }

    public boolean isItemHeaderNameLineBreak() {
        return itemHeaderNameLineBreak;
    }

    public void setItemHeaderNameLineBreak(boolean itemHeaderNameLineBreak) {
        this.itemHeaderNameLineBreak = itemHeaderNameLineBreak;
    }

    public boolean isShowIndexes() {
        return showIndexes;
    }

    public void setShowIndexes(boolean showIndexes) {
        this.showIndexes = showIndexes;
    }

    public boolean isShowItemsIds() {
        return showItemsIds;
    }

    public void setShowItemsIds(boolean showItemsIds) {
        this.showItemsIds = showItemsIds;
    }

    public boolean isStartIndexAtZero() {
        return startIndexAtZero;
    }

    public void setStartIndexAtZero(boolean startIndexAtZero) {
        this.startIndexAtZero = startIndexAtZero;
    }

    public boolean isShowItemsNames() {
        return showItemsNames;
    }

    public void setShowItemsNames(boolean showItemsNames) {
        this.showItemsNames = showItemsNames;
    }

    public boolean isItemsOnOneLine() {
        return itemsOnOneLine;
    }

    public void setItemsOnOneLine(boolean itemsOnOneLine) {
        this.itemsOnOneLine = itemsOnOneLine;
    }

}
