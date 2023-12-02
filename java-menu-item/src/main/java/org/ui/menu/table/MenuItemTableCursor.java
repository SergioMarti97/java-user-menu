package org.ui.menu.table;

import org.geom.vector.vec2d.Vec2di;
import org.ui.menu.MenuItem;
import org.ui.menu.cursor.ICursor;

public class MenuItemTableCursor extends MenuItemTable implements ICursor<MenuItem> {

    protected Vec2di cursor = new Vec2di();

    protected int numCursorItem = 0;

    protected void clampCursor() {
        numCursorItem = cursor.getY() * table.getX() + cursor.getX();

        if (numCursorItem >= items.size()) {
            cursor.setY(items.size() / table.getX());
            cursor.setX((items.size() % table.getX()) - 1);
            numCursorItem = items.size() - 1;
        }
    }

    @Override
    public void onUp() {
        cursor.addToY(-1);
        if (cursor.getY() < 0) {
            cursor.setY(0);
        }
        clampCursor();
    }

    @Override
    public void onDown() {
        cursor.addToY(1);
        int numTotalRows = (items.size() / table.getX()) + (((items.size() % table.getX()) > 0) ? 1 : 0);
        if (cursor.getY() == numTotalRows) {
            cursor.setY(numTotalRows - 1);
        }
        clampCursor();
    }

    @Override
    public void onLeft() {
        cursor.addToX(-1);
        if (cursor.getX() < 0) {
            cursor.setX(0);
        }
        clampCursor();
    }

    @Override
    public void onRight() {
        cursor.addToX(1);
        if (cursor.getX() == table.getX()) {
            cursor.setX(table.getX() - 1);
        }
        clampCursor();
    }

    @Override
    public MenuItem onConfirm() {
        return onConfirm(numCursorItem);
    }

    public MenuItem getSelectedItem() {
        return items.get(numCursorItem);
    }

    // Getters & Setters

    public Vec2di getCursor() {
        return cursor;
    }

    public void setCursor(Vec2di cursor) {
        this.cursor = cursor;
    }

}
