package com.vionf.cdt.dsf.actions;

import org.eclipse.cdt.dsf.gdb.internal.ui.actions.DsfTerminateCommand;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.commands.IDebugCommandRequest;

public class ExtTerminateCommand extends DsfTerminateCommand {

	public ExtTerminateCommand(DsfSession session) {
		super(session);
	}

	@Override
	public boolean execute(IDebugCommandRequest request) {
		return super.execute(request);
	}
}
