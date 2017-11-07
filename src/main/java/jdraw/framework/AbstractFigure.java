/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Base interface for all figures implemented in the graphic editor.
 * Every Figure-type has to implement this interface.
 *
 * @author  Dominik Gruntz &amp; Christoph Denzler
 * @version 2.5
 */
public abstract class AbstractFigure implements Figure {
	/** List of listeners interested in changes of rectangle. */
	private final List<FigureListener> listeners = new CopyOnWriteArrayList<>();

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

	protected void propagateFigureEvent() {
		FigureEvent evt = new FigureEvent(this);
		for(FigureListener listener : new ArrayList<>(listeners)) {
			listener.figureChanged(evt);
		}
	}

    @Override
    public Figure clone() {
        return null;
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }
}
