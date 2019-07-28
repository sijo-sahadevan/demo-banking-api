package demo.banking.api.controller;

import java.util.List;
import java.util.Map;

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

	@GetMapping("/accounts/{userid}")
	public List<Account> getAccountList(@PathVariable("userid") String userID) {
		return service.getAccountList(userID);
	}

	@GetMapping("/accounts/{userid}/{accountid}/{nickname}")
	public void createORUpdateAccountNickName(@PathVariable Map<String, String> params) {
		String userID = params.get("userid");
		String accountID = params.get("accountid");
		String nickName = params.get("nickname");

		service.createORUpdateAccountNickName(userID, accountID, nickName);
	}

}
