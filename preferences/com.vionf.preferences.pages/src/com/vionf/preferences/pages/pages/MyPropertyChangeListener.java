/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florent Vion
 *******************************************************************************/
package com.vionf.preferences.pages.pages;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * @author vionf
 *
 */
public class MyPropertyChangeListener implements IPropertyChangeListener {
	
	private MyPreferencePage page;
	
	/**
	 * Constructor
	 * @param nodePreferencePage
	 */
	public MyPropertyChangeListener(MyPreferencePage nodePreferencePage) {
		this.page = nodePreferencePage;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		page.propertyChange(event);
		
		// The IS_VALID property is only fired when the valid state changes.
		if (event.getProperty().equals(FieldEditor.IS_VALID)) {
			Object source = event.getSource();
			if (source instanceof StringButtonFieldEditor) {
				StringButtonFieldEditor strButFieldEditor = (StringButtonFieldEditor) source;
				String stringValue = strButFieldEditor.getStringValue();
				page.getLabelFieldEditor().setLabelText(stringValue);
			}
		}
	}
}
