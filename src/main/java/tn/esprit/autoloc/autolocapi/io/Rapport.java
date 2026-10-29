package tn.esprit.autoloc.autolocapi.io;

import tn.esprit.autoloc.autolocapi.domain.Agence;

public interface Rapport {
    String generer(Agence agence, double total);
}