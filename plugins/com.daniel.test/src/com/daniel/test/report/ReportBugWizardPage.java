package com.daniel.test.report;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.dialogs.SearchPattern;
import org.eclipse.ui.progress.UIJob;

//import com.atlassian.jira.rest.client.IssueRestClient;
//import com.atlassian.jira.rest.client.SearchRestClient;
//import com.atlassian.jira.rest.client.domain.BasicIssue;
//import com.atlassian.jira.rest.client.domain.Issue;
import com.daniel.test.handlers.DanielUIImages;

//public class ReportBugWizardPage extends WizardPage {
//	
//	private SearchJob searchJob;
//	private UpdateIssuesJob updateJob;
//	//RefreshUIJob refreshUIJob;
//	private ContentProvider contentProvider;
//	private SearchPattern pattern;
//	private SearchPattern lastPattern;
////	private List<BasicIssue> lastResult;
//	private Text summaryText;
//	private TableViewer list;
//	
//	private Report report;
//	
//	private static final String[] priorities = new String[]{
//		"Blocker", //$NON-NLS-1$
//		"Critical", //$NON-NLS-1$
//		"Major", //$NON-NLS-1$
//		"Minor", //$NON-NLS-1$
//		"Optional", //$NON-NLS-1$
//		"Trivial" //$NON-NLS-1$
//	};
//
//	private static final String[] components = new String[]{
//		"aerogear-hybrid", //$NON-NLS-1$
//		"bean-validation", //$NON-NLS-1$
//		"archives", //$NON-NLS-1$
//		"birt", //$NON-NLS-1$
//		"bpel", //$NON-NLS-1$
//		"bpm", //$NON-NLS-1$
//		"browseersim", //$NON-NLS-1$
//		"build", //$NON-NLS-1$
//		"cdi", //$NON-NLS-1$
//		"cdi-extensions", //$NON-NLS-1$
//		"central", //$NON-NLS-1$
//		"cleanup", //$NON-NLS-1$
//		"common/jst/core", //$NON-NLS-1$
//		"deltacloud", //$NON-NLS-1$
//		"discovery", //$NON-NLS-1$
//		"drools", //$NON-NLS-1$
//		"eclipse-migration", //$NON-NLS-1$
//		"ejb3", //$NON-NLS-1$
//		"esb", //$NON-NLS-1$
//		"forge", //$NON-NLS-1$
//		"freemarker", //$NON-NLS-1$
//		"gwt", //$NON-NLS-1$
//		"help", //$NON-NLS-1$
//		"hibernate", //$NON-NLS-1$
//		"infinispan", //$NON-NLS-1$
//		"integration-platform", //$NON-NLS-1$
//		"jbpm", //$NON-NLS-1$
//		"jmx", //$NON-NLS-1$
//		"jsf", //$NON-NLS-1$
//		"jsp/jsf/xml/html source editing", //$NON-NLS-1$
//		"legal", //$NON-NLS-1$
//		"livereload", //$NON-NLS-1$
//		"locus", //$NON-NLS-1$
//		"marketplace", //$NON-NLS-1$
//		"maven", //$NON-NLS-1$
//		"modeshape", //$NON-NLS-1$
//		"openshift", //$NON-NLS-1$
//		"pi4soa", //$NON-NLS-1$
//		"portal-gatein", //$NON-NLS-1$
//		"profiler", //$NON-NLS-1$
//		"project-examples", //$NON-NLS-1$
//		"qa", //$NON-NLS-1$
//		"runtime-detection", //$NON-NLS-1$
//		"savara", //$NON-NLS-1$
//		"seam2", //$NON-NLS-1$
//		"search", //$NON-NLS-1$
//		"server", //$NON-NLS-1$
//		"smooks", //$NON-NLS-1$
//		"struts/shale", //$NON-NLS-1$
//		"swithyard", //$NON-NLS-1$
//		"target-platform", //$NON-NLS-1$
//		"teiid", //$NON-NLS-1$
//		"testing-tools", //$NON-NLS-1$
//		"updatesite", //$NON-NLS-1$
//		"upstream", //$NON-NLS-1$
//		"usage", //$NON-NLS-1$
//		"visual-page-editor-core", //$NON-NLS-1$
//		"visual-page-editor-templates", //$NON-NLS-1$
//		"webservices", //$NON-NLS-1$
//		"website", //$NON-NLS-1$
//		"xml-structure-editor", //$NON-NLS-1$
//		"xulrunner" //$NON-NLS-1$
//	};
//
//	public ReportBugWizardPage(Report report) {
//		super("Report Bug"); //$NON-NLS-1$
//		setTitle("Report Bug"); //$NON-NLS-1$
//		contentProvider = new ContentProvider();
//		searchJob = new SearchJob();
//		updateJob = new UpdateIssuesJob();
//		this.report = report;
//		//refreshUIJob = new RefreshUIJob();
//	}
//	
//	public void dispose() {
//		searchJob.cancel();
//		updateJob.cancel();
//		super.dispose();
//	}
//	
//	public void scheduleUpdate() {
//		updateJob.cancelAll();
//		updateJob.schedule();
//	}
//	
//	public void refresh(){
//		if (list != null && !list.getTable().isDisposed()) {
//
//			list.getTable().deselectAll();
//
//			list.setItemCount(contentProvider.getNumberOfElements());
//			list.refresh();
//
//			list.setSelection(StructuredSelection.EMPTY);
//
//		}
//	}
	
