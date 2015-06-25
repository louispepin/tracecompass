package org.eclipse.tracecompass.internal.lttng2.kernel.ui.views.combinedview;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.tmf.ui.views.statesystem.TmfStateSystemViewer;


//import java.util.Collection;
//import java.util.Set;

/**
 * Viewer for the combined view
 *
 * @author Louis Pepin
 */
public class CombinedViewer extends TmfStateSystemViewer {

    /**
     * Default constructor
     *
     * @param parent
     *          The parent containing this viewer
     */
    public CombinedViewer(final Composite parent) {
        super(parent);
    }

}
