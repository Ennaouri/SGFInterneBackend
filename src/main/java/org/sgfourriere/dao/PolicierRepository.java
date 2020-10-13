package org.sgfourriere.dao;

import org.sgfourriere.modele.Fourriere;
import org.sgfourriere.modele.Policier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface PolicierRepository extends JpaRepository<Policier, Long>{

}
