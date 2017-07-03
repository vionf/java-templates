/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion
 *******************************************************************************/
package com.vionf.cdt.managedbuilder;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.cdt.managedbuilder.core.BuildException;
import org.eclipse.cdt.managedbuilder.core.IBuildObject;
import org.eclipse.cdt.managedbuilder.core.IManagedCommandLineInfo;
import org.eclipse.cdt.managedbuilder.core.IOption;
import org.eclipse.cdt.managedbuilder.core.ITool;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.cdt.managedbuilder.internal.core.ManagedCommandLineGenerator;

/**
 * 
 * @author vionf
 *
 */
@SuppressWarnings("restriction")
public class CommandLineGenerator extends ManagedCommandLineGenerator {

	@Override
	public IManagedCommandLineInfo generateCommandLineInfo(ITool tool, String commandName, String[] flags,
			String outputFlag, String outputPrefix, String outputName, String[] inputResources,
			String commandLinePattern) {
		
		ArrayList<String> listFlags = new ArrayList<String>(Arrays.asList(flags));

		IBuildObject parent = tool.getParent();
		if (parent instanceof IToolChain) {
			IToolChain toolchain = (IToolChain)parent;
			
			/* Get non persistent option */
			IOption optionNonPersistent = toolchain.getOptionBySuperClassId("com.vionf.cdt.managedBuilder.option");
			if (optionNonPersistent != null) {
				try {
					listFlags.add(0, optionNonPersistent.getCommand(optionNonPersistent.getSelectedEnum()));
				} catch (BuildException e) {
					e.printStackTrace();
				}
			}
		}
		
		String[] newFlags = new String[listFlags.size()];
		
		return super.generateCommandLineInfo(tool, commandName, listFlags.toArray(newFlags), outputFlag,
				outputPrefix, outputName, inputResources, commandLinePattern);
	}

}
