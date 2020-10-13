package org.sgfourriere.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Remorquage")
public class Remorquage extends Penalite{

	

	public Remorquage() {
		super(150);
		// TODO Auto-generated constructor stub
	}

	public Remorquage(Long id, double montant, Infraction infraction, Devis devis) {
		super(id, montant, infraction, devis);
		montant=150;
	}
	

}
