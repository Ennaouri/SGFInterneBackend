package org.sgfourriere;

import java.util.stream.Stream;

import org.sgfourriere.modele.AppRole;
import org.sgfourriere.modele.AppUser;
import org.sgfourriere.service.AccountService;
import org.sgfourriere.service.FourriereInitServiceImp;
import org.sgfourriere.service.IFourriereInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SgfourriereApplication implements CommandLineRunner{
	@Autowired
	private IFourriereInit fourriereInitService;
	
	@Autowired
	private AccountService accountService;

	
	public static void main(String[] args) {
		SpringApplication.run(SgfourriereApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		fourriereInitService.initVilles();
		fourriereInitService.initFourrieres();
		fourriereInitService.initParkings();
		fourriereInitService.initPlaces();
		fourriereInitService.initPoliciers();
		fourriereInitService.initDeppanage();
		fourriereInitService.initCategories();
		/*
		 * fourriereInitService.initPenalities(); fourriereInitService.initVehicules();
		 */
		accountService.save(new AppRole(null,"USER"));
		accountService.save(new AppRole(null,"ADMIN"));
		Stream.of("user1","user2","user3","admin").forEach(un->{
			accountService.saveUser(new AppUser(null,un,"1234",null));
		});
			accountService.addRoleToUser("admin", "ADMIN");
		}
			
		
		
	
	
	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
