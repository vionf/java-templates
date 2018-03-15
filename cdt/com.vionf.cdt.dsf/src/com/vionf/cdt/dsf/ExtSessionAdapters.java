package com.vionf.cdt.dsf;

import org.eclipse.cdt.dsf.gdb.internal.ui.GdbSessionAdapters;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.commands.IStepOverHandler;
import org.eclipse.debug.core.commands.ITerminateHandler;

import com.vionf.cdt.dsf.actions.ExtStepOverHandler;
import com.vionf.cdt.dsf.actions.ExtTerminateCommand;

@SuppressWarnings("restriction")
public class ExtSessionAdapters extends GdbSessionAdapters {

	public ExtSessionAdapters(ILaunch launch, DsfSession session, Class<?>[] launchAdapterTypes) {
		super(launch, session, launchAdapterTypes);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createModelAdapter(Class<T> adapterType, ILaunch launch, DsfSession session) {
		if (ITerminateHandler.class.equals(adapterType)) { 
			return (T)new ExtTerminateCommand(session);
		}
		if (IStepOverHandler.class.equals(adapterType)) {
			return (T)new ExtStepOverHandler(session, getSteppingModeTarget());
		}
		return super.createModelAdapter(adapterType, launch, session);
	}

}
