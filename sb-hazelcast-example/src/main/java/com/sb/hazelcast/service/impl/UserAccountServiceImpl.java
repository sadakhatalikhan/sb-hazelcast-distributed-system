package com.sb.hazelcast.service.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sb.hazelcast.cache.HazelcastMapManager;
import com.sb.hazelcast.helper.UserAccountHelper;
import com.sb.hazelcast.model.UserAccount;
import com.sb.hazelcast.repository.UserAccountRepository;
import com.sb.hazelcast.request.UserAccountRequest;
import com.sb.hazelcast.response.Response;
import com.sb.hazelcast.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountRepository repository;
	private final UserAccountHelper helper;
	private final Map<String, UserAccount>  accountMap;
	private final HazelcastMapManager hazelcastMapManager;
	
	@Override
	public ResponseEntity<Response> addAccountNumber(UserAccountRequest request) {
		
		UserAccount accountModel = repository.save(helper.modelMapper(request));
		// Post saving the object into database, add the account object into hashmap
		//accountMap.put(accountModel.getAccountNumber(), accountModel);
		hazelcastMapManager.putToMap(accountModel.getAccountNumber(), accountModel);
		return ResponseEntity.ok().body(
				Response.builder()
					.code(0)
					.message("Successfully created account")
					.body(helper.dtoMapper(accountModel)).build());
		
	}

	@Override
	public ResponseEntity<Response> getAccountDetails(String accountNumber) {
		// If account number available in hashmap then system will load the useraccount object 
		// from hashmap table else it will fetch the data from the database
		/*UserAccount useraccount = (accountMap.get(accountNumber) != null) ?
				accountMap.get(accountNumber):
					repository.findByAccountNumber(accountNumber); */
		UserAccount useraccount = (hazelcastMapManager.getMapItemWithKey(accountNumber) != null)?
				hazelcastMapManager.getMapItemWithKey(accountNumber):
					repository.findByAccountNumber(accountNumber);
		
		return ResponseEntity.ok().body(
				Response.builder()
					.code(0)
					.message("Data loaded successfully")
					.body(helper.dtoMapper(useraccount)).build());
	
	}

	@Override
	public ResponseEntity<Response> deleteAccountNumber(String accountNumber) {
		// remove the object from the hasmap
		hazelcastMapManager.removeMapItemWithKey(accountNumber);
		return ResponseEntity.ok().body(
				Response.builder()
					.code(0)
					.message("Data loaded successfully")
					.body(helper.dtoMapper(repository.deleteByAccountNumber(accountNumber))).build());
	}
	
}