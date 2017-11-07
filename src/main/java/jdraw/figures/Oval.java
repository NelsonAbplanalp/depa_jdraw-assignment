/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.*;
import java.awt.*;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Oval extends AbstractRectangularFigure {
	/**
	 * @param p position of created rect
	 */
	public Oval(Point p) {
		super(p);
	}

	/**
	 * @param g Graphics object on which figure has to be drawn.
	 */
	@Override
	public void draw(Graphics g) {
	    Rectangle r = getBounds();
		g.setColor(Color.white);
		g.fillOval(r.x, r.y, r.width, r.height);
		g.setColor(Color.black);
		g.drawOval(r.x, r.y, r.width, r.height);
	}
}
