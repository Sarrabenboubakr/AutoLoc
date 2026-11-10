package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.calcul.IOffreStrategy;
import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.util.Map;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    private final IAgenceRepository agenceRepository;
    private final Map<String, IOffreStrategy> strategies;

    // Ce constructeur est correct
    public VehiculeServiceImpl(IVehiculeRepository repository, IAgenceRepository agenceRepository, Map<String, IOffreStrategy> strategies) {
        super(repository);
        this.agenceRepository = agenceRepository;
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

    @Override
    public double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {
        double tarifDeBase = vehicule.getPrixParJour().doubleValue();
        IOffreStrategy strategy = strategies.get(offre.name());
        double tarifReduit = strategy.calculer(tarifDeBase);
        double total = tarifReduit * nbJours;

        if (offre == VehiculeOffre.LONGUE_DUREE && total > 2000) {
            total *= 0.95;
        }
        return total;
    }

    @Override
    public Vehicule desaffecter(Long id) {
        Vehicule vehicule = findById(id);
        vehicule.setAgence(null);
        return repository.save(vehicule);
    }

    @Override
    public Vehicule affecterAgence(Long idVehicule, Long idAgence) {
        Vehicule v = findById(idVehicule);
        Agence a = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence.toString()));
        v.setAgence(a);
        return repository.save(v);
    }

    @Override
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        return null;
    }
}