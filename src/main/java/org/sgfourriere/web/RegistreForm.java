package org.sgfourriere.web;

import javax.persistence.Entity;

import lombok.Data;


@Data
public class RegistreForm {
	private String username;
	private String password;
	private String repassword;
}
