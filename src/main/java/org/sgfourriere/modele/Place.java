package org.sgfourriere.modele;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Place {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private double longitude,latitude,altitude;
	private boolean occupe;
	@ManyToOne
	@JsonIgnore
	private Parking parking;
	@OneToMany (mappedBy = "place")
	@JsonIgnore
	private Collection<Infraction> infractions;
}
