package org.sgfourriere.service;

import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sgfourriere.dao.CategorieRepository;
import org.sgfourriere.dao.DepannageRepository;
import org.sgfourriere.dao.DevisRepository;
import org.sgfourriere.dao.FourriereRepository;
import org.sgfourriere.dao.InfractionRepository;
import org.sgfourriere.dao.ParkingRepository;
import org.sgfourriere.dao.PenaliteRepository;
import org.sgfourriere.dao.PlaceRepository;
import org.sgfourriere.dao.PolicierRepository;
import org.sgfourriere.dao.VehiculeRepository;
import org.sgfourriere.dao.VilleRepository;
import org.sgfourriere.modele.Categorie;
import org.sgfourriere.modele.Depannage;
import org.sgfourriere.modele.Fourriere;
import org.sgfourriere.modele.Parking;
import org.sgfourriere.modele.Penalite;
import org.sgfourriere.modele.Place;
import org.sgfourriere.modele.Policier;
import org.sgfourriere.modele.Vehicule;
import org.sgfourriere.modele.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class FourriereInitServiceImp implements IFourriereInit{

	@Autowired
	public VilleRepository villeRepository;
	@Autowired
	public FourriereRepository fourriereRepository;
	@Autowired
	public ParkingRepository parkingRepository;
	@Autowired
	public PlaceRepository placeRepository;
	@Autowired
	public InfractionRepository infractionRepository;
	@Autowired
	public VehiculeRepository vehiculeRepository;
	@Autowired
	public PolicierRepository policierRepository;
	@Autowired
	public CategorieRepository categorieRepository;
	@Autowired
	public PenaliteRepository penaliteRepository;
	@Autowired
	public DevisRepository devisRepository;
	@Autowired
	public DepannageRepository depannageRepository;
	
	
	@Override
	public void initVilles() {
		Stream.of("Rabat").forEach(nomVille->{
			Ville ville=new Ville();
			ville.setNom(nomVille);
			villeRepository.save(ville);
		});
		
	}

	@Override
	public void initFourrieres() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Chebanat","Sale","Temara")
				.forEach(nomFourriere->{
					Fourriere fourriere=new Fourriere();
					fourriere.setLibelle(nomFourriere);
					fourriere.setVille(v);
					fourriereRepository.save(fourriere);
				});
		});
		
	}

	@Override
	public void initParkings() {
		fourriereRepository.findAll()
				.forEach(fourriere->{
					Parking parking=new Parking();
					parking.setNombrePlace(1+(int)(Math.random()*10));
					parking.setFourriere(fourriere);
					fourriere.setParking(parking);
					parkingRepository.save(parking);
					fourriereRepository.save(fourriere);
				});
		
		
	}

	@Override
	public void initPlaces() {
		parkingRepository.findAll().forEach(parking->{
			for(int i=0;i<parking.getNombrePlace();i++) {
				Place place=new Place();
				place.setLibelle(parking.getFourriere().getLibelle()+"Place" + (i+1));
				place.setParking(parking);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initInfractions() {
		
		
	}

	@Override
	public void initPoliciers() {
		Stream.of("Zakaria Elmoumin","Hamid Rajaoui","Mouad Farouk","Fadil Hamdaoui").forEach(p->{
			Policier policier=new Policier();
			String[] prenomNom=p.split(" ");
			policier.setNom(prenomNom[0]);
			policier.setPrenom(prenomNom[1]);
			policierRepository.save(policier);
		});
		
	}

	@Override
	public void initDeppanage() {
		Stream.of("Said Dep","Hakim Dep","khalid Dep","Mounir Dep").forEach(d->{
			Depannage depannage=new Depannage();
			String[] prenomNom=d.split(" ");
			depannage.setNom(prenomNom[0]);
			depannage.setPrenom(prenomNom[1]);
			depannageRepository.save(depannage);
		});
		
	}

	/*
	 * @Override public void initPenalities() {
	 * Stream.of("staionnement","PermisOff").forEach(pen->{ Penalite penalite=new
	 * Penalite(); penalite.setLibelle(pen);
	 * penalite.setMontant(10*(int)(Math.random()*20));
	 * penaliteRepository.save(penalite); });
	 * 
	 * }
	 */

	@Override
	public void initDevis() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initVehicules() {
		Vehicule vehicule=new Vehicule();
		vehicule.setMarque("dacia");
		vehicule.setModele("03/2011");
		vehicule.setNumeroMatricule("ABC123");
	
		vehiculeRepository.save(vehicule);
	}

	@Override
	public void initCategories() {
		Stream.of("leger","lourd","moto").forEach(cat ->{
			Categorie categorie=new Categorie();
			categorie.setLibelle(cat);
			categorieRepository.save(categorie);
			
		});
		
	}

	@Override
	public void initPenalities() {
		// TODO Auto-generated method stub
		
	}

}
