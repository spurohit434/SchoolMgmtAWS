package com.wg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.Issue;
import com.wg.model.StatusResponse;
import com.wg.services.IssueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class IssueController {
	private IssueService issueService;

	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	public IssueController() {
	}

	@PostMapping("/user/{id}/issue")
	public ResponseEntity<Object> raiseIssue(@Valid @RequestBody Issue issue) {
		issueService.raiseIssue(issue);
		return ApiResponseHandler.apiResponseHandler("Issues Raised Successfully", StatusResponse.Success,
				HttpStatus.CREATED, issue);
	}

	@PutMapping("/user/{id}/issue") // should have the issue id for multiple issues
	public void resolveIssue(@PathVariable String id) {
		issueService.resolveIssue(id);
	}

	@GetMapping("/user/{id}/issue")
	public ResponseEntity<Object> checkIssueStatus(@PathVariable String id) {
		List<Issue> issues = issueService.checkIssueStatus(id);
		return ApiResponseHandler.apiResponseHandler("Issues Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, issues);
	}

	@GetMapping("/issue")
	public ResponseEntity<Object> viewAllIssues() {
		List<Issue> issues = issueService.viewAllIssues();
		return ApiResponseHandler.apiResponseHandler("Issues Fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, issues);
	}
}