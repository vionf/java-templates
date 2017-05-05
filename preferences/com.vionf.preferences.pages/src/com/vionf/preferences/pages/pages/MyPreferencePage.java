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

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.vionf.preferences.pages.Activator;

/**
 * @author vionf
 *
 */
public class MyPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	
	private StringButtonFieldEditor fStrButtonFieldEditor;
	private LabelFieldEditor fLabelFieldEditor;
	
	/**
	 * Constructor
	 */
	public MyPreferencePage() {
		/* GRID si tu veux avoir plusieur fieldeditor dans ta page de preference */
		super(GRID);
	}
	

	@Override
	public String getTitle() {
		return "title of the preference page";
	}



	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		String subTitle = "subtitle of the preference page like a description";
		setDescription(subTitle);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		
	}


	public StringButtonFieldEditor getStrButtonFieldEditor() {
		return fStrButtonFieldEditor;
	}


	public LabelFieldEditor getLabelFieldEditor() {
		return fLabelFieldEditor;
	}

	

}
