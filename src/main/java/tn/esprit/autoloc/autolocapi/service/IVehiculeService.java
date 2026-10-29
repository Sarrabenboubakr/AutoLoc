package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

public interface IVehiculeService extends IGenericService<Vehicule, Long> {
    double calculTarif(Vehicule vehicule, int nombreJours, VehiculeOffre offre);

}