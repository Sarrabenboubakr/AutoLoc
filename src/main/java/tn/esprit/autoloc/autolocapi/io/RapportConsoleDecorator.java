package tn.esprit.autoloc.autolocapi.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;

@Primary
@Component
public class RapportConsoleDecorator implements Rapport {

    // 1. On déclare le logger
    private static final Logger log = LoggerFactory.getLogger(RapportConsoleDecorator.class);

    private final Rapport rapport;

    public RapportConsoleDecorator(@Qualifier("rapportSimple") Rapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public String generer(Agence agence, double total) {
        // 2. On remplace les System.out.println par log.info
        log.info("=== EN-TÊTE AGENCE ===");
        log.info("ID: {}", agence.getId());
        log.info("Nom: {}", agence.getNom());
        log.info("Téléphone: {}", agence.getTelephone());

        return rapport.generer(agence, total);
    }
}