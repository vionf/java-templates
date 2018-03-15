package com.vionf.cdt.dsf.launch;

import org.eclipse.cdt.dsf.concurrent.Sequence;
import org.eclipse.cdt.dsf.debug.service.IDsfDebugServicesFactory;
import org.eclipse.cdt.dsf.gdb.launching.GdbLaunch;
import org.eclipse.cdt.dsf.gdb.launching.GdbLaunchDelegate;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ISourceLocator;

public class ExtLaunchDelegate extends GdbLaunchDelegate {

	public ExtLaunchDelegate() {
		super();	
	}

	@Override
	protected GdbLaunch createGdbLaunch(ILaunchConfiguration configuration, String mode, ISourceLocator locator)
			throws CoreException {
		return new ExtGdbLaunch(configuration, mode, locator);
	}

	@Override
	protected Sequence getServicesSequence(DsfSession session, ILaunch launch, IProgressMonitor rm) {
		return super.getServicesSequence(session, launch, rm);
	}

	@Override
	protected IDsfDebugServicesFactory newServiceFactory(ILaunchConfiguration config, String version) {
		return super.newServiceFactory(config, version);
	}
}
