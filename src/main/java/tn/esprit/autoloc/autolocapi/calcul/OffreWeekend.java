package tn.esprit.autoloc.autolocapi.calcul;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class OffreWeekend implements IOffreStrategy {
    @Override
    public double calculer(double tarifJournalier) {
        return tarifJournalier * 0.80; // -20%
    }
}