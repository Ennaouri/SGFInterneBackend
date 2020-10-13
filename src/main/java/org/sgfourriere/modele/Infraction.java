package org.sgfourriere.modele;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Infraction {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
	private Date dateEntre;
	private Date dateSortie;
	private String type;
	private String preuve;
	
	@ManyToOne
	private Vehicule vehicule;
	
	@ManyToOne
	
	private Policier policier;
	@ManyToOne
	
	private Place place;
	@OneToMany (mappedBy = "infraction")
	@JsonIgnore
	private Collection<Penalite> penalites;
	@ManyToOne
	private Depannage depannage;
	@OneToOne
	@JsonIgnore
	private Devis devis;
}
