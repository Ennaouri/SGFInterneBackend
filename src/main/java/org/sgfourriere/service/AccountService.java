package org.sgfourriere.service;

import org.sgfourriere.modele.AppRole;
import org.sgfourriere.modele.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole save(AppRole role);
	public AppUser loadUserByUsername(String username);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUsername(String username);
}
