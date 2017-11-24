/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion
 *******************************************************************************/
package com.vionf.debug.console;

import java.io.IOException;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;


/**
 * 
 * @author vionf
 *
 */
public class Console {
	
	private static final String CONSOLE_NAME = "My Console Name";
	
	public void console() {
		try {
			String[] cmdarray = new String[] {"path of the process" + "options"};
			Process proc = Runtime.getRuntime().exec(cmdarray);
			final MessageConsole console = findConsole(CONSOLE_NAME);
			if (console != null) {
				StreamGobbler errorGobbler = new 
		                StreamGobbler(console, proc.getErrorStream());
				StreamGobbler inputGobbler = new 
		                StreamGobbler(console, proc.getInputStream());
				errorGobbler.start();
	            inputGobbler.start();
			}
			/* In some cases, proc needs to wait the end of cmdarray to cacth all the output */
			proc.waitFor();
			/* close the console at the end */
			closeConsole(console);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MessageConsole findConsole(final String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		/* no console found, so create a new one */
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[]{myConsole});
		return myConsole;
	}
	
	private void closeConsole(final MessageConsole console) {
		if (console == null) {
			return;
		}
		
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		conMan.removeConsoles(new IConsole[] {console});
	}
}
