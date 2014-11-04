package com.daniel.test.report;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UserCredentialsWizardPage extends WizardPage {

	public UserCredentialsWizardPage() {
		super("User Credentials");
		setTitle("User Credentials");
	}

	@Override
	public void createControl(Composite parent) {
		Composite root = new Composite(parent, SWT.NONE);
		
		//GridData labelData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		//textData.heightHint = 20;

		root.setLayout(new GridLayout(2,false));
		
		Label usernameLabel = new Label(root, SWT.NONE);
		usernameLabel.setText("Username:");
		//usernameLabel.setLayoutData(labelData);
		
		Text usernameText = new Text(root, SWT.BORDER);
		usernameText.setLayoutData(textData);
		
		Label passwordLabel = new Label(root, SWT.NONE);
		passwordLabel.setText("Password:");
		//passwordLabel.setLayoutData(labelData);
		
		Text passwordText = new Text(root, SWT.PASSWORD|SWT.BORDER);
		passwordText.setLayoutData(textData);
		
		setControl(root);
	}

}
