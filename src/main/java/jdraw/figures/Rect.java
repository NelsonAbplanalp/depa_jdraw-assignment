/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.*;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends AbstractRectangularFigure {
    /**
     * @param p position of created rect
     */
    public Rect(Point p) {
        super(p);
    }

    /**
     * @param g Graphics object on which figure has to be drawn.
     */
    @Override
    public void draw(Graphics g) {
        Rectangle r = getBounds();
        g.setColor(Color.white);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.black);
        g.drawRect(r.x, r.y, r.width, r.height);
    }
}
