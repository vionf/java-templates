/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion
 *******************************************************************************/
package com.vionf.debugtracingfacility;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 * 
 * @author vionf
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID 		= "com.vionf.debugTracingFacility"; 		//$NON-NLS-1$
	public static final String DEBUG_PLUGIN_ID 	= "com.vionf.debugTracingFacility/debug"; 	//$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private Boolean isDebug;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	//================================================================================
	// Debug Tracing Facility
	//================================================================================

	public static void log(IStatus status) {
		ResourcesPlugin.getPlugin().getLog().log(status);
	}

	public static void debugLog(String debugOption, String message) {
		if (getDefault().isDebugging()) {
			String opt = Platform.getDebugOption(debugOption);
			if ("true".equalsIgnoreCase(opt)) {
				log(new Status(Status.INFO, PLUGIN_ID, message));
			}
		}
	}

	@Override
	public boolean isDebugging() {
		if (isDebug == null) {
			isDebug = super.isDebugging();
		}
		return isDebug;
	}

	//================================================================================
	// ### To activate the trace ###
	// 1) Declare a list of the debug options that you want to turn on in .option file (the same as declared in static field on this class)
	//    These options will be available in debug configuration, tracing tab or 
	//    with -debug option passed to eclipse (in that case .option file must be included in build.properties)
	// 2) To call the debugLog method in your classes, add the following line in your code:
	//    Activator.debugLog(PLUGIN_ID.DEBUG_PLUGIN_ID, "My message without argument");
	//    Activator.debugLog(PLUGIN_ID.DEBUG_PLUGIN_ID, String.format("My message with argument '%s'", myStringVariable));
	//================================================================================
}
