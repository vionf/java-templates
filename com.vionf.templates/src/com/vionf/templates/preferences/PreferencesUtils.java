/*******************************************************************************
 * Copyright (c) 2016 STMicroelectronics.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion - ST
 *******************************************************************************/
package com.vionf.templates.preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.vionf.templates.Activator;

/**
 * @author vionf
 *
 */
@SuppressWarnings("unused")
public class PreferencesUtils {
	
	
	/**
	 * Concrete implementation of a dialog settings (IDialogSettings) using a hash table and XML. 
	 * The dialog store can be read from and saved to a stream. All keys and values must be strings 
	 * or array of strings. Primitive types are converted to strings.
	 */
	public void dialogSetting() {
		IDialogSettings settings = Activator.getDefault().getDialogSettings();
		IDialogSettings idSection = settings.getSection("key");
		if (idSection == null) {
			idSection = new DialogSettings("key");
			idSection.put("subkey", "value");
			
			/* do something */
			
			settings.addSection(idSection);
		} else {
			String value = idSection.get("subkey");
		}
	}
	
	/**
	 * Config Scope
	 * 
	 * the location where Eclipse stores essential runtime metadata (such as information 
	 * about the set of plug-ins installed and the dependencies between them) and cached 
	 * data in general. Eclipse cannot run without a configuration area. Plug-ins may choose 
	 * to store data here that should be available regardless the workspace in use (for 
	 * instance, help index files). User settings shared across workspaces are also stored 
	 * under this location.
	 */
	public void configScope() {

		String userName = System.getProperty("user.name");
		
		Preferences preferences = ConfigurationScope.INSTANCE.getNode("com.flo.templates.preferences." + userName);
		Preferences idSection = preferences.node("one section");

		/* get */
		String value = idSection.get("key", "");
		/* set */
		idSection.put("key", "value");
		
		/* clear */
		try {
			idSection.clear();
		} catch (BackingStoreException e1) {
			e1.printStackTrace();
		}
		

		/* do something */

		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
}
