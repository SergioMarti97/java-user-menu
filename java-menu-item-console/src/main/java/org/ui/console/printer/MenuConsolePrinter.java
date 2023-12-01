package org.ui.console.printer;

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

    private int incSpacesToIdent = 2;

    private boolean showItemHeaderName = true;

    private boolean itemHeaderNameLineBreak = true;

    private boolean showIndexes = true;

    private boolean showItemsIds = false;

    private boolean startIndexAtZero = false;

    private boolean showItemsNames = true;

    private boolean itemsOnOneLine = false;

    private boolean expandChildren = false;

    public MenuConsolePrinter() {
    }

    public MenuConsolePrinter(MenuConsolePrinter other) {
        this.itemNameBracketLeft = other.itemNameBracketLeft;
        this.itemNameBracketRight = other.itemNameBracketRight;
        this.itemHeaderNameJoin = other.itemHeaderNameJoin;
        this.indexBracketLeft = other.indexBracketLeft;
        this.indexBracketRight = other.indexBracketRight;
        this.indexJoin = other.indexJoin;
        this.itemSeparator = other.itemSeparator;
        this.incSpacesToIdent = other.incSpacesToIdent;
        this.showItemHeaderName = other.showItemHeaderName;
        this.itemHeaderNameLineBreak = other.itemHeaderNameLineBreak;
        this.showIndexes = other.showIndexes;
        this.showItemsIds = other.showItemsIds;
        this.startIndexAtZero = other.startIndexAtZero;
        this.showItemsNames = other.showItemsNames;
        this.itemsOnOneLine = other.itemsOnOneLine;
        this.expandChildren = other.expandChildren;
    }

    public String render(MenuItem item, int numSpacesToIdent) {
        StringBuilder out = new StringBuilder();
        String ident = " ".repeat(numSpacesToIdent);
        if (showItemHeaderName) {
            out.append(ident);
            out.append(itemNameBracketLeft);
            out.append(item.getName());
            out.append(itemNameBracketRight);
            out.append(!itemHeaderNameLineBreak ? itemHeaderNameJoin : '\n');
        }
        if (showItemsIds || showItemsNames) {
            if (itemsOnOneLine) {
                out.append(ident);
            }
            int childNumSpacesToIdent = (numSpacesToIdent + incSpacesToIdent);
            for (int i = 0; i < item.numItems(); i++) {
                if (!itemsOnOneLine) {
                    out.append(ident);
                }

                out.append(indexBracketLeft);
                if (showIndexes) {
                    out.append(showItemsIds ? item.getItem(i).getId() : (startIndexAtZero ? i : i + 1));
                }
                out.append(indexBracketRight);
                out.append(indexJoin);

                if (showItemsNames) {
                    out.append(item.getItem(i).getName());
                }
                out.append(itemsOnOneLine ? itemSeparator : '\n');

                if (expandChildren) {
                    MenuConsolePrinter mcp = new MenuConsolePrinter(this);
                    mcp.setShowItemHeaderName(false);
                    String child = mcp.render(item.getItem(i), itemsOnOneLine ? 0 : childNumSpacesToIdent);
                    if (itemsOnOneLine) {
                        out.append(itemSeparator);
                    }
                    out.append(child);
                }
                //out.append('(').append(i + 1).append(") ").append(item.getItem(i).getName()).append('\n'); // <- Indices
                //out.append('(').append(item.getItem(i).getId()).append(") ").append(item.getItem(i).getName()); // <- IDs
            }
        }
        return out.toString();
    }

    public String render(MenuItem item) {
        return render(item, 0);
    }

    public void show(MenuItem item, int numSpacesToIdent) {
        System.out.print(render(item, numSpacesToIdent));
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

    public boolean isExpandChildren() {
        return expandChildren;
    }

    public void setExpandChildren(boolean expandChildren) {
        this.expandChildren = expandChildren;
    }

    public int getIncSpacesToIdent() {
        return incSpacesToIdent;
    }

    public void setIncSpacesToIdent(int incSpacesToIdent) {
        this.incSpacesToIdent = incSpacesToIdent;
    }

    /*public void show() {
        StringBuilder out = new StringBuilder();
        out.append("# --- ").append(name).append(" --- #\n");
        for (int i = 0; i < items.size(); i++) {
            // Indices
            out.append('(').append(i + 1).append(") ").append(items.get(i).getName()).append('\n');
        }
        System.out.print(out);
    }*/

}