//	private void applyFilter(String stringPattern){
//		
//		SearchPattern newPattern = new SearchPattern();
//		newPattern.setPattern(stringPattern);
//
//		if (pattern != null && pattern.equalsPattern(newPattern)) {
//			return;
//		}
//
//		searchJob.cancel();
//
//		this.pattern = newPattern;
//
//		if (this.pattern != null) {
//			searchJob.schedule();
//		}
//	}
	
//	private static SearchRestClient searchClient = null;
//	private static IssueRestClient issueClient = null;
//	
//	public void fillContentProvider(SearchPattern issuesPattern, IProgressMonitor monitor){
//		if(searchClient == null){
//			searchClient = JiraUtils.getAnonymouseSearchClient();
//		}
//		if(issueClient == null){
//			issueClient = JiraUtils.getAnonymouseIssueClient();
//		}
//		List<BasicIssue> issues = JiraUtils.searchDuplicats(searchClient, issuesPattern.getPattern());
//		for(BasicIssue issue : issues){
//			contentProvider.addIssue(issue);
//		}
//	}
	
//	public void setVisible(boolean visible) {
//		super.setVisible(visible);
//		if(visible){
//			summaryText.setFocus();
//		}
//	}
//
//	@Override
//	public void createControl(Composite parent) {
//		Composite root = new Composite(parent, SWT.NONE);
//		
//		GridLayout layout = new GridLayout(2,false);
//		layout.verticalSpacing = 5;
//		layout.horizontalSpacing = 5;
//		
//		root.setLayout(layout);

		// Project
//		Label projectLabel = new Label(root, SWT.NONE);
//		projectLabel.setText("Project:");
//		
//		Text projectText = new Text(root, SWT.BORDER);
//		projectText.setText("Tools (JBoss Tools)");
//		projectText.setEditable(false);
//		projectText.setLayoutData(textData);
		
		//Issue Type - Bug
//		Label issueTypeLabel = new Label(root, SWT.NONE);
//		issueTypeLabel.setText("Issue Type:");
//		
//		Text issueTypeText = new Text(root, SWT.BORDER);
//		issueTypeText.setText("Bug");
//		issueTypeText.setEditable(false);
//		issueTypeText.setLayoutData(textData);
		
		//Summary
//		Label summaryLabel = new Label(root, SWT.NONE);
//		summaryLabel.setText("Summary:"); //$NON-NLS-1$
//		
//		summaryText = new Text(root, SWT.BORDER);
//		summaryText.setText(""); //$NON-NLS-1$
//		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
//		summaryText.setLayoutData(gridData);
//		summaryText.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e){
//				applyFilter(summaryText.getText());
//			}
//		});
//		
//		//READ THIS BEFORE ENTERING NEW REPORTS
//		final Link readThisLink = new Link(root, SWT.WRAP);
//		
//		GridData linkData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
//		linkData.horizontalSpan = 2;
//		
//		readThisLink.setLayoutData(linkData);
//		readThisLink.setText("READ THIS BEFORE ENTERING NEW REPORTS"); //$NON-NLS-1$
//		readThisLink.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
//		readThisLink.addMouseListener(new MouseListener() {
//			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
//				openInExternalBrowser(readThisLink.getDisplay(), "https://community.jboss.org/wiki/HelpBugReport"); //$NON-NLS-1$
//			}
//
//			public void mouseDoubleClick(MouseEvent e) {
//			}
//			public void mouseUp(MouseEvent e) {
//			}
//		});
//		
//		//Similar issues
//		Label similarIssuesLabel = new Label(root, SWT.NONE);
//		similarIssuesLabel.setText("Similar issues:"); //$NON-NLS-1$
//		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
//		similarIssuesLabel.setLayoutData(gridData);
//		
//		list = new TableViewer(root, SWT.MULTI
//				| SWT.BORDER | SWT.V_SCROLL | SWT.VIRTUAL);
//		list.setContentProvider(contentProvider);
//		list.setInput(new Object[0]);
//		list.setItemCount(contentProvider.getNumberOfElements());
		
