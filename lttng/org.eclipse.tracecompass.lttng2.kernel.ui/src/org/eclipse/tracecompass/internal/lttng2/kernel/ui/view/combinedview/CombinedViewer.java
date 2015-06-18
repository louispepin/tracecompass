package org.eclipse.tracecompass.internal.lttng2.kernel.ui.view.combinedview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.tracecompass.tmf.ui.views.statesystem.*;

/**
 * Viewer for the combined view
 *
 * @author Louis Pepin
 */
public class CombinedViewer {

    TmfStateSystemViewer viewer;
    Combo bouton;

    /**
     * Default constructor
     *
     * @param parent
     *          Parent composite in which the viewer is shown
     */
    public CombinedViewer(final Composite parent) {

        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        parent.setLayout(layout);

        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        parent.setLayoutData(layoutData);

        String[] items = {"Virtual Machine View", "Container View"};
        bouton = new Combo(parent, SWT.READ_ONLY);
        bouton.setItems(items);
        bouton.select(0);

        viewer = new TmfStateSystemViewer(parent);
        viewer.getControl().setLayoutData(layoutData);

        bouton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println(bouton.getText());

                //TODO: Manipulate trace/statesystem here
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}
        });
    }
}
