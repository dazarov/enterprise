package com.daniel.test.handlers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

//import com.atlassian.jira.rest.client.JiraRestClient;
//import com.atlassian.jira.rest.client.NullProgressMonitor;
//import com.atlassian.jira.rest.client.domain.Comment;
//import com.atlassian.jira.rest.client.domain.Issue;
//import com.atlassian.jira.rest.client.domain.Transition;
//import com.atlassian.jira.rest.client.domain.input.FieldInput;
//import com.atlassian.jira.rest.client.domain.input.TransitionInput;
//import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.daniel.test.report.ReportBugWizard;

public class CreateJIRAIssueHandler  extends AbstractHandler {

	public CreateJIRAIssueHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), new ReportBugWizard());
		
		dialog.open();
		
//		final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
//        URI jiraServerUri=null;
//		try {
//			jiraServerUri = new URI("https://issues.jboss.org");  //$NON-NLS-1$
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "yourusername", "yourpassword"); //$NON-NLS-1$ //$NON-NLS-2$
//        final NullProgressMonitor pm = new NullProgressMonitor();
//        final Issue issue = restClient.getIssueClient().getIssue("TST-1", pm); //$NON-NLS-1$
// 
//        System.out.println(issue);
// 
//        // now let's vote for it
//        restClient.getIssueClient().vote(issue.getVotesUri(), pm);
// 
//        // now let's watch it
//        restClient.getIssueClient().watch(issue.getWatchers().getSelf(), pm);
// 
//        // now let's start progress on this issue
//        final Iterable<Transition> transitions = restClient.getIssueClient().getTransitions(issue.getTransitionsUri(), pm);
//        final Transition startProgressTransition = getTransitionByName(transitions, "Start Progress"); //$NON-NLS-1$
//        restClient.getIssueClient().transition(issue.getTransitionsUri(), new TransitionInput(startProgressTransition.getId()), pm);
// 
//        // and now let's resolve it as Incomplete
//        final Transition resolveIssueTransition = getTransitionByName(transitions, "Resolve Issue"); //$NON-NLS-1$
//        Collection<FieldInput> fieldInputs = Arrays.asList(new FieldInput("resolution", "Incomplete")); //$NON-NLS-1$ //$NON-NLS-2$
//        final TransitionInput transitionInput = new TransitionInput(resolveIssueTransition.getId(), fieldInputs, Comment.valueOf("My comment")); //$NON-NLS-1$
//        restClient.getIssueClient().transition(issue.getTransitionsUri(), transitionInput, pm);
		return null;
	}
	
//	private static Transition getTransitionByName(Iterable<Transition> transitions, String transitionName) {
//        for (Transition transition : transitions) {
//            if (transition.getName().equals(transitionName)) {
//                return transition;
//            }
//        }
//        return null;
//    }
}
