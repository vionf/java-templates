package com.vionf.cdt.dsf;

import org.eclipse.cdt.dsf.concurrent.ThreadSafe;
import org.eclipse.cdt.dsf.gdb.internal.ui.GdbAdapterFactory;
import org.eclipse.cdt.dsf.gdb.internal.ui.GdbSessionAdapters;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunch;

@SuppressWarnings("restriction")
@ThreadSafe
public class ExtAdapterFactory extends GdbAdapterFactory {

	@Override
	protected GdbSessionAdapters createGdbSessionAdapters(ILaunch launch, DsfSession session) {
		return new ExtSessionAdapters(launch, session, getAdapterList());
	}

	@Override
	public void launchesTerminated(ILaunch[] launches) {
		super.launchesTerminated(launches);
	}
}
