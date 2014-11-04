package com.daniel.test.report;

import org.eclipse.jface.wizard.Wizard;

import com.daniel.test.handlers.DanielUIImages;

public class ReportBugWizard extends Wizard {
	Report report;
	
	public ReportBugWizard(Report report){
		setDefaultPageImageDescriptor(DanielUIImages.getImageDescriptor(DanielUIImages.REPORT_PROBLEM_WIZARD_IMAGE));
		this.report = report;
	}
	
	@Override
	public void addPages() {
		addPage(new UserCredentialsWizardPage());
//		addPage(new ReportBugWizardPage(report));
	}

	public ReportBugWizard() {
		this(null);
	}

	@Override
	public boolean performFinish() {
		// TO DO
		
		return true;
	}

}
