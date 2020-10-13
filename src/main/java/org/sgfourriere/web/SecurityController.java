package org.sgfourriere.web;

import org.sgfourriere.modele.AppUser;
import org.sgfourriere.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/register")
	public AppUser register(@RequestBody RegistreForm user) {
		if(!user.getPassword().equals(user.getRepassword())) throw new RuntimeException("you must confirm");
		AppUser user2=accountService.findUserByUsername(user.getUsername());
		if(user!=null) throw new RuntimeException("this user already exists");
		AppUser appUser=new AppUser();
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		accountService.saveUser(appUser);
		accountService.addRoleToUser(user.getUsername(), "USER");
		return appUser;
	}
}
