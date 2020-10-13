package org.sgfourriere.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vole")
public class Vole extends Penalite{

	

	public Vole() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	public Vole(Long id, double montant, Infraction infraction, Devis devis) {
		super(id, montant, infraction, devis);
		montant=0;
	}
	

}
