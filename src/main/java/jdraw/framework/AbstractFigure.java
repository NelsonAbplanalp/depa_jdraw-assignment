/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Base interface for all figures implemented in the graphic editor.
 * Every Figure-type has to implement this interface.
 *
 * @author  Dominik Gruntz &amp; Christoph Denzler
 * @version 2.5
 */
public abstract class AbstractFigure {
	/** List of listeners interested in changes of rectangle. */
	private final List<FigureListener> listeners = new ArrayList<>();

	/**
	 * @param listener the figure listener.
	 */
	public void addFigureListener(FigureListener listener) {
		if(listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * @param listener the figure listener.
	 */
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @param evt	figure event to propagate
	 */
	protected void propagateFigureEvent(FigureEvent evt) {
		for(FigureListener listener : new ArrayList<>(listeners)) {
			listener.figureChanged(evt);
		}
	}
}
