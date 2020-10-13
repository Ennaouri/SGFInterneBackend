package org.sgfourriere.security;

import java.util.ArrayList;
import java.util.Collection;

import org.sgfourriere.dao.AppUserRepository;
import org.sgfourriere.modele.AppUser;
import org.sgfourriere.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser=accountService.loadUserByUsername(username);
		if(appUser == null) throw new UsernameNotFoundException("invalide User");
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		appUser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(appUser.getUsername(),appUser.getPassword(),authorities);
	}

}
