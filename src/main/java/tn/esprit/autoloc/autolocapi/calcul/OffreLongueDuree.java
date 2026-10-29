package tn.esprit.autoloc.autolocapi.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class OffreLongueDuree implements IOffreStrategy {
    @Override
    public double calculer(double tarifJournalier) {
        return tarifJournalier * 0.75; // -25%
    }
}