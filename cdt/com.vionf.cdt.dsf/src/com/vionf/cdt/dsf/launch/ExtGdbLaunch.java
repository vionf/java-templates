package com.vionf.cdt.dsf.launch;

import org.eclipse.cdt.dsf.concurrent.ThreadSafe;
import org.eclipse.cdt.dsf.gdb.launching.GdbLaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ISourceLocator;

@ThreadSafe
public class ExtGdbLaunch extends GdbLaunch {

	public ExtGdbLaunch(ILaunchConfiguration launchConfiguration, String mode, ISourceLocator locator) {
		super(launchConfiguration, mode, locator);
	}

}
