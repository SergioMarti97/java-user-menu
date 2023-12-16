package org.ui.menu.table;

import org.geom.vector.vec2d.Vec2di;
import org.ui.menu.MenuItem;

public class MenuItemTable extends MenuItem implements ITable<MenuItem> {

    protected Vec2di table = new Vec2di(1, 0);

    @Override
    public int getNumRows() {
        return table.getY();
    }

    @Override
    public int getNumCols() {
        return table.getX();
    }

    @Override
    public MenuItem setNumRows(int numRows) {
        table.setY(numRows);
        return this;
    }

    @Override
    public MenuItem setNumCols(int numCols) {
        table.setX(numCols);
        return this;
    }

    @Override
    public MenuItem setTable(int numRows, int numCols) {
        table.set(numCols, numRows);
        return this;
    }

    // Getters

    public Vec2di getTable() {
        return table;
    }

    public void setTable(Vec2di table) {
        this.table = table;
    }

}
