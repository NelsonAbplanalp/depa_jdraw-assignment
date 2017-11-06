/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Nelson Abplanalp
 *
 */
public class StdDrawModel implements DrawModel, FigureListener {
	/** List of figures contained in the drawing. */
	private final List<Figure> figures = new LinkedList<>();

    /** List of listeners interested in changes of any figure. */
    private final List<DrawModelListener> listeners = new ArrayList<>();

    // **************************************************************
    // Figure methods
    // **************************************************************
    /**
     * @param f figure to be added to draw model.
     */
	@Override
	public void addFigure(Figure f) {
        if (f != null && !figures.contains(f)) {
            figures.add(f);
            f.addFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
        }
	}

    /**
     * @return Iterable<Figure>
     */
	@Override
	public Iterable<Figure> getFigures() {
		return figures;
	}

    /**
     * @param f figure to be removed from draw model.
     */
	@Override
	public void removeFigure(Figure f) {
        if (figures.remove(f)) {
            f.removeFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
	}

    @Override
    public void removeAllFigures() {
	    for (Figure f : new LinkedList<Figure>(figures)) {
            f.removeFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.DRAWING_CLEARED);
        }
        figures.clear();
    }

    /**
     * @param f         the figure whose index has to be set
     * @param index     the position at which the new figure should appear. The other
     *                  figures are moved away.
     */
    @Override
    public void setFigureIndex(Figure f, int index) {
        if(index< 0 || index>= figures.size()) {
            throw new IndexOutOfBoundsException();
        }

        int pos= figures.indexOf(f);
        if (pos< 0) {
            throw new IllegalArgumentException(
                "Figure f not contained in model");
        }

        if (pos!= index) {
            figures.remove(f);
            figures.add(index, f);
            notifyListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
        }
    }

    // **************************************************************
    // Change listener methods
    // **************************************************************
    /**
     * @param listener the draw model listener.
     */
	@Override
	public void addModelChangeListener(DrawModelListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
	}

    /**
     * @param listener the draw model listener.
     */
	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
        listeners.remove(listener);
	}

    /**
     * @param f     changed figure
     * @param type  DrawModelEvent type
     */
    private void notifyListeners(Figure f, DrawModelEvent.Type type) {
        DrawModelEvent dme = new DrawModelEvent(this, f, type);

        for (DrawModelListener l : new ArrayList<>(listeners)) {
            l.modelChanged(dme);
        }
    }

    /**
     * @param e figure event
     */
    @Override
    public void figureChanged(FigureEvent e) {
        notifyListeners(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
    }

    // **************************************************************
    // Draw handler methods
    // **************************************************************
	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}
}
