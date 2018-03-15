package com.vionf.cdt.dsf.actions;

import org.eclipse.cdt.dsf.debug.ui.actions.DsfStepOverCommand;
import org.eclipse.cdt.dsf.debug.ui.actions.DsfSteppingModeTarget;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.commands.IDebugCommandRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ExtStepOverHandler extends DsfStepOverCommand {

	public ExtStepOverHandler(DsfSession session, DsfSteppingModeTarget steppingMode) {
		super(session, steppingMode);
	}

	@Override
	public boolean execute(IDebugCommandRequest request) {
		final Display display = PlatformUI.isWorkbenchRunning() ? PlatformUI.getWorkbench().getDisplay() : null;
		if (display != null) {
			display.syncExec(new Runnable() {
				@Override
				public void run() {
					Shell shell = display.getActiveShell();
					if (shell != null) {
						boolean confirmed = MessageDialog.openConfirm(
								shell,
								"Step Over Confirmation",
								"Are you sure to do a step over actions?"
								);
						if (!confirmed) {
							request.cancel();
							return;
						}
					}
					ExtStepOverHandler.super.execute(request);
				}
			});
		}
		return true;
	}
}
