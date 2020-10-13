package org.sgfourriere.dao;

import org.sgfourriere.modele.Devis;
import org.sgfourriere.modele.Fourriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface DevisRepository extends JpaRepository<Devis, Long>{

}