//		list.addSelectionChangedListener(new ISelectionChangedListener(){
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				ISelection s = event.getSelection();
//				if(!s.isEmpty() && s instanceof IStructuredSelection) {
//					BasicIssue issue = (BasicIssue)((IStructuredSelection)s).getFirstElement();
//					openInExternalBrowser(list.getTable().getDisplay(), JiraUtils.JBOSS_JIRA_URL+"/browse/"+issue.getKey()); //$NON-NLS-1$
//				}
//				summaryText.setFocus();
//			}
//		});
		
//		TableViewerColumn keyColumn = new TableViewerColumn(list, SWT.NONE);
//		keyColumn.setLabelProvider(new KeyLabelProvider());
//		keyColumn.getColumn().setWidth(110);
//
//		TableViewerColumn summaryColumn = new TableViewerColumn(list, SWT.NONE);
//		summaryColumn.setLabelProvider(new SummaryLabelProvider());
//		summaryColumn.getColumn().setWidth(100);
//
//		gridData = new GridData(GridData.FILL_BOTH);
//		gridData.heightHint = 100;
//		list.getTable().setLayoutData(gridData);
//		
//		//Priority - Blocker, Critical, Major, Minor, Optional, Trivial
//		Label priorityLabel = new Label(root, SWT.NONE);
//		priorityLabel.setText("Priority:"); //$NON-NLS-1$
//		
//		Combo prioriryCombo = new Combo(root, SWT.BORDER);
//		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
//		prioriryCombo.setLayoutData(gridData);
//		prioriryCombo.setItems(priorities);
//		prioriryCombo.select(2);
//		
//		//Component/s 
//		Label componentLabel = new Label(root, SWT.NONE);
//		componentLabel.setText("Component:"); //$NON-NLS-1$
//		
//		Combo componentCombo = new Combo(root, SWT.BORDER);
//		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
//		componentCombo.setLayoutData(gridData);
//		componentCombo.setItems(components);
		
		//Affects Version/s - 
//		Label affectsVessionLabel = new Label(root, SWT.NONE);
//		affectsVessionLabel.setText("Affects Version:");
//		
//		Text affectsVersionText = new Text(root, SWT.BORDER);
//		gridData = new GridData(GridData.FILL_HORIZONTAL);
//		affectsVersionText.setLayoutData(gridData);
//		affectsVersionText.setText("");
		
		//Assignee - Automatic
//		Label assigneeLabel = new Label(root, SWT.NONE);
//		assigneeLabel.setText("Assignee:");
//		
//		Text assigneeText = new Text(root, SWT.BORDER);
//		assigneeText.setLayoutData(textData);
//		assigneeText.setText("Automatic");
//		assigneeText.setEditable(false);

		//Environment
//		Label environmentLabel = new Label(root, SWT.NONE);
//		environmentLabel.setText("Environment:");
//		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
//		environmentLabel.setLayoutData(gridData);
//		
//		Text environmentText = new Text(root, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL);
//		gridData = new GridData(GridData.FILL_BOTH);
//		gridData.heightHint = 100;
//		environmentText.setLayoutData(gridData);
//		environmentText.setText("");
		
		//Description
//		Label descriptionLabel = new Label(root, SWT.NONE);
//		descriptionLabel.setText("Description:"); //$NON-NLS-1$
//		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
//		descriptionLabel.setLayoutData(gridData);
//		
//		Text descriptionText = new Text(root, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL);
//		gridData = new GridData(GridData.FILL_BOTH);
//		gridData.heightHint = 100;
//		descriptionText.setLayoutData(gridData);
//		if(report != null){
//			descriptionText.setText(report.getSessionData()+"\n"+report.getStack()); //$NON-NLS-1$
//		}else{
//			descriptionText.setText(""); //$NON-NLS-1$
//		}
//		
//		Button attachLogCheckbox = new Button(root, SWT.WRAP|SWT.CHECK);
//		
//		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
//		gridData.horizontalSpan = 2;
//		
//		attachLogCheckbox.setLayoutData(gridData);
//		attachLogCheckbox.setText("Attach log"); //$NON-NLS-1$
//		attachLogCheckbox.setSelection(true);
//		
//		Button attachFileButton = new Button(root, SWT.WRAP);
//		
//		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
//		gridData.horizontalSpan = 2;
//		
//		attachFileButton.setLayoutData(gridData);
//		attachFileButton.setText("Attach File..."); //$NON-NLS-1$
//		
//		setControl(root);
//	}
	
