package org.sgfourriere.modele;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormFacture {
	
	private double montantTotal;
	private int codePaiement;
	private Long infractionId;
	private boolean valideCarteGrise;
	private boolean validePermis;
	private boolean valideAssurance;
	private boolean valideVignette;
	private boolean valideVisiteTechnique;
	private boolean validePaiement;
	private Date datePaiement;
}
