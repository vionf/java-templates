package com.vionf.cdt.dsf.launch;

import org.eclipse.cdt.dsf.gdb.launching.GdbLaunch;
import org.eclipse.cdt.dsf.gdb.launching.ServicesLaunchSequence;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.core.runtime.IProgressMonitor;

public class ExtLaunchSequence extends ServicesLaunchSequence {

	private GdbLaunch fLaunch;

	public ExtLaunchSequence(DsfSession session, GdbLaunch launch, IProgressMonitor pm) {
		super(session, launch, pm);
		fLaunch = launch;
	}

	@Override
	public Step[] getSteps() {
		return super.getSteps();
//		// Add an extra step at the end to create the new service
//		Step[] steps = super.getSteps();
//		Step[] moreSteps = new Step[steps.length + 1];
//		System.arraycopy(steps, 0, moreSteps, 0, steps.length);
//		moreSteps[steps.length] = new Step() {
//			@Override
//			public void execute(RequestMonitor requestMonitor) {
//				fLaunch.getServiceFactory().createService(IGDBExtendedFunctions.class, fLaunch.getSession()).initialize(requestMonitor);
//			}
//		};
//		return moreSteps;

	}

}
