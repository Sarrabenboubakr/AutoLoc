package tn.esprit.autoloc.autolocapi.calcul;

import org.springframework.stereotype.Component;

@Component("weekend") // <-- minuscule
public class OffreWeekend implements IOffreStrategy {
    @Override
    public double calculer(double tarifJournalier) {
        return tarifJournalier * 0.80; // -20%
    }
}