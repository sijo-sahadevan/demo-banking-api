package demo.banking.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.banking.api.model.Account;
import demo.banking.api.repository.AccountRepository;

@Component
public class AccountService {
	@Autowired
	AccountRepository repository;

	public List<Account> getAccountList(String userID) {
		return (List<Account>) repository.findByUserID(userID);
	}

	public void createORUpdateAccountNickName(String userID, String accountID, String nickName) {
		repository.createORUpdateAccountNickName(userID, accountID, nickName);
	}
}
