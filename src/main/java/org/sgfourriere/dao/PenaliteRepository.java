package org.sgfourriere.dao;

import java.util.List;

import org.sgfourriere.modele.Categorie;
import org.sgfourriere.modele.Fourriere;
import org.sgfourriere.modele.Infraction;
import org.sgfourriere.modele.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PenaliteRepository extends JpaRepository<Penalite, Long>{

	
	
}
