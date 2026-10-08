package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autolocapi.domain.*;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}

@SpringBootTest
class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;


    private void addAgence(CrudRepository<Agence, Long> repository) {
        int time = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setNom("Agence Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");
        agence.setTelephone("71000000");

        Vehicule v1 = Vehicule.builder()
                .immatriculation("123 TUN 4567-" + time)
                .marque("Renault")
                .modele("Clio")
                .annee(2022)
                .couleur("Blanc")
                .kilometrage(15000)
                .prixParJour(new BigDecimal("80.00"))
                .statut(StatutVehicule.values()[0])
                .categorie(CategorieVehicule.values()[0])
                .agence(agence)
                .build();

        Vehicule v2 = Vehicule.builder()
                .immatriculation("234 TUN 5678-" + time)
                .marque("Peugeot")
                .modele("208")
                .annee(2021)
                .couleur("Noir")
                .kilometrage(30000)
                .prixParJour(new BigDecimal("90.00"))
                .statut(StatutVehicule.values()[0])
                .categorie(CategorieVehicule.values()[0])
                .agence(agence)
                .build();

        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        repository.save(agence);
    }


    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }


    @Transactional
    void loadAgence(CrudRepository<Agence, Long> repository, String label) {
        StringBuilder sb = new StringBuilder("\n=== [" + label + "] ===\n");
        for (Agence a : repository.findAll()) {
            sb.append("Agence id : ").append(a.getId()).append("\n");
            sb.append("Nom : ").append(a.getNom()).append("\n");
            sb.append("Nombre de véhicules : ").append(a.getVehicules().size()).append("\n");
            for (Vehicule v : a.getVehicules()) {
                sb.append("   - Véhicule id : ").append(v.getId())
                        .append(" | Immatriculation : ").append(v.getImmatriculation()).append("\n");
            }
            sb.append("---\n");
        }
        Assertions.fail(sb.toString());
    }

    @Test

    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "CrudRepository");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "JpaRepository");
    }
    @Test
    public void loadSortedAgences() {
        StringBuilder sb = new StringBuilder();

        Sort sort = Sort.by("id").descending();
        for (Agence a : fullAgenceRepository.findAll(sort)) {   // ← full, pas basic
            sb.append(a.getId()).append(" | ").append(a.getNom()).append("\n");
        }
        fail(sb.toString());
    }
    @Test
    public void loadPagedAgences() {
        StringBuilder sb = new StringBuilder();

        Sort sort = Sort.by("id").descending();

        // Première page pour connaître le nombre total de pages
        Pageable firstPageable = PageRequest.of(0, 2, sort);
        Page<Agence> firstPage = fullAgenceRepository.findAll(firstPageable);

        int totalPages = firstPage.getTotalPages();

        sb.append("Total pages : ").append(totalPages).append("\n");
        sb.append("Taille de page : ").append(firstPage.getSize()).append("\n");
        sb.append("Total éléments : ").append(firstPage.getTotalElements()).append("\n\n");

        // Boucle sur chaque page
        for (int i = 0; i < totalPages; i++) {
            Pageable pageable = PageRequest.of(i, 2, sort);
            Page<Agence> page = fullAgenceRepository.findAll(pageable);

            sb.append("═══════ Page ").append(page.getNumber())
                    .append(" / ").append(totalPages - 1)
                    .append(" ═══════\n");

            for (Agence a : page.getContent()) {
                sb.append("  ").append(a.getId())
                        .append(" | ").append(a.getNom())
                        .append("\n");
            }
            sb.append("\n");
        }

        fail(sb.toString());
    }
}