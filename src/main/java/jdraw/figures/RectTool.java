/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Point;

import jdraw.framework.AbstractDragDrawTool;
import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class RectTool extends AbstractDragDrawTool {
	/**
	 * Create a new rectangle tool for the given context.
	 * @param context a context to use this tool in.
	 * @param name    name of tool
	 * @param icon    icon for oval tool
	 */
	public RectTool(DrawContext context, String name, String icon) {
		super(context, name, icon);
	}

	@Override
	protected Rect createFigure(Point p) {
		return new Rect(p);
	}

}
