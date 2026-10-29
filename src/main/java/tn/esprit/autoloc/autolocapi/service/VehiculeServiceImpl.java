package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.calcul.IOffreStrategy;
import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.util.Map;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    private final Map<String, IOffreStrategy> strategies;

    // Le constructeur injecte le repository ET la Map de stratégies
    public VehiculeServiceImpl(IVehiculeRepository repository, Map<String, IOffreStrategy> strategies) {
        super(repository);
        this.strategies = strategies;
    }

    @Override
    public boolean check(Vehicule vehicule, boolean isNew) {
        return Utils.isNotEmpty(vehicule.getImmatriculation())
                && Utils.isNotEmpty(vehicule.getMarque());
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule entity) {
        return entity.getId();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setAnnee(source.getAnnee());
        target.setCouleur(source.getCouleur());
        target.setKilometrage(source.getKilometrage());
        target.setPrixParJour(source.getPrixParJour());
        target.setStatut(source.getStatut());
        target.setCategorie(source.getCategorie());
        target.setAgence(source.getAgence());
    }

    // === QUESTION 16 : Pattern Strategy ===
    @Override
    public double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {
        double tarifDeBase = vehicule.getPrixParJour().doubleValue();

        // 1. On récupère la stratégie depuis la Map (ex: "WEEKEND")
        IOffreStrategy strategy = strategies.get(offre.name());

        // 2. On applique la réduction
        double tarifReduit = strategy.calculer(tarifDeBase);

        // 3. On calcule le total
        double total = tarifReduit * nbJours;

        // 4. Réduction de 5% pour longue durée si > 2000
        if (offre == VehiculeOffre.LONGUE_DUREE && total > 2000) {
            total *= 0.95;
        }

        return total;
    }
}