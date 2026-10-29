package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;

public interface IAgenceService extends IGenericService<Agence, Long> {

    boolean hasVehicles(Long id);

    String simuler(Long idAgence, int nombreJours, VehiculeOffre offre);
}