package org.sgfourriere.dao;

import org.sgfourriere.modele.Fourriere;
import org.sgfourriere.modele.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{

}
