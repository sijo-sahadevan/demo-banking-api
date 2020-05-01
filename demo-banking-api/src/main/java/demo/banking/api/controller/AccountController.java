package demo.banking.api.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.banking.api.model.Account;
import demo.banking.api.service.AccountService;

@RestController
@RequestMapping("/api/retailbanking")
public class AccountController {
	@Autowired
	AccountService service;

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@GetMapping("/accounts/{userid}")
	public List<Account> getAccountList(@PathVariable("userid") String userID) {
		logger.debug("request - fetch account list for the user '" + userID + "'.");
		return service.getAccountList(userID);
	}

	@GetMapping("/accounts/{userid}/{accountid}/{nickname}")
	public void createORUpdateAccountNickName(@PathVariable Map<String, String> params) {
		String userID = params.get("userid");
		String accountID = params.get("accountid");
		String nickName = params.get("nickname");
		logger.debug("request - fetch create or update account nickname for the account '" + accountID + "' & user '"
				+ userID + "'.");
		service.createORUpdateAccountNickName(userID, accountID, nickName);
	}

}