//	private class UpdateIssuesJob extends Job {
//		RefreshUIJob refreshUIJob = new RefreshUIJob();
//
//		/**
//		 * Creates a new instance of the class.
//		 */
//		public UpdateIssuesJob() {
//			super("Updating issues"); //$NON-NLS-1$
//			setSystem(true);
//		}

		/**
		 * Stops the job and all sub-jobs.
		 */
//		public void cancelAll() {
//			cancel();
//			refreshUIJob.cancel();
//		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
//		protected IStatus run(IProgressMonitor monitor) {
//			System.out.println("Update Issues Job"); //$NON-NLS-1$
//			if (monitor.isCanceled()) {
//				return new Status(IStatus.CANCEL, "", IStatus.CANCEL, "", null); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//
//			if (contentProvider != null) {
//				for(int index = 0; index < contentProvider.getNumberOfElements(); index++){
//					contentProvider.updateIssue(index);
//					if (!monitor.isCanceled()) {
//						refreshUIJob.schedule();
//					}
//				}
//			}
//
//			return new Status(IStatus.OK, PlatformUI.PLUGIN_ID, IStatus.OK,	"", null); //$NON-NLS-1$
//
//		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.runtime.jobs.Job#canceling()
		 */
//		protected void canceling() {
//			super.canceling();
//			contentProvider.stopFilteringIssues();
//		}
//
//	}

//	private class RefreshUIJob extends UIJob {
//
//		public RefreshUIJob() {
//			super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay(), "Refresh UI Job"); //$NON-NLS-1$
//			setSystem(true);
//		}
//
//		public IStatus runInUIThread(IProgressMonitor monitor) {
//			System.out.println("Refresh UI JOB"); //$NON-NLS-1$
//			if (monitor.isCanceled())
//				return new Status(IStatus.OK, "", IStatus.OK, "", null); //$NON-NLS-1$ //$NON-NLS-2$
//
//			if (ReportBugWizardPage.this != null) {
//				ReportBugWizardPage.this.refresh();
//			}
//
//			return new Status(IStatus.OK, PlatformUI.PLUGIN_ID, IStatus.OK,	"", null); //$NON-NLS-1$
//		}
//
//	}
	
//	private class SearchJob extends Job {
//		RefreshUIJob refreshUIJob = new RefreshUIJob();
//		protected SearchPattern issuesPattern;
//
//		public SearchJob() {
//			super("Search duplicate issues"); //$NON-NLS-1$
//			setSystem(true);
//		}
//		
//		public void cancelAll() {
//			cancel();
//			refreshUIJob.cancel();
//		}

//		protected final IStatus run(IProgressMonitor monitor) {
//			return doRun(monitor);
//		}

		/**
		 * Executes job using the given filtering progress monitor. A hook for
		 * subclasses.
		 * 
		 * @param monitor
		 *            progress monitor
		 * @return result of the execution
		 */
//		protected IStatus doRun(IProgressMonitor monitor) {
//			try {
//				internalRun(monitor);
//			} catch (CoreException e) {
//				cancel();
//				return new Status(
//						IStatus.ERROR,
//						PlatformUI.PLUGIN_ID,
//						IStatus.ERROR,
//						"Error", //$NON-NLS-1$
//						e);
//			}
//			return Status.OK_STATUS;
//		}

		/**
		 * Main method for the job.
		 * 
		 * @param monitor
		 * @throws CoreException
		 */
//		private void internalRun(IProgressMonitor monitor)	throws CoreException {
//			System.out.println("Search JOB"); //$NON-NLS-1$
//			try {
//				if (monitor.isCanceled())
//					return;
//
//				this.issuesPattern = pattern;
//				contentProvider.reset();
//
//				if (pattern.getPattern().length() != 0) {
//					filterContent(monitor);
//				}
//				
//				if (monitor.isCanceled())
//					return;
//				
//				refreshUIJob.schedule();
//
//				contentProvider.update();
//			} finally {
//				monitor.done();
//			}
//		}

		/**
		 * Filters items.
		 * 
		 * @param monitor
		 *            for monitoring progress
		 * @throws CoreException
		 */
