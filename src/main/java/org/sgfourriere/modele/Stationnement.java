package org.sgfourriere.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("Stationnement")
public class Stationnement extends Penalite {

	

	public Stationnement() {
		super(20);
		
	}
	
	

	public Stationnement(Long id, double montant, Infraction infraction, Devis devis) {
		super(id, montant, infraction, devis);
		
	}
	
}
