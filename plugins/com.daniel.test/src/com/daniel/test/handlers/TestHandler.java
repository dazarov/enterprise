package com.daniel.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.osgi.internal.messages.Msg;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleException;

import com.daniel.test.DanielTestPlugin;

public class TestHandler extends AbstractHandler {

	public TestHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("----- <<<<<< TestHandler - EXECUTE >>>>>>> --------"); //$NON-NLS-1$
		
		IWorkbench workbench = PlatformUI.getWorkbench();
		if(workbench != null){
			System.out.println("Number of windows - "+workbench.getWorkbenchWindowCount()); //$NON-NLS-1$
			
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			if(window != null){
				if(window instanceof WorkbenchWindow){
					CoolBarManager toolBarManager1 = ((WorkbenchWindow) window).getCoolBarManager();
					if(toolBarManager1 != null){
						for(IContributionItem item : toolBarManager1.getItems()){
							System.out.println("1 Item - "+item.getId()+" "+item.getClass());
						}
					}else{
						System.out.println("TOOLBAR MANAGER 1 - NULL"); //$NON-NLS-1$
					}
					ICoolBarManager toolBarManager2 = ((WorkbenchWindow) window).getCoolBarManager2();
					if(toolBarManager2 != null){
						for(IContributionItem item : toolBarManager2.getItems()){
							System.out.println("2 Item - "+item.getId()+" "+item.getClass());
						}
					}else{
						System.out.println("TOOLBAR MANAGER 2 - NULL"); //$NON-NLS-1$
					}
				}else if(window instanceof ApplicationWindow){
					ToolBarManager toolBarManager = ((ApplicationWindow) window).getToolBarManager();
					if(toolBarManager != null){
						for(IContributionItem item : toolBarManager.getItems()){
							System.out.println("1 Item - "+item.getId()+" "+item.getClass());
						}
					}else{
						System.out.println("TOOLBAR MANAGER 1 - NULL"); //$NON-NLS-1$
					}
					IToolBarManager toolBarManager2 = ((ApplicationWindow) window).getToolBarManager2();
					if(toolBarManager2 != null){
						for(IContributionItem item : toolBarManager2.getItems()){
							System.out.println("2 Item - "+item.getId()+" "+item.getClass());
						}
					}else{
						System.out.println("TOOLBAR MANAGER 2 - NULL"); //$NON-NLS-1$
					}
				}else{
					System.out.println("WINDOW - "+window.getClass()); //$NON-NLS-1$
				}
				Shell shell = window.getShell();
				if(shell != null){
					ToolBar toolBar = shell.getToolBar();
					if(toolBar != null){
						for(Control control : toolBar.getChildren()){
							System.out.println("Control class - "+control.getClass()); //$NON-NLS-1$
						}
					}else{
						System.out.println("TOOLBAR - NULL"); //$NON-NLS-1$
					}
				}else{
					System.out.println("SHELL - NULL"); //$NON-NLS-1$
				}
			}else{
				System.out.println("ACTIVE WINDOW - NULL"); //$NON-NLS-1$
			}
			Display display = workbench.getDisplay();
			if(display != null){
				Shell shell = display.getActiveShell();
				if(shell != null){
					ToolBar toolBar = shell.getToolBar();
					if(toolBar != null){
						for(Control control : toolBar.getChildren()){
							System.out.println("2 Control class - "+control.getClass()); //$NON-NLS-1$
						}
					}else{
						System.out.println("TOOLBAR 2 - NULL"); //$NON-NLS-1$
					}
				}else{
					System.out.println("ACTIVE SHELL - NULL"); //$NON-NLS-1$
				}
			}else{
				System.out.println("DISPLAY - NULL"); //$NON-NLS-1$
			}
		}else{
			System.out.println("WORKBENCH - NULL"); //$NON-NLS-1$
		}
		//try {
			//IViewPart part = workbench.getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.pde.runtime.LogView");
			//ToolBarManager manager = (ToolBarManager)part.getViewSite().getActionBars().getToolBarManager();
			//System.out.println("ToolBarManager - "+manager.getClass());
			//manager.

			//for(IContributionItem item : manager.getItems()){
			//	System.out.println("222 1 item id - "+item.getId()+" class - "+item.getClass()); //$NON-NLS-1$
			//}
//			ToolBar toolBar = part.getSite().getShell().getToolBar();
//			if(toolBar != null){
//				for(Control control : toolBar.getChildren()){
//					System.out.println("222 2 Control class - "+control.getClass()); //$NON-NLS-1$
//				}
//			}else{
//				System.out.println("TOOLBAR 222 2 - NULL"); //$NON-NLS-1$
//			}
		//} catch (CoreException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		//}
		
//		Display.getDefault().asyncExec(new Runnable(){
//
//			@Override
//			public void run() {
//				Exception base = new Exception("This is very serious reason!!!!");
//				base.setStackTrace(Thread.currentThread().getStackTrace());
//				
//				Status status = new Status(Status.ERROR, "com.daniel.test", "VERY SERIOUS ERROR!!!!!!!!!", base);
//				CoreException ex = new CoreException(status);
//				
//				
//				DanielTestPlugin.getDefault().logError(ex);
			String reportMessage = "org.osgi.framework.BundleException: Could not resolve module: org.jboss.tools.c.c.core [1089]\n"+
"  Unresolved requirement: Require-Bundle: org.jboss.tools.b.b\n"+
"    -> Bundle-SymbolicName: org.jboss.tools.b.b; bundle-version=\"1.7.0.qualifier\"; singleton:=\"true\"\n"+
"       org.jboss.tools.b.b [1329]\n"+
"         Unresolved requirement: Require-Bundle: org.jboss.tools.a.a; visibility:=\"reexport\"\n"+
"           -> Bundle-SymbolicName: org.jboss.tools.a.a; bundle-version=\"1.7.0.qualifier\"; singleton:=\"true\"\n"+
"              org.jboss.tools.a.a [1359]\n"+
"                Unresolved requirement: Require-Capability: osgi.ee; filter:=\"(&(osgi.ee=JavaSE)(version=1.8))\"";
       		Exception base =  new BundleException(reportMessage, BundleException.RESOLVE_ERROR);
           base.setStackTrace(Thread.currentThread().getStackTrace());
			
			Status status = new Status(Status.ERROR, "com.daniel.test", "FrameworkEvent ERROR", base);
			//CoreException ex = new CoreException(status);
			
			
			DanielTestPlugin.logError(base);
		
		
		//LoggingJob job = new LoggingJob("a");
		//job.schedule();
		
		return null;
	}
	
	class LoggingJob extends UIJob{

		public LoggingJob(String name) {
			super(name);
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			Exception base = new Exception("This is very serious reason!!!!");
			base.setStackTrace(Thread.currentThread().getStackTrace());
			
			Status status = new Status(Status.ERROR, "com.daniel.test", "VERY SERIOUS ERROR!!!!!!!!!", base);
			CoreException ex = new CoreException(status);
			
			
			DanielTestPlugin.getDefault().logError(ex);
			return status;
		}
		
	}
}
