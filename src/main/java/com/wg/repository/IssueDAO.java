package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wg.constants.IssueConstants;
import com.wg.model.Issue;
import com.wg.model.IssuesStatus;
import com.wg.repository.interfaces.InterfaceIssuesDAO;

@Component
public class IssueDAO extends GenericDAO<Issue> implements InterfaceIssuesDAO {

	public IssueDAO() {
		super();
	}

	@Override
	protected Issue mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Issue issue = new Issue();
		issue.setIssueID(resultSet.getString(IssueConstants.ISSUE_ID_COLUMN));
		issue.setMessage(resultSet.getString(IssueConstants.MESSAGE_COLUMN));
		issue.setUserId(resultSet.getString(IssueConstants.USER_ID_COLUMN));
		issue.setStatus(IssuesStatus.valueOf(resultSet.getString("status")));
		issue.setCreatedAt(resultSet.getDate("createdAt").toLocalDate());
		return issue;
	}

	@Override
	public Issue getIssueById(String issueId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", IssueConstants.ISSUE_TABLE_NAME,
				IssueConstants.ISSUE_ID_COLUMN, issueId);
		return executeGetQuery(query);
	}

	@Override
	public List<Issue> viewAllIssues() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", IssueConstants.ISSUE_TABLE_NAME);
		return executeGetAllQuery(query);
	}

	@Override
	public List<Issue> checkIssueStatus(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", IssueConstants.ISSUE_TABLE_NAME,
				IssueConstants.USER_ID_COLUMN, userId);
		return executeGetAllQuery(query);
	}

	@Override
	public boolean resolveIssue(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE Issue SET Status = '%s' WHERE userId = '%s'",
				IssuesStatus.RESOLVED.toString(), userId);
		// System.out.println(query);
		return executeQuery(query);
	}

	@Override
	public boolean raiseIssue(Issue issue) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s) " + "VALUES ('%s', '%s', '%s', '%s')",
				IssueConstants.ISSUE_TABLE_NAME, IssueConstants.ISSUE_ID_COLUMN, IssueConstants.MESSAGE_COLUMN,
				IssueConstants.USER_ID_COLUMN, IssueConstants.STATUS_COLUMN, issue.getIssueID(), issue.getMessage(),
				issue.getUserId(), issue.getStatus().toString());
		return executeQuery(query);
	}

}