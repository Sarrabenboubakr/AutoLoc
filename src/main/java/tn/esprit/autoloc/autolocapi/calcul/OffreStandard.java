package tn.esprit.autoloc.autolocapi.calcul;

import org.springframework.stereotype.Component;

@Component("STANDARD")
public class OffreStandard implements IOffreStrategy {
    @Override
    public double calculer(double tarifJournalier) {
        return tarifJournalier;
    }
}