package com.wg.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.exceptions.NotFoundExceptions;
import com.wg.helper.LoggingUtil;
import com.wg.model.Issue;
import com.wg.repository.interfaces.InterfaceIssuesDAO;

@Service
public class IssueService {
	private InterfaceIssuesDAO issueDAO;
	Logger logger = LoggingUtil.getLogger(IssueService.class);

	public IssueService() {
	}

	@Autowired
	public IssueService(InterfaceIssuesDAO issueDAO) {
		this.issueDAO = issueDAO;
	}

	public void raiseIssue(Issue issue) {
		boolean flag = false;
		try {
			String randomString = UUID.randomUUID().toString();
			int desiredLength = 7;
			if (desiredLength > randomString.length()) {
				desiredLength = randomString.length();
			}
			String issueId = randomString.substring(0, desiredLength);
			issueId = 'L' + issueId;
			issue.setIssueID(issueId);
			flag = issueDAO.raiseIssue(issue);
			if (flag == true) {
				System.out.println("Issue raised Successfully");
			} else {
				System.out.println("Error raising issue");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public void resolveIssue(String userId) {
		boolean flag = false;
		try {
			flag = issueDAO.resolveIssue(userId);
			if (flag == true) {
				System.out.println("Issue resolved Successfully");
			} else {
				System.out.println("Error resolving issue");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Issue> checkIssueStatus(String userId) {
		List<Issue> issue = null;
		try {
			issue = issueDAO.checkIssueStatus(userId);
			return issue;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return issue;
	}

	public List<Issue> viewAllIssues() {
		List<Issue> issues = null;
		try {
			issues = issueDAO.viewAllIssues();
			if (issues == null) {
				throw new NotFoundExceptions("No issues available");
			}
			return issues;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return issues;
	}
}
