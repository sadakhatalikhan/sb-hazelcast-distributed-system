package com.sb.hazelcast.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.hazelcast.request.UserAccountRequest;
import com.sb.hazelcast.response.Response;
import com.sb.hazelcast.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank/account")
public class UserAccountController {
	
	private final UserAccountService userAccountService;
	
	// add account number
	@PostMapping("/add")
	public ResponseEntity<Response> addAccountNumber(@RequestBody UserAccountRequest request) {
		return userAccountService.addAccountNumber(request);
	}
	
	// get account number
	@GetMapping("/details/{accountNumber}")
	public ResponseEntity<Response> getAccountDetails(@PathVariable String accountNumber) {
		return userAccountService.getAccountDetails(accountNumber);
	}
	
	// delete account number
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<Response> deleteAccountNumber(@PathVariable String accountNumber) {
		return userAccountService.deleteAccountNumber(accountNumber);
	}
	
}
