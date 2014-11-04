/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package com.daniel.test.handlers;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.jboss.tools.common.ui.CommonUIImages;

import com.daniel.test.DanielTestPlugin;

public class DanielUIImages extends CommonUIImages {
	private static String WIZARDS_PATH         = "wizards/"; //$NON-NLS-1$
	
	public static final String REPORT_PROBLEM_WIZARD_IMAGE = WIZARDS_PATH+"ReportProblemWizBan.png"; //$NON-NLS-1$
	public static final String PRIORITY_BLOCKER_IMAGE = "blocker.png"; //$NON-NLS-1$
	public static final String PRIORITY_CRITICAL_IMAGE = "critical.png"; //$NON-NLS-1$
	public static final String PRIORITY_MAJOR_IMAGE = "major.png"; //$NON-NLS-1$
	public static final String PRIORITY_MINOR_IMAGE = "minor.png"; //$NON-NLS-1$
	public static final String PRIORITY_TRIVIAL_IMAGE = "trivial.png"; //$NON-NLS-1$
	
	private static DanielUIImages INSTANCE;
	
	static {
		try {
			INSTANCE = new DanielUIImages(new URL(DanielTestPlugin.getDefault().getBundle().getEntry("/"), "icons/")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (MalformedURLException e) {
			// do nothing
			DanielTestPlugin.getDefault().logError(e);
		}
	}
	
	public static Image getImage(String key) {
		return getInstance().getOrCreateImage(key);
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		return getInstance().getOrCreateImageDescriptor(key);
	}

	public static void setImageDescriptors(IAction action, String iconName)	{
		action.setImageDescriptor(getImageDescriptor(iconName));
	}
	
	public static DanielUIImages getInstance() {
		return INSTANCE;
	}

	protected DanielUIImages(URL registryUrl, DanielUIImages parent){
		super(registryUrl, parent);
	}
	
	protected DanielUIImages(URL url){
		this(url,null);		
	}

	protected ImageRegistry getImageRegistry() {
		return DanielTestPlugin.getDefault().getImageRegistry();
	}
}
