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
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.core.IFolderInfo;
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

		IBuildObject buildObject = tool.getParent();
		if (buildObject instanceof IToolChain) {
			IToolChain toolchain = (IToolChain)buildObject;
			
			/* Get non persistent option */
			IConfiguration parent = toolchain.getParent();
			IFolderInfo rootFolderInfo = parent.getRootFolderInfo();
			IToolChain toolChainParent = rootFolderInfo.getToolChain();
			IOption optionBySuperClassId = toolChainParent.getOptionBySuperClassId("com.vionf.cdt.managedBuilder.option");
			if (optionBySuperClassId != null) {
				try {
					listFlags.add(0, optionBySuperClassId.getCommand(optionBySuperClassId.getSelectedEnum()));
				} catch (BuildException e) {
					e.printStackTrace();
				}
			}
			
			/* old way but does not work, the getOptionBySuperClassID return null if the folderInfo is not the same between
			 * the parent option and the new option */
//			IToolChain superClass = toolchain.getSuperClass();
//			IOption optionNonPersistent = toolchain.getOptionBySuperClassId("com.vionf.cdt.managedBuilder.option");
//			if (optionNonPersistent != null) {
//				try {
//					String command = optionNonPersistent.getCommand(optionNonPersistent.getSelectedEnum());
//					listFlags.add(0, optionNonPersistent.getCommand(optionNonPersistent.getSelectedEnum()));
//				} catch (BuildException e) {
//					e.printStackTrace();
//				}
//			} else {
//				System.out.println("no option");
//			}
		}
		
		String[] newFlags = new String[listFlags.size()];
		
		return super.generateCommandLineInfo(tool, commandName, listFlags.toArray(newFlags), outputFlag,
				outputPrefix, outputName, inputResources, commandLinePattern);
	}

}
