/**********************************************************************
 * Copyright (c) 2013, 2014 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Bernd Hufmann - Initial API and implementation
 **********************************************************************/
package org.eclipse.tracecompass.internal.lttng2.control.core.model;

/**
 * <p>
 * Interface for retrieve trace comon information.
 * </p>
 *
 * @author Bernd Hufmann
 */
public interface ITraceInfo {
    /**
     * @return the name of the information element.
     */
    String getName();

    /**
     * Sets the name of the information element.
     *
     * @param name
     *            The name to assign
     */
    void setName(String name);

}
