// $Id$
/*
 * ====================================================================
 * Copyright (c) 2002-2004, Christophe Labouisse All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.ggtools.grand.ui.actions;

import java.util.Collection;

import net.ggtools.grand.ui.graph.Draw2dNode;
import net.ggtools.grand.ui.graph.GraphControlerProvider;
import net.ggtools.grand.ui.graph.GraphSelectionListener;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * An abstract class implementing basic features for action listening to a graph
 * selection. The default behavior of this class is to enable itself if there is
 * exactly one item selected.
 * 
 * @author Christophe Labouisse
 */
public abstract class GraphSelectionAction extends GraphListenerAction implements
        GraphSelectionListener {

    private String currentNode;

    /**
     * @param parent
     */
    public GraphSelectionAction(GraphControlerProvider parent) {
        super(parent);
        init();
    }

    /**
     * @param parent
     * @param text
     */
    public GraphSelectionAction(GraphControlerProvider parent, String text) {
        super(parent, text);
        init();
    }

    /**
     * @param parent
     * @param text
     * @param image
     */
    public GraphSelectionAction(GraphControlerProvider parent, String text, ImageDescriptor image) {
        super(parent, text, image);
        init();
    }

    /**
     * @param parent
     * @param text
     * @param style
     */
    public GraphSelectionAction(GraphControlerProvider parent, String text, int style) {
        super(parent, text, style);
        init();
    }

    /**
     * @return Returns the currentNode.
     */
    public final String getCurrentNode() {
        return currentNode;
    }

    /*
     * (non-Javadoc)
     * @see net.ggtools.grand.ui.graph.GraphSelectionListener#selectionChanged(java.util.Collection)
     */
    public void selectionChanged(Collection selectedNodes) {
        final boolean isEnabled = selectedNodes.size() == 1;
        if (isEnabled) {
            currentNode = ((Draw2dNode) selectedNodes.iterator().next()).getName();
        }
        setEnabled(isEnabled);
    }

    /**
     *  
     */
    final private void init() {
        boolean isEnabled = false;

        if (getGraphControler() != null) {
            Collection selectedNodes = getGraphControler().getSelection();
            isEnabled = selectedNodes.size() == 1;
            if (isEnabled) {
                currentNode = ((Draw2dNode) selectedNodes.iterator().next()).getName();
            }
        }
        setEnabled(isEnabled);
    }
}