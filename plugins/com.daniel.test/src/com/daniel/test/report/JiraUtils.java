package com.daniel.test.report;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//import com.atlassian.jira.rest.client.IssueRestClient;
//import com.atlassian.jira.rest.client.JiraRestClient;
//import com.atlassian.jira.rest.client.NullProgressMonitor;
//import com.atlassian.jira.rest.client.SearchRestClient;
//import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
//import com.atlassian.jira.rest.client.domain.BasicIssue;
//import com.atlassian.jira.rest.client.domain.Issue;
//import com.atlassian.jira.rest.client.domain.SearchResult;
//import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
//import com.daniel.test.DanielTestPlugin;
//import com.sun.jersey.api.client.UniformInterfaceException;

public class JiraUtils {
	public static final String JBOSS_JIRA_URL = "https://issues.jboss.org"; //$NON-NLS-1$
	
	public static final String STATUS_RESOLVED = "Resolved"; //$NON-NLS-1$
	public static final String STATUS_CLOSED = "Closed"; //$NON-NLS-1$
	public static final String STATUS_OPEN = "Open"; //$NON-NLS-1$

	public static final String PRIORITY_BLOCKER = "Blocker"; //$NON-NLS-1$
	public static final String PRIORITY_CRITICAL = "Critical"; //$NON-NLS-1$
	public static final String PRIORITY_MAJOR = "Major"; //$NON-NLS-1$
	public static final String PRIORITY_MINOR = "Minor"; //$NON-NLS-1$
	public static final String PRIORITY_TRIVIAL = "Trivial"; //$NON-NLS-1$
	
//	public static SearchRestClient getAnonymouseSearchClient(){
//		JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
//		URI jiraServerUri = null;
//		try{
//			jiraServerUri = new URI(JBOSS_JIRA_URL);
//		}catch(URISyntaxException ex){
//			DanielTestPlugin.getDefault().logError(ex);
//		}
//		if(jiraServerUri != null){
//			AnonymousAuthenticationHandler auth = new AnonymousAuthenticationHandler();
//	        JiraRestClient restClient = factory.create(jiraServerUri, auth);
//	        
//	        return restClient.getSearchClient();
//		}
//		
//		return null;
//	}
//	
//	public static IssueRestClient getAnonymouseIssueClient(){
//		JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
//		URI jiraServerUri = null;
//		try{
//			jiraServerUri = new URI(JBOSS_JIRA_URL);
//		}catch(URISyntaxException ex){
//			DanielTestPlugin.getDefault().logError(ex);
//		}
//		if(jiraServerUri != null){
//			AnonymousAuthenticationHandler auth = new AnonymousAuthenticationHandler();
//	        JiraRestClient restClient = factory.create(jiraServerUri, auth);
//	        
//	        return restClient.getIssueClient();
//		}
//		
//		return null;
//	}
//	
//	public static List<Issue> searchDuplicats(SearchRestClient searchClient, IssueRestClient issueClient, String pattern){
//		ArrayList<Issue> issues = new ArrayList<Issue>();
//		
//        try{
//	        String jql = "project = \"Tools (JBoss Tools)\" AND summary ~ \""+pattern+"\" order by status desc, priority, created"; //$NON-NLS-1$ //$NON-NLS-2$
//	        
//	        SearchResult searchResult = searchClient.searchJql(jql, 10, 0, new NullProgressMonitor());
//	        
//	        for(BasicIssue bIssue : searchResult.getIssues()){
//	        	Issue issue = issueClient.getIssue(bIssue.getKey(), new NullProgressMonitor());
//	        	//System.out.println("Issue - "+issue.getKey()+" "+issue.getClass());
//	        	
//        		issues.add(issue);
//	        }
//        }catch(UniformInterfaceException ex){
//        	System.out.println(ex.getMessage());
//        }
//		
//		return issues;
//	}
//
//	public static List<BasicIssue> searchDuplicats(SearchRestClient searchClient, String pattern){
//		ArrayList<BasicIssue> issues = new ArrayList<BasicIssue>();
//		
//        try{
//	        String jql = "project = \"Tools (JBoss Tools)\" AND summary ~ \""+pattern+"\" order by status desc, priority, created"; //$NON-NLS-1$ //$NON-NLS-2$
//	        
//	        SearchResult searchResult = searchClient.searchJql(jql, 10, 0, new NullProgressMonitor());
//	        
//	        for(BasicIssue issue : searchResult.getIssues()){
//        		issues.add(issue);
//	        }
//        }catch(UniformInterfaceException ex){
//        	System.out.println(ex.getMessage());
//        }
//		
//		return issues;
//	}
//	
//	public static Issue getIssue(IssueRestClient issueClient, BasicIssue basicIssue){
//		return issueClient.getIssue(basicIssue.getKey(), new NullProgressMonitor());
//	}
//
}