//		protected void filterContent(IProgressMonitor monitor)
//				throws CoreException {

//			if (lastPattern != null
//					&& lastPattern.isSubPattern(this.issuesPattern)) {
//
//				int length = lastResult.size() / 500;
//				monitor.beginTask("Searching for duplicate issues...", length);
//
//				for (int pos = 0; pos < lastResult.size(); pos++) {
//
//					BasicIssue item = lastResult.get(pos);
//					if (monitor.isCanceled())
//						break;
//					contentProvider.addIssue(item);
//
//					if ((pos % 500) == 0) {
//						monitor.worked(1);
//					}
//				}
//
//			} else {

//				lastPattern = null;
//				lastResult = null;
//
//				SubProgressMonitor subMonitor = null;
//				if (monitor != null) {
//					monitor.beginTask("Searching for duplicate issues...", 100); //$NON-NLS-1$
//					subMonitor = new SubProgressMonitor(monitor, 95);
//
//				}
//
//				fillContentProvider(issuesPattern, subMonitor);
//				
//				if (monitor != null && !monitor.isCanceled()) {
//					monitor.worked(2);
//					contentProvider.rememberResult(issuesPattern);
//					monitor.worked(3);
//				}
//			}

//		}

//	}
	
//	private void openInExternalBrowser(Display display, final String url){
//		BusyIndicator.showWhile(display, new Runnable() {
//			public void run() {
//				URL theURL = null;
//				;
//				try {
//					theURL = new URL(url);
//				} catch (MalformedURLException e) {
//					//VpePlugin.reportProblem(e);
//				}
//				IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
//				try {
//					support.getExternalBrowser().openURL(theURL);
//				} catch (PartInitException e) {
//					//VpePlugin.reportProblem(e);
//				}
//			}
//		});
	//}
	
//	private class ContentProvider implements IStructuredContentProvider, ILazyContentProvider {
//		ArrayList<BasicIssue> issues = new ArrayList<BasicIssue>();
//		//ArrayList<BasicIssue> lastSortedIssues = new ArrayList<BasicIssue>();
//		
//		public void addIssue(BasicIssue issue){
//			issues.add(issue);
//		}
//
//		@Override
//		public void dispose() {
//		}
//
//		@Override
//		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//		}
//
//		@Override
//		public void updateElement(int index) {
//			ReportBugWizardPage.this.list.replace((lastResult.size() > index) ? lastResult.get(index) : null, index);
//		}
//
//		@Override
//		public Object[] getElements(Object inputElement) {
//			return issues.toArray();
//		}
//		
//		public int getNumberOfElements(){
//			return issues.size();
//		}
//		
//		private Object[] getSortedItems() {
////			if (lastSortedIssues.size() != issues.size()) {
////				synchronized (lastSortedIssues) {
////					lastSortedIssues.clear();
////					lastSortedIssues.addAll(issues);
////					//Collections.sort(lastSortedIssues);
////				}
////			}
//			return issues.toArray();
//		}
//		
//		public void stopFilteringIssues(){
//			
//		}
//
//		
//		public void rememberResult(SearchPattern itemsFilter) {
//			List itemsList = Collections.synchronizedList(Arrays
//					.asList(getSortedItems()));
//			// synchronization
//			if (itemsFilter == pattern) {
//				lastPattern = itemsFilter;
//				lastResult = itemsList;
//			}
//
//		}
//		public void reset(){
//			issues.clear();
//			//lastSortedIssues.clear();
//		}
//		
//		public void updateIssue(int index){
//			BasicIssue bIssue = issues.get(index);
//			if(!(bIssue instanceof Issue)){
//				Issue issue = JiraUtils.getIssue(issueClient, bIssue);
//				issues.set(index, issue); // TODO java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
//			}
//			rememberResult(pattern);
//		}
//		
//		public void update() {
//			scheduleUpdate();
//		}
//
//	}
	
//	static Color blue = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
//	static Color black = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
//	static Color gray = new Color(null, 128, 128, 128);

//	static Styler ID_STYLER = new DefaultStyler(blue, false, false);
//	static Styler ID_STRIKEOUT_STYLER = new DefaultStyler(blue, false, true);
//	static Styler ISSUE_NAME_STYLER = new DefaultStyler(black, false, false);
//	static Styler LOADING_ISSUE_STYLER = new DefaultStyler(gray, false, false);

