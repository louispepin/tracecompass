package org.eclipse.tracecompass.internal.lttng2.kernel.ui.views.combinedview;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.tracecompass.tmf.ui.viewers.tree.AbstractTmfTreeViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.tree.ITmfTreeViewerEntry;
import org.eclipse.tracecompass.tmf.ui.viewers.tree.TmfTreeViewerEntry;
import org.eclipse.tracecompass.tmf.ui.views.statesystem.TmfStateSystemViewer;
import org.eclipse.tracecompass.statesystem.core.*;
import org.eclipse.tracecompass.tmf.core.analysis.IAnalysisModule;
import org.eclipse.tracecompass.tmf.core.timestamp.ITmfTimestamp;
import org.eclipse.tracecompass.tmf.core.trace.*;

import org.eclipse.tracecompass.lttng2.kernel.core.analysis.combined.module.*;

//import java.util.Collection;
//import java.util.Set;

/**
 * Viewer for the combined view
 *
 * @author Louis Pepin
 */
public class CombinedViewer extends TmfStateSystemViewer {

    private static ITmfTrace trace;

    private boolean fFilterStatus = false;
    private long fSelection = 0;

    /**
     * Default constructor
     *
     * @param parent
     *          The parent containing this viewer
     */
    public CombinedViewer(final Composite parent) {
        super(parent);
    }

    /**
     * Grabs the VM and container state systems from the
     * combined analysis
     *
     * @return The VM and container state systems
     */
    private static Iterable<ITmfStateSystem> getStateSystems() {
        trace = TmfTraceManager.getInstance().getActiveTrace();

        if (trace == null) {
            return null;
        }

        Iterable<ITmfStateSystem> stateSystems = null;
        for (IAnalysisModule am : trace.getAnalysisModules()) {
            if (am instanceof CombinedAnalysis) {
                stateSystems = ((CombinedAnalysis)am).getStateSystems();
            }
        }

        return stateSystems;
    }

    /**
     * TODO: Implement StateEntry and all its methods
     */
    @Override
    protected ITmfTreeViewerEntry updateElements(long start, long end, boolean selection) {

        if (selection) {
            fSelection = start;
        } else {
            TmfTraceContext ctx = TmfTraceManager.getInstance().getCurrentTraceContext();
            fSelection = ctx.getSelectionRange().getStartTime().normalize(0, ITmfTimestamp.NANOSECOND_SCALE).getValue();
        }

        if (getTrace() == null) {
            return null;
        }

        ITmfTreeViewerEntry root = getInput();

        if (root == null) {
            root = createRoot();
        } else if (fFilterStatus) {
            super.clearStateSystemEntries(root);
        }

        /*
         * Update the values of the elements of the state systems at the
         * selection start time
         */
        boolean changed = updateStateSystemEntries(root, fSelection);

        return selection || changed ? root : null;
    }

    /**
     * Creates root node for the tree viewer with one child
     * per state system we wish to show
     *
     * @return The root node
     */
    private static ITmfTreeViewerEntry createRoot() {
        TmfTreeViewerEntry rootEntry = new TmfTreeViewerEntry("root"); //$NON-NLS-1$
        TmfTreeViewerEntry traceEntry = new TmfTreeViewerEntry(trace.getName());
        for (ITmfStateSystem ss : getStateSystems()) {
            traceEntry.addChild(new StateSystemEntry(ss));
        }
        rootEntry.addChild(traceEntry);
        return rootEntry;
    }

    /**
     * Constructor for combined view entries, same as the one in
     * TmfStateSystemViewer
     */
    private static class StateSystemEntry extends TmfTreeViewerEntry {
        private final @NonNull ITmfStateSystem fSS;

        public StateSystemEntry(@NonNull ITmfStateSystem ss) {
            super(ss.getSSID());
            fSS = ss;
        }

        public @NonNull ITmfStateSystem getSS() {
            return fSS;
        }
    }
}
