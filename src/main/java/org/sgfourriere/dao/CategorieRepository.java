package org.sgfourriere.dao;

import org.sgfourriere.modele.Categorie;
import org.sgfourriere.modele.Fourriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	public Categorie findByLibelle(String libelle);
}
