package tn.esprit.autoloc.autolocapi.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.io.RapportFactory;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.util.List;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory;
    private final IEmployeService employeService;

    public AgenceServiceImpl(IAgenceRepository repository, IVehiculeService vehiculeService,
                             RapportFactory rapportFactory, @Lazy IEmployeService employeService) {
        super(repository);
        this.vehiculeService = vehiculeService;
        this.rapportFactory = rapportFactory;
        this.employeService = employeService;
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

    // === CORRECTION S1186 ===
    @Override
    public boolean hasVehicles(Long id) {
        throw new UnsupportedOperationException("La méthode hasVehicles n'est pas encore implémentée");
    }

    @Override
    public String simuler(Long idAgence, int nbJours, VehiculeOffre offre) {
        Agence agence = findById(idAgence);
        double total = 0;
        if (agence.getVehicules() != null) {
            for (Vehicule vehicule : agence.getVehicules()) {
                total += vehiculeService.calculTarif(vehicule, nbJours, offre);
            }
        }
        return rapportFactory.getRapport().generer(agence, total);
    }

    @Override
    @Transactional
    public Agence ajouterEmployes(Long idAgence, List<Employe> employes) {
        Agence agence = findById(idAgence);

        for (Employe e : employes) {
            e.setAgence(agence);
            employeService.create(e);
        }

        agence.setEffectif(employes.size());
        return repository.save(agence);
    }
}