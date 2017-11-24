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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 *
 * Stream Gobbler empties any stream passed into the InputStream. 
 *
 * @author vionf
 *
 */
public class StreamGobbler extends Thread {
	final MessageConsole console;
	InputStream is;
	
	StreamGobbler(final MessageConsole console, InputStream is) {
		this.is = is;
		this.console = console;
	}

	public void run() {
    	try	{
    		MessageConsoleStream out = console.newMessageStream();
    		InputStreamReader isr = new InputStreamReader(is);
    		BufferedReader br = new BufferedReader(isr);
    		String line=null;
    		while ( (line = br.readLine()) != null) {
    			out.println(line);
    			//System.out.println(line);
    		}
    	} catch (IOException ioe) {
    		ioe.printStackTrace();  
    	}
    }
}
