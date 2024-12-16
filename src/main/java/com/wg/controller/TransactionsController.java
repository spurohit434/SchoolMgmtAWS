package com.wg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.dto.ApiResponseHandler;
import com.wg.model.StatusResponse;
import com.wg.model.Transactions;
import com.wg.services.TransactionsService;

@RestController
@RequestMapping("/api")
public class TransactionsController {

	private TransactionsService transactionsService;

	public TransactionsController() {
	}

	@Autowired
	public TransactionsController(TransactionsService transactionsService) {
		this.transactionsService = transactionsService;
	}

	@GetMapping("user/{id}/transaction")
	public ResponseEntity<Object> getTransaction(@PathVariable String id) {
		List<Transactions> transactions = transactionsService.getTransaction(id);
		return ApiResponseHandler.apiResponseHandler("Transaction fetched Successfully", StatusResponse.Success,
				HttpStatus.OK, transactions);
	}
}
