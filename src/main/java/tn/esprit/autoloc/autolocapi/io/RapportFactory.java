package tn.esprit.autoloc.autolocapi.io;

import org.springframework.stereotype.Component;

@Component
public class RapportFactory {
    private final Rapport rapport;

    public RapportFactory(Rapport rapport) {
        this.rapport = rapport;
    }

    public Rapport getRapport() {
        return rapport;
    }
}