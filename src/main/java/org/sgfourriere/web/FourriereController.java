package org.sgfourriere.web;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sgfourriere.dao.CategorieRepository;
import org.sgfourriere.dao.DepannageRepository;
import org.sgfourriere.dao.DevisRepository;
import org.sgfourriere.dao.InfractionRepository;
import org.sgfourriere.dao.PenaliteRepository;
import org.sgfourriere.dao.PlaceRepository;
import org.sgfourriere.dao.PolicierRepository;
import org.sgfourriere.dao.VehiculeRepository;
import org.sgfourriere.modele.Categorie;
import org.sgfourriere.modele.Depannage;
import org.sgfourriere.modele.Devis;
import org.sgfourriere.modele.FormFacture;
import org.sgfourriere.modele.FormInfraction;
import org.sgfourriere.modele.Infraction;
import org.sgfourriere.modele.Parkinage;
import org.sgfourriere.modele.Penalite;
import org.sgfourriere.modele.Place;
import org.sgfourriere.modele.Policier;
import org.sgfourriere.modele.Remorquage;
import org.sgfourriere.modele.Stationnement;
import org.sgfourriere.modele.Vehicule;
import org.sgfourriere.modele.Vole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.compiler.ast.InstanceOfExpr;
import javassist.expr.Instanceof;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FourriereController {
	@Autowired
	private VehiculeRepository vehiculeRepository;
	@Autowired
	private InfractionRepository infractionRepository;
	@Autowired
	private PenaliteRepository penaliteRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private DevisRepository devisRepository;
	@Autowired
	private DepannageRepository depannageRepository;
	@Autowired
	private PolicierRepository policierRepository;
	
	
	@RequestMapping("/vehicules")
	public List<Vehicule> getVoitures(){
	
		return vehiculeRepository.findAll();
	}
	
	@RequestMapping("/infractions")
	public List<Infraction> getInfractions(){
	
		return infractionRepository.findAll();
	}
	
	@RequestMapping("/penalites")
	public List<Penalite> getPenalites(){
	
		return penaliteRepository.findAll();
	}
	
	@RequestMapping("/categories")
	public List<Categorie> getCategories(){
	
		return categorieRepository.findAll();
	}
	
	@RequestMapping("/places")
	public List<Place> getPlaces(){
	
		return placeRepository.findAll();
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value ="/facture")
	
	public void ajouterFacture(@RequestBody FormFacture facture) {
		Devis devis=new Devis();
		Infraction infraction= infractionRepository.findById(facture.getInfractionId()).get();
		
        
        
        
        devis.setValidePaiement(facture.isValidePaiement());
		devis.setCodePaiement(123);
		
		devis.setInfraction(infraction);
		devis.setValideAssurance(facture.isValideAssurance());
		devis.setValideCarteGrise(facture.isValideCarteGrise());
		devis.setValidePermis(facture.isValidePermis());
		devis.setValideVignette(facture.isValideVignette());
		devis.setValideVisiteTechnique(facture.isValideVisiteTechnique());
		devis.setMontantTotal(facture.getMontantTotal());
		if(facture.isValideCarteGrise() && facture.isValideAssurance() && facture.isValidePermis() && 
				facture.isValideVignette() && facture.isValideVisiteTechnique() && facture.isValidePaiement()) {
			devis.setDatePaiement(facture.getDatePaiement());
			infraction.setDateSortie(facture.getDatePaiement());
		}
		System.out.println("la infraction est :");
		System.out.println("la facture est :" + facture);
		devisRepository.save(devis);
		infraction.setDevis(devis);
		infractionRepository.save(infraction);
		List<Penalite> penalites = penaliteRepository.findAll();
        penalites.forEach(penalite -> {
        	if(penalite.getInfraction().getId() == facture.getInfractionId()) {
        		penalite.setDevis(devis);
        		penaliteRepository.save(penalite);
        	}
        	System.out.println("penalite " + penalite.getId());
        });
	}
	
	@GetMapping("/depannages")
	public List<Depannage> getDepannages(){
		return depannageRepository.findAll();
	}
	@GetMapping("/policiers")
	public List<Policier> getPoliciers(){
		return policierRepository.findAll();
	}
	
	@PostMapping("/ajouterInfraction")
	@Transactional
	public void addInfraction(@RequestBody FormInfraction formInfraction) {
		System.out.println("Image Inf " + formInfraction);
		System.out.println("form Inf " + formInfraction);
		Infraction infraction=new Infraction();
		Vehicule vehicule=new Vehicule();
		infraction.setVehicule(vehicule);
		Penalite parkinage=new Parkinage();
		parkinage.setInfraction(infraction);
		Penalite remorquage=new Remorquage();
		remorquage.setInfraction(infraction);
		vehicule.setNumeroMatricule(formInfraction.getNumeroMatricule());
		vehicule.setMarque(formInfraction.getMarqueVehicule());
		vehicule.setCategorie(categorieRepository.findByLibelle(formInfraction.getTypeVehicule()));
		vehiculeRepository.save(vehicule);
		infraction.setPolicier(policierRepository.findById(formInfraction.getPolicierId()).get());
		infraction.setDepannage(depannageRepository.findById(formInfraction.getDepannageId()).get());
		
		List<Place> places=placeRepository.findAll();
		for(Place place : places) {
			if(place.isOccupe() == false) {
				infraction.setPlace(place);
				place.setOccupe(true);
				placeRepository.save(place);
				break;
			}
		}
		switch(formInfraction.getTypeInfraction()) {
			case "stationnement" : Penalite stationnement=new Stationnement();
									stationnement.setInfraction(infraction);
									penaliteRepository.save(stationnement);
								break;
			case "vole" : Penalite vole=new Vole();
							penaliteRepository.save(vole);
							vole.setInfraction(infraction);
							break;
			default : break;
		}
		penaliteRepository.save(remorquage);
		penaliteRepository.save(parkinage);
		infraction.setDateEntre(formInfraction.getDateEntre());
		infraction.setType(formInfraction.getTypeInfraction());
		infractionRepository.save(infraction);
	}
	@GetMapping("/infractions/{id}")
	public void UpdateMontant(@PathVariable Long id) {
		penaliteRepository.findAll().forEach(penalite -> {
			if (penalite instanceof Parkinage) {
				System.out.println("je suis bien dans stationnement");
				
			} 
			System.out.println("je suis pas bien dans stationnement");
		
		});
	}
	
	@DeleteMapping("/delete/{id}")
	public void DeleteInfraction(@PathVariable Long id) {
		
		Infraction infraction=infractionRepository.findById(id).get();
		List<Penalite> penalites=penaliteRepository.findAll();
		penalites.forEach(penalite->{
			if(penalite.getInfraction().getId().equals(id)) {
				System.out.println("je suis bien dans delete ");
				penaliteRepository.delete(penalite);
			}
		});
		if(infraction.getDevis()!=null) {
			System.out.println("un devis existe");
			devisRepository.deleteById(infraction.getDevis().getId());
		}
			vehiculeRepository.deleteById(infraction.getVehicule().getId());
			
			placeRepository.findById(infraction.getPlace().getId()).get().setOccupe(false);
			infractionRepository.deleteById(id);
		
		
	}
	
	
}
