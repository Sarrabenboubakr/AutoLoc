package tn.esprit.autoloc.autolocapi.io;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;

@Primary // On dit à Spring d'utiliser cette version par défaut
@Component
public class RapportConsoleDecorator implements Rapport {

    private final Rapport rapport;

    public RapportConsoleDecorator(@Qualifier("rapportSimple") Rapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public String generer(Agence agence, double total) {
        // 1. Affichage sur la console (ce que demande le prof)
        System.out.println("=== EN-TÊTE AGENCE ===");
        System.out.println("ID: " + agence.getId());
        System.out.println("Nom: " + agence.getNom());
        System.out.println("Téléphone: " + agence.getTelephone());

        // 2. Retourne le total via le rapport simple
        return rapport.generer(agence, total);
    }
}