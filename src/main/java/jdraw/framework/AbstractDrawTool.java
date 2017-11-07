/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Base interface for all figures implemented in the graphic editor.
 * Every Figure-type has to implement this interface.
 *
 * @author Nelson Abplanalp
 * @version 2.5
 */
public abstract class AbstractDrawTool implements DrawTool {
    private static final String IMAGES = "/images/";
    private final String name;
    private final String icon;

    protected AbstractDrawTool(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Icon getIcon() {
        if (icon != null) {
            return new ImageIcon(getClass().getResource(IMAGES + icon));
        } else {
            return null;
        }
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    /**
     * Activates the Figure Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * figure attributes
     */
    @Override
    public void activate() {}

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     */
    @Override
    public void deactivate() {}

    /**
     * Initializes a new Figure object by setting an anchor
     * point where the mouse was pressed. A new Figure is then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     */
    @Override
    public void mouseDown(int x, int y, MouseEvent e) {}

    /**
     * During a mouse drag, the Rectangle will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     */
    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {}

    /**
     * When the user releases the mouse, the Rectangle object is updated
     * according to the color and fill status settings.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     */
    @Override
    public void mouseUp(int x, int y, MouseEvent e) {}
}
