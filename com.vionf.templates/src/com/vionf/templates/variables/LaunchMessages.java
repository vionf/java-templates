/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion
 *******************************************************************************/
package com.vionf.templates.variables;

import org.eclipse.osgi.util.NLS;

/**
 * Common superclass for all message bundle classes. Provides convenience methods for manipulating messages.
 * Ex to use it, simply call: 
 * 		LaunchMessages.my_variable
 */
public class LaunchMessages extends NLS {

	public static String my_variable;

	/**
	 * Constructor
	 */
	private LaunchMessages() {}

	static {
		/* Load message values from bundle file */
		NLS.initializeMessages(LaunchMessages.class.getCanonicalName(), LaunchMessages.class);
	}
}
