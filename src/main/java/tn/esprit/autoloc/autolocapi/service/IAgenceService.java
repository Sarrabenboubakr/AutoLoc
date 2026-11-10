package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository; // <-- IMPORT REPOSITORY

import java.util.List;


public interface IAgenceService extends IGenericService<Agence, Long> {

    boolean hasVehicles(Long id);

    String simuler(Long idAgence, int nombreJours, VehiculeOffre offre);
    Agence ajouterEmployes(Long idAgence, List<Employe> employes);

}