package tn.esprit.autoloc.autolocapi.io;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;

@Component("rapportSimple")
public class RapportSimple implements Rapport {
    @Override
    public String generer(Agence agence, double total) {
        // On retourne juste le total (le texte simple)
        return "Total tarif : " + total;
    }
}