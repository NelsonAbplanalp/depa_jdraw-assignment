/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.AbstractFigure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static oracle.jrockit.jfr.events.Bits.doubleValue;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Line extends AbstractFigure {
	private final Line2D.Double line;

	/**
	 * @param origin position of created rect
	 */
	public Line(Point origin) {
		line = new Line2D.Double(origin, origin);
	}

	/**
	 * @param g Graphics object on which figure has to be drawn.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setColor(Color.black);
		g.drawLine(intValue(line.x1), intValue(line.y1), intValue(line.x2), intValue(line.y2));
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		Rectangle original = line.getBounds();
		line.setLine(origin, corner);

		// notification only if there is a change
		if (!original.equals(line.getBounds())) {
			propagateFigureEvent();
		}
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) { // notification only if changed
            line.setLine(line.x1 + dx, line.y1 + dy, line.x2 + dx, line.y2 + dy);
			propagateFigureEvent();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return 5 >= line.ptLineDist(doubleValue(x), doubleValue(y));
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}
}
