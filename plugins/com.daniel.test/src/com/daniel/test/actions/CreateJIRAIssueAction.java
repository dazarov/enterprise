package com.daniel.test.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.log.LogEntry;

import com.daniel.test.report.Report;
import com.daniel.test.report.ReportBugWizard;

public class CreateJIRAIssueAction implements IObjectActionDelegate, IViewActionDelegate{
	Report report=null;

	public CreateJIRAIssueAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), new ReportBugWizard(report));
		
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection; 
			if (structuredSelection.size() == 1) {
				Object object = structuredSelection.getFirstElement();
				if(object instanceof LogEntry){
					report = new Report((LogEntry)object);
				}
			}
		}

	}

	@Override
	public void init(IViewPart view) {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