//	private static class DefaultStyler extends Styler {
//		private Color foreground;
//		private boolean bold;
//		private boolean strikeout;
//
//		public DefaultStyler(Color foreground, boolean bold, boolean strikeout) {
//			this.foreground = foreground;
//			this.strikeout = strikeout;
//			this.bold = bold;
//		}
//
//		@Override
//		public void applyStyles(TextStyle textStyle) {
//			if (foreground != null) {
//				textStyle.foreground = foreground;
//			}
//			
//			textStyle.strikeout = strikeout;
//			if(bold) {
//				textStyle.font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
//			}
//		}
//	}
//	
//	class KeyLabelProvider extends StyledCellLabelProvider implements DelegatingStyledCellLabelProvider.IStyledLabelProvider {
//		@Override
//		public void update(ViewerCell cell) {
//			Object element = cell.getElement();
//			StyledString styledString = getStyledText(element);
//			cell.setText(styledString.getString());
//			cell.setStyleRanges(styledString.getStyleRanges());
//			cell.setImage(getImage(element));
//
//			super.update(cell);
//		}
//
//		public String getText(Object element) {
//			return getStyledText(element).getString();
//		}
//
//		@Override
//		public StyledString getStyledText(Object element) {
//			StyledString sb = new StyledString();
//			sb.append("    "); //$NON-NLS-1$
//			if(element instanceof Issue){
//				Issue issue = (Issue)element;
//				
//				if(JiraUtils.STATUS_CLOSED.equals(issue.getStatus().getName()) || JiraUtils.STATUS_RESOLVED.equals(issue.getStatus().getName())){
//					sb.append(issue.getKey(), ID_STRIKEOUT_STYLER);
//				}else{
//					sb.append(issue.getKey(), ID_STYLER);
//				}
//			}else if(element instanceof BasicIssue){
//				BasicIssue bIssue = (BasicIssue)element;
//				sb.append(bIssue.getKey(), ID_STYLER).append(' ');
//			}
//			return sb;
//		}
//
//		@Override
//		public Image getImage(Object element) {
//			if(element instanceof Issue){
//				Issue issue = (Issue)element;
//				
//				if(JiraUtils.PRIORITY_BLOCKER.equals(issue.getPriority().getName())){
//					return DanielUIImages.getImage(DanielUIImages.PRIORITY_BLOCKER_IMAGE);
//				}else if(JiraUtils.PRIORITY_CRITICAL.equals(issue.getPriority().getName())){
//					return DanielUIImages.getImage(DanielUIImages.PRIORITY_CRITICAL_IMAGE);
//				}else if(JiraUtils.PRIORITY_MAJOR.equals(issue.getPriority().getName())){
//					return DanielUIImages.getImage(DanielUIImages.PRIORITY_MAJOR_IMAGE);
//				}else if(JiraUtils.PRIORITY_MINOR.equals(issue.getPriority().getName())){
//					return DanielUIImages.getImage(DanielUIImages.PRIORITY_MINOR_IMAGE);
//				}else if(JiraUtils.PRIORITY_TRIVIAL.equals(issue.getPriority().getName())){
//					return DanielUIImages.getImage(DanielUIImages.PRIORITY_TRIVIAL_IMAGE);
//				}
//			}
//			return null;
//		}
//	}
//	
//	class SummaryLabelProvider extends StyledCellLabelProvider implements DelegatingStyledCellLabelProvider.IStyledLabelProvider {
//		@Override
//		public void update(ViewerCell cell) {
//			Object element = cell.getElement();
//			StyledString styledString = getStyledText(element);
//			cell.setText(styledString.getString());
//			cell.setStyleRanges(styledString.getStyleRanges());
//			cell.setImage(getImage(element));
//
//			super.update(cell);
//		}
//
//		public String getText(Object element) {
//			return getStyledText(element).getString();
//		}
//
//		@Override
//		public StyledString getStyledText(Object element) {
//			StyledString sb = new StyledString();
//			if(element instanceof Issue){
//				Issue issue = (Issue)element;
//				
//				sb.append(issue.getSummary(), ISSUE_NAME_STYLER);
//			}else if(element instanceof BasicIssue){
//				sb.append(" loading...", LOADING_ISSUE_STYLER); //$NON-NLS-1$
//			}
//			return sb;
//		}
//
//		@Override
//		public Image getImage(Object element) {
//			return null;
//		}
//	}
//}
