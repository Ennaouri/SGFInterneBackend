package org.sgfourriere.modele;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Devis {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double montantTotal;
	private int codePaiement;
	private boolean valideCarteGrise;
	private boolean validePermis;
	private boolean valideAssurance;
	private boolean valideVignette;
	private boolean valideVisiteTechnique;
	private boolean validePaiement;
	private Date datePaiement;
	@OneToMany (mappedBy = "devis")
	@JsonIgnore
	private Collection<Penalite> penalites;
	@OneToOne
	private Infraction infraction;
}
