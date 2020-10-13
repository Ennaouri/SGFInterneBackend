package org.sgfourriere.modele;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicule {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroMatricule;
	 
	private String marque;
	
	private String modele;
	
	@OneToMany (mappedBy = "vehicule")
	@JsonIgnore
	private Collection<Infraction> infractions;
	@ManyToOne
	
	private Categorie categorie;
}
