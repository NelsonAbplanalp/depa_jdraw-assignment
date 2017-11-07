/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.framework;

import java.awt.*;

/**
 * Base interface for all figures implemented in the graphic editor.
 * Every Figure-type has to implement this interface.
 *
 * @author Nelson Abplanalp
 * @version 2.5
 */
public abstract class AbstractRectangularFigure extends AbstractFigure {
    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private final Rectangle rectangle;

    protected AbstractRectangularFigure(Point origin) {
        rectangle = new Rectangle(origin);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle original = new Rectangle(rectangle);
        rectangle.setFrameFromDiagonal(origin, corner);

        if (!original.equals(rectangle)) { // notification only if
            propagateFigureEvent();// there is a change
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) { // notification only if changed
            rectangle.translate(dx, dy);
            propagateFigureEvent();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(rectangle);
    }
}
