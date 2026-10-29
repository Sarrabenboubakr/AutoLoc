package tn.esprit.autoloc.autolocapi.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.io.RapportFactory; // <-- IMPORT
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory; // <-- AJOUT

    public AgenceServiceImpl(IAgenceRepository repository, IVehiculeService vehiculeService, RapportFactory rapportFactory) {
        super(repository);
        this.vehiculeService = vehiculeService;
        this.rapportFactory = rapportFactory; // <-- AJOUT
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence entity) {
        return entity.getId();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean hasVehicles(Long id) {
        return false;
    }

    // ─── QUESTION 14 : Utilisation de la Factory ───
    @Override
    public String simuler(Long idAgence, int nbJours, VehiculeOffre offre) {
        Agence agence = findById(idAgence);

        double total = 0;
        if (agence.getVehicules() != null) {
            for (Vehicule vehicule : agence.getVehicules()) {
                total += vehiculeService.calculTarif(vehicule, nbJours, offre);
            }
        }

        // ON REMPLACE LE STRINGBUILDER PAR LA FACTORY
        return rapportFactory.getRapport().generer(agence, total);
    }
}