package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long> implements IContratService {

    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Contrat contrat, boolean isNew) {
        // On vérifie que les champs obligatoires ne sont pas nuls
        return contrat.getDateSignature() != null
                && contrat.getMontantTotal() != null
                && contrat.getValide() != null;
    }

    @Override
    protected String getEntityName() {
        return "Contrat";
    }

    @Override
    protected Long getId(Contrat entity) {
        return entity.getId();
    }

    @Override
    protected void setFields(Contrat source, Contrat target) {
        // On copie tous les champs de l'entité source vers l'entité cible
        target.setDateSignature(source.getDateSignature());
        target.setMontantTotal(source.getMontantTotal());
        target.setValide(source.getValide());
    }
}