package org.sgfourriere.modele;

import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.STRING,length=20)
public abstract class Penalite {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double montant;
	@ManyToOne
	
	private Infraction infraction;
	@ManyToOne
	@JsonIgnore
	private Devis devis;
	
	Penalite(double montant){
		this.montant=montant;
	}
}
