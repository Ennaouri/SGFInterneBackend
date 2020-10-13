package org.sgfourriere.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Parkinage")
public class Parkinage extends Penalite{

	

	public Parkinage() {
		super(20);
		// TODO Auto-generated constructor stub
	}

	public Parkinage(Long id, double montant, Infraction infraction, Devis devis) {
		super(id, montant, infraction, devis);
		montant=20;
	}
	

}
