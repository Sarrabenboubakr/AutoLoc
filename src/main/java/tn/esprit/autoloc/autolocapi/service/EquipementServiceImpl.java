package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Equipement equipement, boolean isNew) {
        return Utils.isNotEmpty(equipement.getNom());
    }

    @Override
    protected String getEntityName() {
        return "Equipement";
    }

    @Override
    protected Long getId(Equipement entity) {
        return entity.getId();
    }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        target.setNom(source.getNom());
        target.setDescription(source.getDescription());
        target.setPrix(source.getPrix());
    }
}