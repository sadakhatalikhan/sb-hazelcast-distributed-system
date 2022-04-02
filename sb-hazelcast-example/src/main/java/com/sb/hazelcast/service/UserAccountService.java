package com.sb.hazelcast.service;

import org.springframework.http.ResponseEntity;

import com.sb.hazelcast.request.UserAccountRequest;
import com.sb.hazelcast.response.Response;

public interface UserAccountService {
	
	ResponseEntity<Response> addAccountNumber(UserAccountRequest request);

	ResponseEntity<Response> getAccountDetails(String accountNumber);
	
	ResponseEntity<Response> deleteAccountNumber(String accountNumber);
}
