/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.framework;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Base interface for all figures implemented in the graphic editor.
 * Every Figure-type has to implement this interface.
 *
 * @author Nelson Abplanalp
 * @version 2.5
 */
public abstract class AbstractDragDrawTool extends AbstractDrawTool {
    // The context we use for drawing.
    private final DrawContext context;

    // During rectangle creation this variable refers to the point the
	// mouse was first pressed.
    private Point anchor;

    private Figure figure;

    protected AbstractDragDrawTool(DrawContext context, String name, String icon) {
        super(name, icon);
        this.context = context;
    }

    @Override
    public void activate() {
        context.showStatusText(getName() + " Mode");
    }

    @Override
    public void deactivate() {
        context.showStatusText("");
    }

    /**
     * @param p position of created figure
     */
    protected abstract Figure createFigure(Point p);

    /**
     * @param x coordinate x of position
     * @param y coordinate x of position
     * @param e MouseEvent for mouseDrag
     */
    @Override
    public final void mouseDown(int x, int y, MouseEvent e) {
        if (figure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        figure = createFigure(anchor);
        context.getModel().addFigure(figure);
    }

    /**
     * @param x coordinate x of position
     * @param y coordinate x of position
     * @param e MouseEvent for mouseDrag
     */
    @Override
    public final void mouseDrag(int x, int y, MouseEvent e) {
        figure.setBounds(anchor, new Point(x, y));
    }

    /**
     * @param x coordinate x of position
     * @param y coordinate x of position
     * @param e MouseEvent for mouseDrag
     */
    @Override
    public final void mouseUp(int x, int y, MouseEvent e) {
        java.awt.Rectangle r = figure.getBounds();
        if (r.width== 0 && r.height== 0) {
            context.getModel().removeFigure(figure);
        }
        anchor = null;
        figure = null;
    }
}
