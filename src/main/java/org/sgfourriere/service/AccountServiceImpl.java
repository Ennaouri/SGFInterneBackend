package org.sgfourriere.service;



import org.sgfourriere.dao.AppRoleRepository;
import org.sgfourriere.dao.AppUserRepository;
import org.sgfourriere.modele.AppRole;
import org.sgfourriere.modele.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	

	

	@Override
	public AppUser saveUser(AppUser user) {
		String hashPw=bCryptPasswordEncoder.encode(user.getPassword());
		
				user.setPassword(hashPw);
		return appUserRepository.save(user);
	}

	@Override
	public AppRole save(AppRole role) {
		appRoleRepository.save(role);
		return null;
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser=appUserRepository.findByUsername(username);
		AppRole appRole=appRoleRepository.findByRoleName(roleName);
		appUser.getRoles().add(appRole);
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(username);
	}

}
