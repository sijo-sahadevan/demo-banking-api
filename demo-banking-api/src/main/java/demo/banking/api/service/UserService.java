package demo.banking.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.banking.api.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	UserRepository repository;

	public boolean validateUser(String userID, String password) {
		boolean validUser = false;
		if (repository.findByUserID(userID) != null && repository.validateUser(userID, password) != null) {
			validUser = true;
		}
		return validUser;
	}

	public int updatePassword(String userID, String currentPassword, String newPassword) {
		return (int) repository.updatePassword(userID, newPassword);
	}
}
