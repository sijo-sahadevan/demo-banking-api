package demo.banking.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.banking.api.service.UserService;

@RestController
@RequestMapping("/api/retailbanking")
public class UserController {
	@Autowired
	UserService service;

	@GetMapping("/user/{userid}/{password}")
	public boolean validateUser(@PathVariable("userid") String userID, @PathVariable("password") String password) {
		return service.validateUser(userID, password);
	}

	@GetMapping("/user/update/{userid}/{currentpwd}/{newpwd}")
	public int updatePassword(@PathVariable Map<String, String> params) {
		String userID = params.get("userid");
		String currentPassword = params.get("currentpwd");
		String newPassword = params.get("newpwd");
		return service.updatePassword(userID, currentPassword, newPassword);
	}
}
